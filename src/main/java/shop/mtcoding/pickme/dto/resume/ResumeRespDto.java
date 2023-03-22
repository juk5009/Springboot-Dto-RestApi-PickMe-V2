package shop.mtcoding.pickme.dto.resume;

import java.sql.Timestamp;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeSaveReqDto;
import shop.mtcoding.pickme.model.Userskill;

@Setter
@Getter
public class ResumeRespDto {
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

    @Builder
    @Getter
    @Setter
    public static class ResumeDetailRespDtoV2 {
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
        private List<UserskillDto> userSkills;
        private Timestamp createdAt;

        public static ResumeDetailRespDtoV2 ofResumeDetailRespDtoV2 (ResumeRespDto resumeRespDto) {
            return ResumeDetailRespDtoV2.builder()
            .id(resumeRespDto.getId())
            .userId(resumeRespDto.getUserId())
            .resumeUsername(resumeRespDto.getResumeUsername())
            .resumeBirth(resumeRespDto.getResumeBirth())
            .resumeEmail(resumeRespDto.getResumeEmail())
            .resumeAddress(resumeRespDto.getResumeAddress())
            .resumeLocation(resumeRespDto.getResumeLocation())
            .resumeCareer(resumeRespDto.getResumeCareer())
            .resumeGrade(resumeRespDto.getResumeGrade())
            .resumePhoneNumber(resumeRespDto.getResumePhoneNumber())
            .resumeSex(resumeRespDto.getResumeSex())
            .resumeContent(resumeRespDto.getResumeContent())
            .build();
        }

        @Getter
        @Setter
        public static class UserskillDto { // 최종적으로 리턴하고 싶은 데이터를 dto로 만들기
            private Integer id;
            private Integer resumeId;
            private Integer userId;
            private String userskillName;
            private Timestamp createdAt;

        }
    }
}
