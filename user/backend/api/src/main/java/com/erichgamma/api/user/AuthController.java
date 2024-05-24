package com.erichgamma.api.user;

import com.erichgamma.api.common.component.MessengerVo;
import com.erichgamma.api.user.model.UserDto;
import com.erichgamma.api.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

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

    @PostMapping(path = "/login")
    public ResponseEntity<MessengerVo> login(@RequestBody UserDto param){
        log.info("입력받은 정보 : {}",param );
        return ResponseEntity.ok(userservice.login(param));
    }

    @GetMapping("/existsByUsername")
    public ResponseEntity<Boolean> existByUsername(@RequestParam("username") String username) throws SQLException {
        Boolean flag = userservice.existByUsername(username);

        return ResponseEntity.ok(flag);
    }
}
