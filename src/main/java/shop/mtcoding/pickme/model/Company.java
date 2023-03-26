package shop.mtcoding.pickme.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {
    private Integer id;
    private String companyName;
    private String companyPassword;
    private String companyEmail;
    private String companyProfile;
    private String role;
    private Timestamp createdAt;
}
