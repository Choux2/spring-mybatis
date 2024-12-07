package spring.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserBookDTO {
    int bookNum;
    String userId;
    String date;
    int seq;

    String value;

}
