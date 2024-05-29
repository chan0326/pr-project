package site.toeicdoit.api.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Log4j2
public class UserDto {
    private Long id;
    private String addressId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String job;
    private String regDate;
    private String modDate;
    private String token;

}
