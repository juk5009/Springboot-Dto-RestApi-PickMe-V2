package shop.mtcoding.pickme.dto.company;

import lombok.Getter;
import lombok.Setter;

public class CompanyResp {

    @Setter
    @Getter
    public static class CompanyListRespDto {
        private int id;
        private String noticeCompanyname;

    }
}
