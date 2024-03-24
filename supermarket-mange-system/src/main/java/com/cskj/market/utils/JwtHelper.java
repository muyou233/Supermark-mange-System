package com.cskj.market.utils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Author lujun
 * @Description
 * @Date 2023/11/12 22:11
 * @Version 1.0
 */
public class JwtHelper {
    //token的有效期(单位:毫秒)
    private static long tokenExpiration = 60*60*1000;
    //token的密钥
    private static String tokenSignKey = "Yxmy";

    public static String createToken(Integer userId, String userName) {
        String token = Jwts.builder()
                .setSubject("Yxyx-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("userName", userName)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    //从token字符串获取userid
    public static Integer getUserId(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer) claims.get("userId");
        return userId;
    }

    //从token字符串获取userName
    public static String getUserName(String token) {
        if (StringUtils.isEmpty(token)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return String.valueOf(claims.get("userName"));
    }
	
	//判断token是否有效
    public static boolean isExpiration(String token){
        try {
            boolean isExpire = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().before(new Date());
            //没有过期，有效，返回false
            return isExpire;
        }catch(Exception e) {
            //过期出现异常，返回true
            return true;
        }
    }
}
