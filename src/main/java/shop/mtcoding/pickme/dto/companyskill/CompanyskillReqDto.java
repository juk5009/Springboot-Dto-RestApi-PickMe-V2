package shop.mtcoding.pickme.dto.companyskill;

import lombok.Getter;
import lombok.Setter;

public class CompanyskillReqDto {

    @Setter
    @Getter
    public static class CompanyskillSaveReqDto {
        private Integer noticeId;
        private String companyskillName;
    }

}
