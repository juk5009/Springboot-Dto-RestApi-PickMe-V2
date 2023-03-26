package shop.mtcoding.pickme.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyJoinRespDto {
    private String companyName;
    @JsonIgnore
    private String companyPassword;
    private String companyEmail;
}