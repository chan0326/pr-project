package site.toeicdoit.api.user;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.toeicdoit.api.user.service.UserServiceImpl;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RestController  //controller + ResponseBody
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserServiceImpl userservice;

//    @PostMapping(path = "/login")
//    public ResponseEntity<MessengerVo> login(@RequestBody UserDto param){
//        log.info("입력받은 정보 : {}",param );
//        return ResponseEntity.ok(userservice.login(param));
//    }

//    @GetMapping("/existsByUsername")
//    public ResponseEntity<Boolean> existByUsername(@RequestParam("username") String username) throws SQLException {
//        Boolean flag = userservice.existByUsername(username);
//
//        return ResponseEntity.ok(flag);
//    }
}
