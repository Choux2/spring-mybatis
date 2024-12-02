package spring.user.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.user.dto.UserBookDTO;
import spring.user.dto.UserDetailInfoDTO;
import spring.user.dto.UserInfoDTO;
import spring.user.vo.BookVO;
import spring.user.vo.UserVO;
import spring.user.repository.UserRepository;
import spring.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    public void join(UserInfoDTO userInfoDTO) {
//        userRepository.join(userInfoDTO);
//    }

    public void join(UserVO user) {

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(user.getUserId());
        userInfoDTO.setUserPw(user.getUserPw());
        userInfoDTO.setUserName(user.getUserName());
        userInfoDTO.setUserGender(user.getUserGender());
        userInfoDTO.setUserBirth(user.getUserBirth());

        List<UserDetailInfoDTO> detailInfoDTOS = new ArrayList<>();
        UserDetailInfoDTO addr = new UserDetailInfoDTO();
        addr.setUserId(user.getUserId());
        addr.setProp("addr");
        addr.setValue(user.getUserAddr());
        detailInfoDTOS.add(addr);

        UserDetailInfoDTO email = new UserDetailInfoDTO();
        email.setUserId(user.getUserId());
        email.setProp("email");
        email.setValue(user.getUserEmail());
        detailInfoDTOS.add(email);

        UserDetailInfoDTO phone = new UserDetailInfoDTO();
        phone.setUserId(user.getUserId());
        phone.setProp("phone");
        phone.setValue(user.getUserPhone());
        detailInfoDTOS.add(phone);

        userRepository.join(userInfoDTO);

        for(UserDetailInfoDTO detailInfoDTO : detailInfoDTOS) {
            userRepository.joinDetail(detailInfoDTO);
        }
    }

    public boolean login(UserInfoDTO userInfoDTO) {
        UserInfoDTO loginUser = userRepository.login(userInfoDTO);
        if (loginUser != null) {
            return true;
        } else {
            return false;
        }
    }

    public List<UserInfoDTO> findAll() {
        List<UserInfoDTO> userInfoDTOList = userRepository.findAll();
        return userInfoDTOList;
    }

    @Override
    public UserVO findById(String userId) {
        UserInfoDTO userInfoDTO = userRepository.findById(userId);
        List<UserDetailInfoDTO> details = userRepository.findDetailAll(userInfoDTO.getUserId());
        List<UserBookDTO> books = userRepository.findBookAll(userInfoDTO.getUserId());
//        userInfoDTO.setDetailList(details);
        UserVO user = new UserVO();
        user.setUserId(userInfoDTO.getUserId());
        user.setUserPw(userInfoDTO.getUserPw());
        user.setUserName(userInfoDTO.getUserName());
        user.setUserGender(userInfoDTO.getUserGender());
        user.setUserBirth(userInfoDTO.getUserBirth());

        for (UserDetailInfoDTO detail : details) {
            switch (detail.getProp()) {
                case "addr":
                    user.setUserAddr(detail.getValue());
                    break;
                case "email":
                    user.setUserEmail(detail.getValue());
                    break;
                case "phone":
                    user.setUserPhone(detail.getValue());
                    break;
            }
        }

        List<BookVO> bookList = new ArrayList<>();
        for (UserBookDTO book : books) {
            BookVO bookVO = new BookVO();
            bookVO.setUserId(book.getUserId());
            bookVO.setBookNum(book.getBookNum());
            bookVO.setValue(book.getValue());
            bookList.add(bookVO);
        }
        user.setBookList(bookList);

        return user;
    }

    public void update(UserVO user) {

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(user.getUserId());
        userInfoDTO.setUserPw(user.getUserPw());
        userInfoDTO.setUserName(user.getUserName());
        userInfoDTO.setUserGender(user.getUserGender());
        userInfoDTO.setUserBirth(user.getUserBirth());

        List<UserDetailInfoDTO> detailInfoDTOS = new ArrayList<>();
        UserDetailInfoDTO addr = new UserDetailInfoDTO();
        addr.setUserId(user.getUserId());
        addr.setProp("addr");
        addr.setValue(user.getUserAddr());
        detailInfoDTOS.add(addr);

        UserDetailInfoDTO email = new UserDetailInfoDTO();
        email.setUserId(user.getUserId());
        email.setProp("email");
        email.setValue(user.getUserEmail());
        detailInfoDTOS.add(email);

        UserDetailInfoDTO phone = new UserDetailInfoDTO();
        phone.setUserId(user.getUserId());
        phone.setProp("phone");
        phone.setValue(user.getUserPhone());
        detailInfoDTOS.add(phone);

        userRepository.update(userInfoDTO);

        for (UserDetailInfoDTO detailInfoDTO : detailInfoDTOS) {
            userRepository.updateDetail(detailInfoDTO);
        }

        List<UserBookDTO> bookDTOS = new ArrayList<>();
        for (BookVO bookVO : user.getBookList()) {
            UserBookDTO bookDTO = new UserBookDTO();
            bookDTO.setUserId(user.getUserId());
            bookDTO.setBookNum(bookVO.getBookNum());
            bookDTO.setValue(bookVO.getValue());
            bookDTOS.add(bookDTO);
            userRepository.updateBook(bookDTO);
        }

//        for (UserBookDTO bookDTO : bookDTOS) {
//            userRepository.updateBook(bookDTO);
//        }
    }

    @Override
    public void delete(String userId) {
        userRepository.delete(userId);
    }

    @Override
    public void rent(UserBookDTO userBookDTO) {
        userRepository.rent(userBookDTO);
    }

    @Override
    public void deleteBook(BookVO book) {
        UserBookDTO userBookDTO = new UserBookDTO();
        userBookDTO.setUserId(book.getUserId());
        userBookDTO.setBookNum(book.getBookNum());
        userRepository.deleteBook(userBookDTO);
    }

//    @Override
//    public void updateBook(String userId, BookVO book) {
//        UserBookDTO userBookDTO = new UserBookDTO();
//        userBookDTO.setUserId(book.getUserId());
//        userBookDTO.setBookNum(book.getBookNum());
//        userBookDTO.setValue(book.getValue());
//        userRepository.updateBook(userBookDTO);
//    }

//    @Override
//    public void deleteBook(String userId, int bookNum) {
//        UserBookDTO userBookDTO = new UserBookDTO();
//        userBookDTO.setUserId(userId);
//        userBookDTO.setBookNum(bookNum);
//        userRepository.deleteBook(userBookDTO);
//    }
}
