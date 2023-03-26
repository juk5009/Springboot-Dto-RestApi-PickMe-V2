package shop.mtcoding.pickme.config.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginCompany {
    private Integer id;
    private String role;

    @Builder
    public LoginCompany(Integer id, String role) {
        this.id = id;
        this.role = role;
    }
}
