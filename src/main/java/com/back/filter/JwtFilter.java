package com.back.filter;

import com.back.domain.LoginHistory;
import com.back.domain.dto.LoginDto;
import com.back.domain.params.LoginParam;
import com.back.service.LoginService;
import com.back.token.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final LoginService loginService;

    @Value("${jwt.validLongYn}")
    String validLongYn;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        String token = jwtUtils.resolveToken((HttpServletRequest) request);

        if(token != null){

            LoginParam loginParam = new LoginParam();
            loginParam.accessToken = token;

            if (jwtUtils.validateToken(token)) {
                if("Y".equals(validLongYn)){
                    Authentication authentication = jwtUtils.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else{
                    LoginHistory loginHistory = loginService.getTokenInfo(loginParam);

                    if(loginHistory != null){
                        Authentication authentication = jwtUtils.getAuthentication(token);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }else{
                        ObjectMapper mapper = new ObjectMapper();
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        response.setCharacterEncoding("UTF-8");
                        response.setStatus(HttpStatus.FORBIDDEN.value());

                        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                        Map<String, Object> resBody = new HashMap<>();
                        resBody.put("resultCode", "tokenInvalid");
                        resBody.put("message", "인증토큰이 유효하지 않습니다.");
                        map.put("header", resBody);

                        mapper.writeValue(response.getWriter(), map);
                    }
                }
            }
            else if(!jwtUtils.validateToken(token)) {

                ObjectMapper mapper = new ObjectMapper();
                LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                Map<String, Object> resBody = new HashMap<>();

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");

                //리프레시 토큰 발급 안하게 처리함
                if("Y".equals(validLongYn)){
                    response.setStatus(HttpStatus.FORBIDDEN.value());

                    resBody.put("resultCode", "tokenInvalid");
                    resBody.put("message", "인증토큰이 유효하지 않습니다.");
                    map.put("header", resBody);
                }else{
                    LoginHistory loginHistory = loginService.getTokenInfo(loginParam);

                    String refreshToken = "";
                    if(loginHistory != null){
                        refreshToken = loginHistory.refreshToken;
                    }

                    //todo 리프레시 토큰 테스트 필요(로직이 의심됨)
                    if(!"".equals(refreshToken)){
                        if (jwtUtils.validateToken(refreshToken)) {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            LoginDto loginDto = loginService.getLoginUser(loginParam.loginId, loginParam.authRole);
                            String accessToken = jwtUtils.generateToken(loginDto);
                            loginHistory.accessToken = accessToken;
                            loginService.updateToken(loginHistory);

                            resBody.put("resultCode", "newToken");
                            resBody.put("message", "새 인증토큰을 발급했습니다.");
                            resBody.put("newToken", accessToken);
                            resBody.put("newExpiredTokenTime", jwtUtils.getValidTime(accessToken));
                            map.put("header", resBody);
                        }else {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            resBody.put("resultCode", "tokenInvalid");
                            resBody.put("message", "인증토큰이 유효하지 않습니다.");
                            map.put("header", resBody);
                        }

                    }else{
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        resBody.put("resultCode", "tokenInvalid");
                        resBody.put("message", "인증토큰이 유효하지 않습니다.");
                        map.put("header", resBody);
                    }
                }

                mapper.writeValue(response.getWriter(), map);
            }
        }

        filterChain.doFilter(request , response);
    }
}
