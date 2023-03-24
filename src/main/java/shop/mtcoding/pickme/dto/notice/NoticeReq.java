package shop.mtcoding.pickme.dto.notice;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

public class NoticeReq {

    @Getter
    @Setter
    public static class NoticeSaveReqDto {

        private Integer id;
        private Integer companyId;
        @NotEmpty(message = "회사이름을 작성해주세요.")
        private String noticeCompanyname;
        @NotEmpty(message = "제목을 작성해주세요.")
        private String noticeTitle;
        @NotEmpty(message = "경력을 작성해주세요.")
        private String noticeCareer;
        @NotEmpty(message = "급여를 작성해주세요.")
        private String noticePay;
        @NotEmpty(message = "근무형태를 작성해주세요.")
        private String noticeEmploytype;
        @NotEmpty(message = "학력을 작성해주세요.")
        private String noticeGrade;
        @NotEmpty(message = "근무지역을 작성해주세요.")
        private String noticeLocation;
        @NotEmpty(message = "내용을 작성해주세요.")
        private String noticeContent;
        private String companyskillList;
    }

    @Getter
    @Setter
    public static class NoticeUpdateReqDto {
        private Integer id;
        private Integer companyId;
        @NotEmpty(message = "회사이름을 작성해주세요.")
        private String noticeCompanyname;
        @NotEmpty(message = "제목을 작성해주세요.")
        private String noticeTitle;
        @NotEmpty(message = "경력을 작성해주세요.")
        private String noticeCareer;
        @NotEmpty(message = "급여를 작성해주세요.")
        private String noticePay;
        @NotEmpty(message = "근무형태를 작성해주세요.")
        private String noticeEmploytype;
        @NotEmpty(message = "학력을 작성해주세요.")
        private String noticeGrade;
        @NotEmpty(message = "근무지역을 작성해주세요.")
        private String noticeLocation;
        @NotEmpty(message = "내용을 작성해주세요.")
        private String noticeContent;
        private String companyskillList;
    }
}
