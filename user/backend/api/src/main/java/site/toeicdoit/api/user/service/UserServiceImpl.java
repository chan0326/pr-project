package site.toeicdoit.api.user.service;


import site.toeicdoit.api.common.component.MessengerVo;
import site.toeicdoit.api.common.component.security.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.toeicdoit.api.user.model.UserModel;
import site.toeicdoit.api.user.model.UserDto;
import site.toeicdoit.api.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;




    @Override
    public MessengerVo save(UserDto userDto) {
//        entityToDto((userRepository.save(dtoToEntity(userDto))));
        return MessengerVo.builder()
                .message(
                        Stream.of(userDto)
                                .peek(i -> userRepository.save(dtoToEntity(i)))
                                .map(i -> "SUCCESS")
                                .findAny()
                                .orElseGet(() -> "FAILURE")
                )
                .build();
    }

    @Override
    public MessengerVo deleteById(Long id) {
        log.info(id);
        return MessengerVo.builder()
                .message(
                        Stream.of(id)
                                .filter(i->userRepository.existsById(i))
                                .peek(i->userRepository.deleteById(i))
                                .map(i->"SUCCESS")
                                .findAny()
                                .orElseGet(()->"FAILURE")
                )
                .build();

    }
    @Override
    public List<UserDto> findAll() {
//        List<User> userList = userRepository.findAll();
//        List<UserDto> list = new ArrayList<>();
//        for (User user: userList){
//            list.add(entityToDto(user));
//        }
//        userList.forEach(i -> list.add(entityToDto(i)));
//        return  list;
       return    userRepository.findAll().stream().map(i -> entityToDto(i)).toList();
    }

    @Override
    public Optional<UserDto> findById(Long id) {

        return userRepository.findById(id).map(i -> entityToDto(i));
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserModel autoRegister() {
        UserModel userModel = UserModel.builder()
                .username(UUID.randomUUID().toString())
                .email("example@example.com")
                .addressId("서울특별시 서초구 역삼동")
                .build();

        return userRepository.save(userModel);
    }

    @Override
    public MessengerVo modify(UserDto user) {
         Optional<UserModel> userList=userRepository.findById(user.getId());
         return MessengerVo.builder().message(
                 userRepository.findById(user.getId()).stream()
                         .peek(i->i.setName(user.getName() == null?userList.get().getName(): user.getName()))
                         .peek(i->i.setPassword(user.getPassword() == null?userList.get().getPassword():user.getPassword()))
                         .peek(i->i.setUsername(user.getUsername() == null?userList.get().getUsername():user.getUsername()))
                         .peek(i->i.setPhone(user.getPhone()== null?userList.get().getPhone():user.getPhone()))
                         .peek(i->i.setJob(user.getJob() == null?userList.get().getJob():user.getJob()))
                         .map(i->userRepository.save(i))
                         .map(i->"SUCCESS")
                         .findAny()
                         .orElseGet(()->"FAIL"))
         .build();
    }

    @Override
    public List<UserDto> findUsersByName(String name) {
        return userRepository.findUsersByName(name).stream().map(i -> entityToDto(i)).toList();
    }


    @Override
    public List<UserDto> findUsersByJob(String job) {
        return userRepository.findByJob(job).stream().map(i->entityToDto(i)).toList();
    }


    @Override
    public Optional<UserModel> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
// SRP 에 따라 아이디 존재여부를 프론트에서 먼저 판단하고, 넘어옴 (시큐리티) get()-> optional 때문에 아이디가 무조건존재한다는 뜻으로 사용 저걸 안쓰면  or else()
    @Transactional //에러 막는 용도
    @Override
    public MessengerVo login(UserDto param) {
        UserModel userModel = userRepository.findUserByUsername(param.getUsername()).get();
        log.info(entityToDto(userModel));
        boolean flag = userModel.getPassword().equals(param.getPassword());
        String Token = jwtProvider.createToken(entityToDto(userModel));


        jwtProvider.printPayload(Token);

        userRepository.modifiyTokenByID(userModel.getId(), Token);
        return MessengerVo.builder()
                .message( flag ? "SUCCESS":"FAILURE")
                .accessToken(flag ? Token: "none")
                .build();
    }



    @Override
    public Boolean existByUsername(String username) {
        Integer count = userRepository.existsByUsername(username);
        return count == 1;
    }

    @Transactional
    @Override
    public Boolean logout(String accessToken) {
        Long id = 1L
                ;
        String deletedToken = "";
        userRepository.modifiyTokenByID(id,deletedToken);

        Optional<UserModel> s= userRepository.findById(id);

        return s.get().getToken().equals("");
    }


}
