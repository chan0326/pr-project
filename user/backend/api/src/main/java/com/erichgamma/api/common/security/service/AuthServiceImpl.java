package com.erichgamma.api.common.security.service;

import com.erichgamma.api.common.component.MessengerVo;
import com.erichgamma.api.user.model.User;
import com.erichgamma.api.user.model.UserDto;
import com.erichgamma.api.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
//    private final UserRepository userRepository;
//    @Override
//    public MessengerVo modify(UserDto user) {
//        return null;
//    }
//
//
//    @Override
//    public MessengerVo login(UserDto param) {
//        boolean flag = findUserByUsername(param.getUsername()).get().getPassword().equals( param.getPassword());
//
//
//        return MessengerVo.builder()
//                .message( flag ? "SUCCESS":"FAILRE")
//                .token(flag ? createToken(param): "none")
//                .build();
//    }
//
//    @Override
//    public Optional<User> findUserByUsername(String username) {
//        return userRepository.findUserByUsername(username);
//    }
//
//    @Override
//    public String createToken(UserDto user) {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime tokenValid = now.plusSeconds(24*60*60*1000);
//        String token = Jwts.builder()
//                .claims()
//
//                .issuer("erichgamma.co.kr")
//                .add("sub","User Auth")
//                .add("exp",tokenValid)
//                .add("id",user.getId())
//                .add("username",user.getUsername())
//                .add("job","admin")
//                .and()
//
//                .compact();
//        log.info("로그인 성공으로 발급된 토근: "+token);
//        return token;
//
//    }


}
