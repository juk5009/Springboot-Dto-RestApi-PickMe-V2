package shop.mtcoding.pickme.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeSaveReqDto;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeUpdateReqDto;

@Getter
@Setter
public class Userskill {
    private Integer id;
    private Integer resumeId;
    private Integer userId;
    private String userskillName;
    private Timestamp createdAt;

    public Userskill(ResumeSaveReqDto resumeSaveReqDto) {
        this.userskillName = resumeSaveReqDto.getUserskillList();
        this.resumeId = resumeSaveReqDto.getId();
        this.userId = resumeSaveReqDto.getUserId();
    }

    public Userskill(ResumeUpdateReqDto resumeUpdateReqDto) {
        this.userskillName = resumeUpdateReqDto.getUserskillList();
        this.resumeId = resumeUpdateReqDto.getId();
        this.userId = resumeUpdateReqDto.getUserId();
    }

    public Userskill() {

    }

}
