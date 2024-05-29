package site.toeicdoit.api.user.service;

import site.toeicdoit.api.common.command.CommandService;
import site.toeicdoit.api.common.component.MessengerVo;
import site.toeicdoit.api.common.query.QueryService;
import site.toeicdoit.api.user.model.UserModel;
import site.toeicdoit.api.user.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService extends CommandService<UserDto>, QueryService<UserDto> {

    UserModel autoRegister();

    MessengerVo modify(UserDto user);
    List<UserDto> findUsersByName(String name);


    List<UserDto> findUsersByJob(String job);

    Optional<UserModel> findUserByUsername(String username);

    default UserModel dtoToEntity(UserDto dto) {
        return UserModel.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .name(dto.getName())
                .job(dto.getJob())
                .phone(dto.getPhone())
                .build();
    } //디티오를 엔티티로 바꿈

    default UserDto entityToDto(UserModel userModel) {
        return UserDto.builder()
                .id(userModel.getId())
                .username(userModel.getUsername())
                .password(userModel.getPassword())
                .name(userModel.getName())
                .phone(userModel.getPhone())
                .job(userModel.getJob())
                .build();
    } // 엔티티를 디티오로 바꿈 이걸 하는 이유는 원투매니때문에 발생

    MessengerVo login(UserDto param);


    Boolean existByUsername(String username);

    Boolean logout(String accessToken);
}





