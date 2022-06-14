package dao;

import entity.Role;
import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {
    public Role findById(Integer id) throws SQLException;
    public List<Role> findAll() throws SQLException;
    public void update(Role role) throws SQLException;
    public boolean persist(Role role) throws SQLException;
    public void delete(Role role) throws SQLException;
}
