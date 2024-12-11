package spring.user.service;

import spring.user.dto.BookInfoDTO;
import spring.user.dto.UserBookDTO;
import spring.user.dto.UserInfoDTO;
import spring.user.vo.BookVO;
import spring.user.vo.UserBookVO;
import spring.user.vo.UserVO;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    void join(UserVO user);

    boolean login(UserInfoDTO userInfoDTO);

    UserVO findById(String userId);

    List<UserInfoDTO> findAll();

    void update(UserVO user);

    void delete(String userId);

    void rent(UserBookDTO userBookDTO);

    void deleteBook(UserBookVO userBook);


}

