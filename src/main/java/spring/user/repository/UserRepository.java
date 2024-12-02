package spring.user.repository;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import spring.user.dto.UserBookDTO;
import spring.user.dto.UserDetailInfoDTO;
import spring.user.dto.UserInfoDTO;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final SqlSessionTemplate sql;
    public void join(UserInfoDTO userInfoDTO) {
        sql.insert("User.join", userInfoDTO);
    }

    public void joinDetail(UserDetailInfoDTO userDetailInfoDTO) {
        sql.insert("User.joinDetail", userDetailInfoDTO);
    }

    public UserInfoDTO login(UserInfoDTO userInfoDTO) {
        return sql.selectOne("User.login", userInfoDTO);
    }

    public UserInfoDTO findById(String userId) {
        return sql.selectOne("User.findById", userId);
    }

    public List<UserInfoDTO> findAll() {
        return sql.selectList("User.findAll");
    }

    public List<UserDetailInfoDTO> findDetailAll(String userId) {
        return sql.selectList("User.findDetailAllById", userId);
    }

    public List<UserBookDTO> findBookAll(String userId) {
        return sql.selectList("User.findBookAllById", userId);
    }

    public void update(UserInfoDTO userInfoDTO) {
        sql.update("User.update",userInfoDTO);
    }

    public void updateDetail(UserDetailInfoDTO userDetailInfoDTO) {
        sql.update("User.updateDetail", userDetailInfoDTO);
    }

    public void updateBook(UserBookDTO bookDTO) {
        sql.update("User.updateBook", bookDTO);
    }

    public void delete(String userId) {
        sql.delete("User.delete", userId);
    }

    public void rent(UserBookDTO userBookDTO) {
        sql.insert("User.rent", userBookDTO);
    }

    public void deleteBook(UserBookDTO userBookDTO) {
        sql.delete("User.deleteBook", userBookDTO);
    }
}
