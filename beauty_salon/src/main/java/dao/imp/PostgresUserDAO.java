package dao.imp;

import com.ua.kpi.beauty_salon.connection.Postgres_Connector;
import dao.UserDAO;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresUserDAO implements UserDAO {
    // COLUMNS
    private static final String COLUMN_ID = "user_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // REQUEST
    private static final String SELECT = "SELECT * FROM public.\"Users\"";
    private static final String UPDATE = "UPDATE public.\"Users\" SET "+COLUMN_USERNAME+"=?," +
            COLUMN_PASSWORD+"=?"+ " WHERE "+ COLUMN_ID +"=?";
    private static final String INSERT = "INSERT INTO public.\"Users\" ("+COLUMN_USERNAME+", "+COLUMN_PASSWORD+") " +
            "VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM public.\"Users\" WHERE "+COLUMN_ID+"=?";

    @Override
    public User findById(Integer id) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT + " WHERE " + COLUMN_ID + "=?");

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        User user = rs.next()? new User(id, rs.getString(COLUMN_USERNAME), rs.getString(COLUMN_PASSWORD)) : null;

        rs.close();
        ps.close();
        connection.close();

        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT);
        ResultSet rs = ps.executeQuery();

        List<User> users = new ArrayList<>();

        while (rs.next()) {
            users.add(new User(rs.getInt(COLUMN_ID), rs.getString(COLUMN_USERNAME), rs.getString(COLUMN_PASSWORD)));
        }

        rs.close();
        ps.close();
        connection.close();

        return users;
    }

    @Override
    public void update(User user) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(UPDATE);

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setInt(3,user.getUserId());
        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    @Override
    public boolean persist(User user) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());

        if (ps.executeUpdate() == 1) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setUserId(rs.getInt(COLUMN_ID));
            }
            rs.close();
        }

        ps.close();
        connection.close();

        return user.getUserId() != null;
    }

    @Override
    public void delete(User user) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE);

        ps.setInt(1, user.getUserId());
        ps.executeUpdate();

        ps.close();
        connection.close();
    }
}
