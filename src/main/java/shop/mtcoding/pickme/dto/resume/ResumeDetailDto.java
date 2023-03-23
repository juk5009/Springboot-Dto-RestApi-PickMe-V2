package shop.mtcoding.pickme.dto.resume;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResumeDetailDto {
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
    private List<UserskillSaveRespDto> userskillList;

    @Getter
    @Setter
    public static class UserskillSaveRespDto {
        private Integer id;
        private String userskillName;

    }

}
