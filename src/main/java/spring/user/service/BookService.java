package spring.user.service;

import spring.user.dto.BookInfoDTO;
import spring.user.vo.BookVO;

import java.util.List;

public interface BookService {
    void addBookList(BookVO book);

    List<BookInfoDTO> findBookListAll();

    BookVO findByBookNum(int bookNum);

    void updateBookList(BookVO bookVO);

    void deleteBookList(int bookNum);

    BookInfoDTO addBook(int bookNum);

}
