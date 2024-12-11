package spring.user.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.user.dto.BookInfoDTO;
import spring.user.repository.BookRepository;
import spring.user.service.BookService;
import spring.user.vo.BookVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public void addBookList(BookVO book) {
        BookInfoDTO bookInfoDTO = new BookInfoDTO();
        bookInfoDTO.setBookNum(book.getBookNum());
        bookInfoDTO.setBookName(book.getBookName());
        bookInfoDTO.setAuthor(book.getAuthor());
        bookInfoDTO.setPublisher(book.getPublisher());

        bookRepository.addBookList(bookInfoDTO);
    }

    @Override
    public List<BookInfoDTO> findBookListAll() {
        System.out.println("bookRepository = " + bookRepository);
        return bookRepository.findBookListAll();
    }

    @Override
    public BookVO findByBookNum(int bookNum) {
        BookInfoDTO bookInfoDTO = bookRepository.findByBookNum(bookNum);
        BookVO book = new BookVO();
        book.setBookNum(bookInfoDTO.getBookNum());
        book.setBookName(bookInfoDTO.getBookName());
        book.setAuthor(bookInfoDTO.getAuthor());
        book.setPublisher(bookInfoDTO.getPublisher());

        return book;
    }

    @Override
    public void updateBookList(BookVO bookVO) {

        BookInfoDTO bookInfoDTO = new BookInfoDTO();
        bookInfoDTO.setBookNum(bookVO.getBookNum());
        bookInfoDTO.setBookName(bookVO.getBookName());
        bookInfoDTO.setAuthor(bookVO.getAuthor());
        bookInfoDTO.setPublisher(bookVO.getPublisher());

        bookRepository.updateBookList(bookInfoDTO);
    }

    @Override
    public void deleteBookList(int bookNum) {
        bookRepository.deleteBookList(bookNum);
    }

    @Override
    public BookInfoDTO addBook(int bookNum) {
        return bookRepository.addBook(bookNum);
    }
}
