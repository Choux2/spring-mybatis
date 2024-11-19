package spring.user.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.user.dto.UserBookDTO;
import spring.user.dto.UserInfoDTO;
import spring.user.vo.UserVO;
import spring.user.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute UserVO user) {
//        System.out.println("userInfoDTO = " + userInfoDTO);
        userService.join(user);

//        UserDetailInfoDTO userDetailInfoDTO = new UserDetailInfoDTO();
//        userService.joinDetail(userDetailInfoDTO);

        return "login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserInfoDTO userInfoDTO,
                        HttpSession session) {
        boolean loginResult = userService.login(userInfoDTO);
        if (loginResult) {
            //로그인 성공
            session.setAttribute("loginId", userInfoDTO.getUserId());
            System.out.println("session = " + session);
            return "redirect:/";
        } else {
            //로그인 실패
            return "login";
        }
    }

    @GetMapping("/list")
    public String findAll(Model model) { //model : 어떤 데이터를 화면으로 가져갈 수 있도록 해주는 전달객체
        List<UserInfoDTO> userList = userService.findAll();
        model.addAttribute("userList", userList);
//        System.out.println("boardDTOList = " + userList);

        for (UserInfoDTO user : userList) {
            System.out.println(user);
        }
        return "list";
    }

    @GetMapping("/detail/{userId}") //id값이 계속 달라지기 때문에 중괄호로 표현
    public String findById(@PathVariable("userId") String userId, Model model) {
        //상세내역 불러오기
        UserVO userVo = userService.findById(userId);
        model.addAttribute("userInfo", userVo);
//        model.addAttribute("userDetailInfo", userVo.getDetailList());
        System.out.println("userInfoDTO = " + userVo);
        return "detail";
    }

    @GetMapping("/update/{userId}")
    public String updateFrom(@PathVariable("userId") String userId, Model model) {
        UserVO userVo = userService.findById(userId);
//        UserInfoDTO userInfoDTO = userService.findById(userId);
        model.addAttribute("userInfo", userVo);
        return "update";
    }

    //수정 후 상세내역 다시 불러오기
    @PostMapping("/update/{userId}")
    public String update(@PathVariable("userId") String userId, @ModelAttribute UserVO userVO) {
        userVO.setUserId(userId);
        userService.update(userVO);
//        UserInfoDTO dto = userService.findById(userInfoDTO.getUserId());
//        model.addAttribute("userInfo", dto);
        return "redirect:/detail/" + userId;
    }

    //회원정보 삭제
    @GetMapping("/delete/{userId}")
    public String delete(@PathVariable("userId") String userId) {
        userService.delete(userId);
        return "redirect:/list";
    }

    @GetMapping("/rent")
    public String rentForm() {
        return "rent";
    }

    @PostMapping("/rent")
    public String rent(@ModelAttribute UserBookDTO userBookDTO) {
        userService.rent(userBookDTO);
        return "redirect:/list";
    }

//    @PostMapping("/deleteBook")
//    public String deleteBook(@RequestParam("userId") String userId,
//                             @RequestParam("bookNum") Integer bookNum) {
//        userService.deleteBook(userId, bookNum);
//        return "redirect:/return/" + userId;
//    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam("userId") String userId) {
        userService.deleteBook(userId, 1);
        return "redirect:/return/" + userId;
    }
}
