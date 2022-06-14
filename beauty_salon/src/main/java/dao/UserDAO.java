package dao;

import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public User findById(Integer id) throws SQLException;
    public List<User> findAll() throws SQLException;
    public void update(User user) throws SQLException;
    public boolean persist(User user) throws SQLException;
    public void delete(User user) throws SQLException;

}
