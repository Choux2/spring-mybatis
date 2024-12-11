package spring.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.user.dto.BookInfoDTO;
import spring.user.service.BookService;
import spring.user.vo.BookVO;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    // 책 등록 crud
    @GetMapping("/addBookList")
    public String addBookList() {
        return "addBookList";
    }

    @PostMapping("/addBookList")
    public String addBookList(@ModelAttribute BookVO book) {
        bookService.addBookList(book);
        return "redirect:/bookList";
    }

    @GetMapping("/bookList")
    public String findBookListAll(Model model) { //model : 어떤 데이터를 화면으로 가져갈 수 있도록 해주는 전달객체
        List<BookInfoDTO> bookList = bookService.findBookListAll();
        model.addAttribute("bookList", bookList);

        return "bookList";
    }

    @GetMapping("/detailBookList/{bookNum}")
    public String findByBookNum(@PathVariable("bookNum") int bookNum, Model model) {
        BookVO book = bookService.findByBookNum(bookNum);
        model.addAttribute("bookInfo", book);

        return "detailBookList";
    }

    @GetMapping("/updateBookList/{bookNum}")
    public String updateBookList(@PathVariable("bookNum") int bookNum, Model model) {
        BookVO bookVO = bookService.findByBookNum(bookNum);
        model.addAttribute("bookInfo", bookVO);
        return "updateBookList";
    }

    @PostMapping("/updateBookList/{bookNum}")
    public String updateBookList(BookVO book, Model model) {
        bookService.updateBookList(book);
        BookVO bookVO = bookService.findByBookNum(book.getBookNum());
        model.addAttribute("bookInfo", bookVO);

        return "redirect:/detailBookList/" + bookVO.getBookNum();
    }

    @GetMapping("/deleteBookList/{bookNum}")
    public String deleteBookList(@PathVariable("bookNum") int bookNum) {
        bookService.deleteBookList(bookNum);
        return "redirect:/bookList";
    }

    @GetMapping("/addBook")
    @ResponseBody
    public BookInfoDTO addBook(@RequestParam("bookNum") int bookNum) {
        BookInfoDTO bookInfoDTO = bookService.addBook(bookNum);

        if (bookInfoDTO == null) {
            return null;
        }

        return bookInfoDTO;
    }
}
