package spring.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserBookDTO {
    String userId;
    int bookNum;
    String value;
}
