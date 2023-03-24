package shop.mtcoding.pickme.dto.company;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

public class CompanyReq {

    @Getter
    @Setter
    public static class CompanyLoginReqDto {
        @NotEmpty(message = "회사 이름을 입력해주세요.")
        private String companyName;
        @NotEmpty(message = "비밀번호를 입력해주세요.")
        private String companyPassword;
    }

    @Getter
    @Setter
    public static class CompanyJoinReqDto {
        @NotEmpty(message = "회사 이름을 입력해주세요.")
        private String companyName;

        @NotEmpty(message = "비밀번호를 입력해주세요.")
        private String companyPassword;
        @NotEmpty(message = "이메일을 입력해주세요.")
        private String companyEmail;

    }

    @Setter
    @Getter
    public static class CompanyMypageReqDto {
        @NotEmpty(message = "회사 이름을 입력해주세요.")
        private String companyName;
        @NotEmpty(message = "비밀번호를 입력해주세요.")
        private String companyPassword;
        @NotEmpty(message = "이메일을 입력해주세요.")
        private String companyEmail;
    }

}
