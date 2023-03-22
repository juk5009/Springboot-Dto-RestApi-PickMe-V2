package shop.mtcoding.pickme.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeSaveReqDto;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeUpdateReqDto;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Resume {
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

    public Resume(ResumeSaveReqDto resumeSaveReqDto) {
        this.userId = resumeSaveReqDto.getUserId();
        this.resumeUsername = resumeSaveReqDto.getResumeUsername();
        this.resumeBirth = resumeSaveReqDto.getResumeBirth();
        this.resumeEmail = resumeSaveReqDto.getResumeEmail();
        this.resumeAddress = resumeSaveReqDto.getResumeAddress();
        this.resumeCareer = resumeSaveReqDto.getResumeCareer();
        this.resumeLocation = resumeSaveReqDto.getResumeLocation();
        this.resumeGrade = resumeSaveReqDto.getResumeGrade();
        this.resumePhoneNumber = resumeSaveReqDto.getResumePhoneNumber();
        this.resumeSex = resumeSaveReqDto.getResumeSex();
        this.resumeContent = resumeSaveReqDto.getResumeContent();
    }

    public Resume(ResumeUpdateReqDto resumeUpdateReqDto) {
        this.id = resumeUpdateReqDto.getId();
        this.userId = resumeUpdateReqDto.getUserId();
        this.resumeUsername = resumeUpdateReqDto.getResumeUsername();
        this.resumeBirth = resumeUpdateReqDto.getResumeBirth();
        this.resumeEmail = resumeUpdateReqDto.getResumeEmail();
        this.resumeAddress = resumeUpdateReqDto.getResumeAddress();
        this.resumeCareer = resumeUpdateReqDto.getResumeCareer();
        this.resumeLocation = resumeUpdateReqDto.getResumeLocation();
        this.resumeGrade = resumeUpdateReqDto.getResumeGrade();
        this.resumePhoneNumber = resumeUpdateReqDto.getResumePhoneNumber();
        this.resumeSex = resumeUpdateReqDto.getResumeSex();
        this.resumeContent = resumeUpdateReqDto.getResumeContent();
    }

};