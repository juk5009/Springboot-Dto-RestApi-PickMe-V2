package shop.mtcoding.pickme.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyMypageRespDto {
    private String companyName;
    @JsonIgnore
    private String companyPassword;
    private String companyEmail;

    public CompanyMypageRespDto(String companyName, String companyPassword, String companyEmail) {
        this.companyName = companyName;
        this.companyPassword = companyPassword;
        this.companyEmail = companyEmail;
    }
}
