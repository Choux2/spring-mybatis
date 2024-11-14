package spring.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserInfoDTO {
    String userId;
    String userPw;
    String userName;
    String userGender;
    String userBirth;
    List<UserDetailInfoDTO> detailList;
}
