package shop.mtcoding.pickme.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private Integer id;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userProfile;
    private Timestamp createdAt;
}
