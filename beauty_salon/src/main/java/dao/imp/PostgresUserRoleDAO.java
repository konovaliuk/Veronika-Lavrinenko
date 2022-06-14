package dao.imp;

import com.ua.kpi.beauty_salon.connection.Postgres_Connector;
import dao.UserRoleDAO;
import entity.UserRole;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresUserRoleDAO implements UserRoleDAO{
    private static final String COLUMN_USER_ROLE_ID = "id_user_role";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_ROLE_ID = "role_id";

    // REQUEST
    private static final String SELECT = "SELECT * FROM public.\"UserRole\"";
    private static final String UPDATE = "UPDATE public.\"UserRole\" SET "+COLUMN_USER_ID+"=?," +
            COLUMN_ROLE_ID+"=?" + " WHERE "+ COLUMN_USER_ROLE_ID +"=?";
    private static final String INSERT = "INSERT INTO public.\"UserRole\" ("+COLUMN_USER_ID+", "+COLUMN_ROLE_ID+") " +
            "VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM public.\"UserRole\" WHERE "+COLUMN_USER_ROLE_ID+"=?";

    @Override
    public UserRole findById(Integer id) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT + " WHERE " + COLUMN_USER_ROLE_ID + "=?");

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        UserRole userrole = rs.next()? new UserRole(id, rs.getInt(COLUMN_USER_ID), rs.getInt(COLUMN_ROLE_ID)) : null;

        rs.close();
        ps.close();
        connection.close();

        return userrole;
    }

    @Override
    public List<UserRole> findAll() throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT);
        ResultSet rs = ps.executeQuery();

        List<UserRole> userrole = new ArrayList<>();

        while (rs.next()) {
            userrole.add(new UserRole(rs.getInt(COLUMN_USER_ROLE_ID), rs.getInt(COLUMN_USER_ID), rs.getInt(COLUMN_ROLE_ID)));
        }

        rs.close();
        ps.close();
        connection.close();

        return userrole;
    }

    @Override
    public void update(UserRole userrole) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(UPDATE);

        ps.setInt(1, userrole.getIduser());
        ps.setInt(2, userrole.getIdrole());
        ps.setInt(3,userrole.getUserroleId());
        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    @Override
    public boolean persist(UserRole userrole) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, userrole.getIduser());
        ps.setInt(2, userrole.getIdrole());

        if (ps.executeUpdate() == 1) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                userrole.setUserroleId(rs.getInt(COLUMN_USER_ROLE_ID));
            }
            rs.close();
        }

        ps.close();
        connection.close();

        return userrole.getUserroleId() != null;
    }

    @Override
    public void delete(UserRole userrole) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE);

        ps.setInt(1, userrole.getUserroleId());
        ps.executeUpdate();

        ps.close();
        connection.close();
    }
}


