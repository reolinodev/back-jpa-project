package com.back.token;

import com.back.domain.dto.LoginDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    String secretKey;

    @Value("${jwt.accessTokenValidTime}")
    private long accessTokenValidTime;

    @Value("${jwt.refreshTokenValidTime}")
    private long refreshTokenValidTime;

    @Value("${jwt.validLongYn}")
    private String validLongYn;

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

    private String createToken(Map<String, Object> claims,  LoginDto loginDto) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(loginDto.loginId)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidTime))
            .claim("userId", loginDto.userId)
            .claim("loginId", loginDto.loginId)
//            .claim("auth_id", loginDto.auth_id)
            .claim("userNm", loginDto.userNm)
            .claim("userPw", loginDto.userPw)
            .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

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
            .claim("userPw", loginDto.userPw)
            .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();
    }

    public String getTokenInfo(String token, String field) {
        return (String) getClaims(token).get(field);
    }


    public Authentication getAuthentication(String token) {
        String loginId = this.getTokenInfo(token,"loginId");
        String userPw = this.getTokenInfo(token,"userPw");
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(loginId, userPw, new ArrayList<>());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
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

