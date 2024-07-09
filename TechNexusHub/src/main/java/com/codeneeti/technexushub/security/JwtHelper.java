//package com.codeneeti.technexushub.security;
//
//import io.jsonwebtoken.Claims;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.StringTokenizer;
//
//@Component
//public class JwtHelper {
//    public static final long JWT_TOKEN_VALIDITY=5*60*60;
//    @Value("${jwt.secret}")
//     private String secret;
//
////    public String getUserNameFromToken(String token){
////        return getClaimFromTOken(token, Claims::getSubject);
////    }
////
////    public Date getExpirationDateFromToken(String token){
////        return  getExpirationDateFromToken(token,Claims::getExpiration);
////    }
//
//}
