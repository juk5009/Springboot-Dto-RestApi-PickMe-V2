package shop.mtcoding.pickme.dto.user;

import lombok.Getter;
import lombok.Setter;

public class UserReq {

    @Getter
    @Setter
    public static class UserJoinReqDto {
        private String userName;
        private String userPassword;
        private String userEmail;
    }

    @Getter
    @Setter
    public static class UserLoginReqDto {
        private String userName;
        private String userPassword;
    }

    @Setter
    @Getter
    public static class UserMyPageReqDto {
        private String userName;
        private String userPassword;
        private String userEmail;
    }
}
