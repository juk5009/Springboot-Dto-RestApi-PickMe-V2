package shop.mtcoding.pickme.dto.company;

import lombok.Getter;
import lombok.Setter;

public class CompanyReq {

    @Getter
    @Setter
    public static class CompanyLoginReqDto {
        private String companyName;
        private String companyPassword;
    }

    @Getter
    @Setter
    public static class CompanyJoinReqDto {
        private String companyName;
        private String companyPassword;
        private String companyEmail;

    }

    @Setter
    @Getter
    public static class CompanyMypageReqDto {
        private String companyName;
        private String companyPassword;
        private String companyEmail;
    }
}
