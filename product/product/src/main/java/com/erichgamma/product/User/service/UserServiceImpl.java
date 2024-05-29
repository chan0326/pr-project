package com.erichgamma.product.User.service;

import com.erichgamma.product.User.model.User;
import com.erichgamma.product.User.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements Userservice{
    private final UserRepository userRepository;

    @Override
    public User autoRegister() {
        User user = User.builder()
                .username(UUID.randomUUID().toString())
                .email("example@example.com")
                .addressId("서울특별시 서초구 역삼동")
                .build();

        return userRepository.save(user);
    }
}
