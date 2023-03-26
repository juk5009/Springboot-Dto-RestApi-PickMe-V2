package shop.mtcoding.pickme.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileReqDto {
    private Integer id;
    private String userProfile; // base64
}
