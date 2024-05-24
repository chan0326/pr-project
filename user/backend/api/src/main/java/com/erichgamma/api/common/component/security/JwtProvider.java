package com.erichgamma.api.common.component.security;

import com.erichgamma.api.user.model.User;
import com.erichgamma.api.user.model.UserDto;
import com.erichgamma.api.user.repository.UserRepository;
import com.erichgamma.api.user.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

@Log4j2
@Component
public class JwtProvider {
   private String issuer;
    private  final SecretKey secretKey ;

    Instant expiredDate = Instant.now().plus(1, ChronoUnit.DAYS);


    public  JwtProvider(@Value("${jwt.secret}") String secretKey){
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }
    public  String createToken(UserDto userDto){
        String token = Jwts.builder()
                .issuer(issuer)
                .signWith(secretKey)
                .expiration(Date.from(expiredDate))
                .subject("erichgamma")
                .claim("username",userDto.getUsername())
                .claim("job",userDto.getJob())
                .claim("userId",userDto.getId())
                .compact();
        log.info("로그인 성공으로 발급된 토근: "+token);
        return token;

    }

    public void printPayload(String Token) {
        String[] chunks = Token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        log.info("Token header :"+header);
        log.info("Token payload :"+payload);

//        return new StringBuilder().append(header).append(payload).toString();
    }
    public Claims getPayload(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

    public String extractTokenFromHeader(HttpServletRequest request)   {
        log.info("extractTokenFromHeader 받은 getContextPath : "+ request.getContextPath());
        log.info("extractTokenFromHeader 받은 getServletPath : "+ request.getServletPath());
        String bearerToken =  request.getHeader("Authorization");

        log.info("extractTokenFromHeader 받은 bearerToken : "+ bearerToken);


        return bearerToken != null && bearerToken.startsWith("Bearer ") ?  bearerToken.substring(7) : "undefined";
    }


}
