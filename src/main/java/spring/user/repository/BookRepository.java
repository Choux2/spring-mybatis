package spring.user.repository;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import spring.user.dto.BookInfoDTO;
import spring.user.dto.UserInfoDTO;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final SqlSessionTemplate sql;

    public void addBookList(BookInfoDTO bookInfoDTO) {
        sql.insert("Book.addBookList", bookInfoDTO);
    }

    public List<BookInfoDTO> findBookListAll() {
        return sql.selectList("Book.findBookListAll");
    }

    public BookInfoDTO findByBookNum(int bookNum) {
        return sql.selectOne("Book.findByBookNum", bookNum);
    }

    public void updateBookList(BookInfoDTO bookInfoDTO) {
        sql.update("Book.updateBookList", bookInfoDTO);
    }

    public void deleteBookList(int bookNum) {
        sql.delete("Book.deleteBookList", bookNum);
    }

    public BookInfoDTO addBook(int bookNum) {
        return sql.selectOne("Book.addBook", bookNum);
    }

}
