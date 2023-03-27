package shop.mtcoding.pickme.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinRespDto {
    private String userName;
    @JsonIgnore
    private String userPassword;
    private String userEmail;
    private String role;
}