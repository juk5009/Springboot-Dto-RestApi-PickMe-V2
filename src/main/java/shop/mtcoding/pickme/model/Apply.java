package shop.mtcoding.pickme.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Apply {
    private int id;
    private int resumeId;
    private int noticeId;
    private int userId;
    private int companyId;
    private Timestamp createdAt;
}
