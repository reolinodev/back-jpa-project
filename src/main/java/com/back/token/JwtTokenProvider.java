package com.back.token;

import com.back.domain.dto.LoginDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    String secretKey;

    @Value("${jwt.accessTokenValidTime}")
    private long accessTokenValidTime;

    @Value("${jwt.refreshTokenValidTime}")
    private long refreshTokenValidTime;

    @Value("${jwt.validLongYn}")
    private String validLongYn;

    private long tokenValidTime = 30 * 60 * 1000L;     // 토큰 유효시간 30분

    private final UserDetailsService userDetailsService;

    // 객체 초기화, secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateToken(LoginDto loginDto) {

        if("Y".equals(validLongYn)){
            accessTokenValidTime = 315360000000L;
        }
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, loginDto);
    }

    public String generateRefreshToken(LoginDto loginDto) {
        Map<String, Object> claims = new HashMap<>();
        return createRefreshToken(claims, loginDto);
    }

    // 토큰 생성
    private String createToken(Map<String, Object> claims, LoginDto loginDto) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(loginDto.loginId)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidTime))
            .claim("userId", loginDto.userId)
            .claim("loginId", loginDto.loginId)
//            .claim("auth_id", loginDto.auth_id)
            .claim("userNm", loginDto.userNm)
//            .claim("userPw", loginDto.userPw)
            .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    // 토큰 생성
    private String createRefreshToken(Map<String, Object> claims, LoginDto loginDto) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(loginDto.loginId)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidTime))
            .claim("userId", loginDto.userId)
            .claim("loginId", loginDto.loginId)
//            .claim("auth_id", loginDto.auth_id)
            .claim("userNm", loginDto.userNm)
//            .claim("userPw", loginDto.userPw)
            .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }


    // 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserData(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

//    public Authentication getAuthentication(String token) {
//        String loginId = this.getTokenInfo(token,"loginId");
//        String userPw = this.getTokenInfo(token,"userPw");
//        UserDetails userDetails = new org.springframework.security.core.userdetails.User(loginId, userPw, new ArrayList<>());
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }

    public Claims getClaims(String token) {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();
    }

    public String getTokenInfo(String token, String field) {
        return (String) getClaims(token).get(field);
    }


    // 토큰에서 회원 정보 추출
    public String getUserData(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public String getValidTime(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return claims.getBody().getExpiration().toString();
        } catch (Exception e) {
            return "";
        }
    }
}
