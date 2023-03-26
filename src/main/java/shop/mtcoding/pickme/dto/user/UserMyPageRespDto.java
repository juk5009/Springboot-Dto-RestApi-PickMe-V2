package shop.mtcoding.pickme.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMyPageRespDto {
    private String userName;
    @JsonIgnore
    private String userPassword;
    private String userEmail;

    public UserMyPageRespDto(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }
}