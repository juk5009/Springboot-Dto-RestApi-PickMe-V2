package shop.mtcoding.pickme.dto.resume;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeSaveRespDto {
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
    private String userskillList;
    private Timestamp createdAt;

}
