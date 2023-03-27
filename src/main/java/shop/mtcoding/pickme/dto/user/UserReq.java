package shop.mtcoding.pickme.dto.user;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

public class UserReq {

    @Getter
    @Setter
    public static class UserJoinReqDto {
        @NotEmpty(message = "이름을 입력해주세요.")
        private String userName;
        @NotEmpty(message = "비밀번호 입력해주세요.")
        private String userPassword;
        @NotEmpty(message = "이메일을 입력해주세요.")
        private String userEmail;
        @NotEmpty(message = "role을 입력해주세요.")
        private String role;
    }

    @Getter
    @Setter
    public static class UserLoginReqDto {
        @NotEmpty(message = "이름을 입력해주세요.")
        private String userName;
        @NotEmpty(message = "비밀번호 입력해주세요.")
        private String userPassword;
    }

    @Setter
    @Getter
    public static class UserMyPageReqDto {
        @NotEmpty(message = "이름을 입력해주세요.")
        private String userName;
        @NotEmpty(message = "비밀번호 입력해주세요.")
        private String userPassword;
        @NotEmpty(message = "이메일을 입력해주세요.")
        private String userEmail;
    }
}
