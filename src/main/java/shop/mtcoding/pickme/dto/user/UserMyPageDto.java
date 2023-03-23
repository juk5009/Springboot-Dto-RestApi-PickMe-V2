package shop.mtcoding.pickme.dto.user;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMyPageDto {
    private Integer id;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userProfile;
    private Timestamp createdAt;
    private List<ResumeSelectRespDto> resumeSelectList;

    @Getter
    @Setter
    public static class ResumeSelectRespDto {
        private String resumeUsername;
        private Integer id;
        private Integer userId;
    }
}