package shop.mtcoding.pickme.dto.user;

import lombok.Getter;
import lombok.Setter;

public class UserResp {

    @Setter
    @Getter
    public static class UserListRespDto {
        private int id;
        private String resumeUsername;
        private String resumeCareer;
        private String resumeGrade;
        private String userProfile;
    }
}