package shop.mtcoding.pickme.dto.userskill;

import lombok.Getter;
import lombok.Setter;

public class UserskillReqDto {

    @Setter
    @Getter
    public static class UserskillSaveReqDto {
        private Integer resumeId;
        private String userskillName;
    }

}
