package spring.user.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserVO {
    String userId;
    String userPw;
    String userName;
    String userGender;
    String userBirth;
    String userPhone;
    String userAddr;
    String userEmail;
    String userBook;
    List<BookVO> bookList;
}
