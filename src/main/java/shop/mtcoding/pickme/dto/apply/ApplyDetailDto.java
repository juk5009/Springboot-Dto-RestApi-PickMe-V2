package shop.mtcoding.pickme.dto.apply;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplyDetailDto {
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
    private List<UserskillDto> userskillList;

    @Setter
    @Getter
    public static class UserskillDto {
        private Integer id;
        private String userskillName;
    }
}