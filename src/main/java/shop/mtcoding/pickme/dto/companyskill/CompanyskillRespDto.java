package shop.mtcoding.pickme.dto.companyskill;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class CompanyskillRespDto {

    @Setter
    @Getter
    public static class CompanyskillSaveRespDto {
        private Integer id;
        private Integer noticeId;
        private Integer companyId;
        private String companyskillName;
        private Timestamp createdAt;
    }
}
