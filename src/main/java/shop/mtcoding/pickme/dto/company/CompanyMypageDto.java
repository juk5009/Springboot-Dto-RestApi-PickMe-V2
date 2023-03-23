package shop.mtcoding.pickme.dto.company;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyMypageDto {
    private Integer id;
    private String companyName;
    @JsonIgnore
    private String companyPassword;
    private String companyEmail;
    private String companyProfile;
    private Timestamp createdAt;
    private List<NoticeListDto> noticeList;

    @Getter
    @Setter
    public static class NoticeListDto {
        private String noticeTitle;
        private Integer id;
        private Integer companyId;

    }
}
