package com.erichgamma.api.user.service;

import com.erichgamma.api.common.command.CommandService;
import com.erichgamma.api.common.component.MessengerVo;
import com.erichgamma.api.common.query.QueryService;
import com.erichgamma.api.user.model.User;
import com.erichgamma.api.user.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService extends CommandService<UserDto>, QueryService<UserDto> {
    MessengerVo modify(UserDto user);
    List<UserDto> findUsersByName(String name);


    List<UserDto> findUsersByJob(String job);

    Optional<User> findUserByUsername(String username);

    default User dtoToEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .name(dto.getName())
                .job(dto.getJob())
                .phone(dto.getPhone())
                .build();
    } //디티오를 엔티티로 바꿈

    default UserDto entityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .phone(user.getPhone())
                .job(user.getJob())
                .build();
    } // 엔티티를 디티오로 바꿈 이걸 하는 이유는 원투매니때문에 발생

    MessengerVo login(UserDto param);


    Boolean existByUsername(String username);

    Boolean logout(String accessToken);
}





