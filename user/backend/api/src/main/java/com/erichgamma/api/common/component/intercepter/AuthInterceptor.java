package com.erichgamma.api.common.component.intercepter;

import com.erichgamma.api.common.component.security.JwtProvider;
import com.erichgamma.api.user.model.User;
import com.erichgamma.api.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.stream.Stream;

@Log4j2
@RequiredArgsConstructor  //final 힐때 싱글톤.
@Component
public class AuthInterceptor  implements HandlerInterceptor {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = jwtProvider.extractTokenFromHeader(request);
//
//
//        log.info("1. 인터셉터 토큰 로그 bearer 포함 : {} ", token);
//        if (token.equals("undefined")){
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            return false;
//        }
//
//        Long id = jwtProvider.getPayload(token).get("userId",Long.class); // 디스크에 있는 정적 객체
//        log.info("2. 인터셉터 사용자 id : {} ", id);
//
//        Optional<User> user = userRepository.findById(id);
//        log.info("3. 인터셉터 사용자 정보 {} ", user);
//
//        if (!user.isPresent()){
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
//
//        log.info("4. 인터셉터 최종 여부 {}", true);

        return Stream.of(request)
                .map(jwtProvider::extractTokenFromHeader)//1번\
                .peek(i->log.info("1. 인터셉터 토큰 로그 bearer 포함 : {} "+i))
                .filter(i->!i.equals("undefined"))//1번 if
                .peek(i -> jwtProvider.printPayload(i))
                .map(i->jwtProvider.getPayload(i).get("userId",Long.class))
                .peek(i-> log.info("2. 인터셉터 사용자 id : {} ", i))
                .map(i->userRepository.existsById(i))
                .findFirst()
                .isPresent();
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
