package shop.mtcoding.pickme.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListRespDto {
    private int id;
    private String resumeUsername;
    private String resumeCareer;
    private String resumeGrade;
    private UserProfileDto userProfile;

    @Getter
    @Setter
    public static class UserProfileDto {
        private Integer id;
        private String userProfile;
    }
}
