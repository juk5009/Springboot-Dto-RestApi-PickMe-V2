package shop.mtcoding.pickme.dto.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyProfileReqDto {
    private Integer id;
    private String companyProfile; // base64
}
