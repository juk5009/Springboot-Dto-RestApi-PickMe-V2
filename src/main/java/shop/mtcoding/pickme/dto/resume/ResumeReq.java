package shop.mtcoding.pickme.dto.resume;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

public class ResumeReq {

    @Getter
    @Setter
    public static class ResumeSaveReqDto {
        private Integer id;
        private Integer userId;
        @NotEmpty(message = "이름을 작성해주세요.")
        private String resumeUsername;
        @NotEmpty(message = "생년월일을 작성해주세요.")
        private String resumeBirth;
        @NotEmpty(message = "email 작성해주세요.")
        private String resumeEmail;
        @NotEmpty(message = "주소를 작성해주세요.")
        private String resumeAddress;
        @NotEmpty(message = "지역을 선택해주세요.")
        private String resumeLocation;
        @NotEmpty(message = "경력를 작성해주세요.")
        private String resumeCareer;
        @NotEmpty(message = "학력을 작성해주세요.")
        private String resumeGrade;
        @NotEmpty(message = "휴대 전화번호를 작성해주세요.")
        private String resumePhoneNumber;
        @NotEmpty(message = "성별을 작성해주세요.")
        private String resumeSex;
        @NotEmpty(message = "자기소개서를 작성해주세요.")
        private String resumeContent;
        private String userskillList;
        private Timestamp createdAt;
    }

    @Getter
    @Setter
    public static class ResumeUpdateReqDto {
        private Integer id;
        private Integer userId;
        @NotEmpty(message = "이름을 작성해주세요.")
        private String resumeUsername;
        @NotEmpty(message = "생년월일을 작성해주세요.")
        private String resumeBirth;
        @NotEmpty(message = "email 작성해주세요.")
        private String resumeEmail;
        @NotEmpty(message = "주소를 작성해주세요.")
        private String resumeAddress;
        @NotEmpty(message = "지역을 선택해주세요.")
        private String resumeLocation;
        @NotEmpty(message = "경력를 작성해주세요.")
        private String resumeCareer;
        @NotEmpty(message = "학력을 작성해주세요.")
        private String resumeGrade;
        @NotEmpty(message = "휴대 전화번호를 작성해주세요.")
        private String resumePhoneNumber;
        @NotEmpty(message = "성별을 작성해주세요.")
        private String resumeSex;
        @NotEmpty(message = "자기소개서를 작성해주세요.")
        private String resumeContent;
        private String userskillList;
    }
}
