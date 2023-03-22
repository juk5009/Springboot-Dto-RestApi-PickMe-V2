package shop.mtcoding.pickme.dto.company;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.pickme.dto.notice.NoticeSelectRespDto;
import shop.mtcoding.pickme.model.Company;

@Setter
@Getter
public class CompanyMypageDto {
    private List<NoticeSelectRespDto> noticeSelectList;
    private Company companyProfilePS;
    private Company companyPS;

}
