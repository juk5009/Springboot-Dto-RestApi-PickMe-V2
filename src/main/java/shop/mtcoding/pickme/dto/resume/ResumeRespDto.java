package shop.mtcoding.pickme.dto.resume;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeSaveReqDto;
import shop.mtcoding.pickme.model.Userskill;


@Setter
@Getter
public class ResumeRespDto {
    
    @Getter
    @Setter
    public static class ResumeUpdateRespDto {
        private Integer id;
        private Integer userId;
        private String resumeUsername;
        private String resumeBirth;
        private String resumeEmail;
        private String resumeAddress;
        private String resumeLocation;
        private String resumeCareer;
        private String resumeGrade;
        private String resumePhoneNumber;
        private String resumeSex;
        private String resumeContent;
        private Timestamp createdAt;
    }

    @Getter
    @Setter
    public static class ResumeDetailRespDto {
        private List<Userskill> userSkills;
        private ResumeSaveReqDto resumeSaveReqDto;
    }

}
