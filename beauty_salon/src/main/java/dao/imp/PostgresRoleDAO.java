package dao.imp;

import com.ua.kpi.beauty_salon.connection.Postgres_Connector;
import dao.RoleDAO;
import entity.Role;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresRoleDAO implements RoleDAO {
    private static final String COLUMN_ROLE_ID = "id_role";
    private static final String COLUMN_CODENAME = "code_name";
    private static final String COLUMN_DESCRIPTION = "description";

    private static final String SELECT = "SELECT * FROM public.\"Role\"";
    private static final String UPDATE = "UPDATE public.\"Role\" SET "+COLUMN_CODENAME+"=?," +
            COLUMN_DESCRIPTION+"=?"+ " WHERE "+ COLUMN_ROLE_ID +"=?";
    private static final String INSERT = "INSERT INTO public.\"Role\" ("+COLUMN_CODENAME+", "+COLUMN_DESCRIPTION+") " +
            "VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM public.\"Role\" WHERE "+COLUMN_ROLE_ID+"=?";
    @Override
    public Role findById(Integer id) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT + " WHERE " + COLUMN_ROLE_ID + "=?");

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        Role role = rs.next()? new Role(id, rs.getString(COLUMN_CODENAME), rs.getString(COLUMN_DESCRIPTION)) : null;

        rs.close();
        ps.close();
        connection.close();

        return role;
    }

    @Override
    public List<Role> findAll() throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT);
        ResultSet rs = ps.executeQuery();

        List<Role> role = new ArrayList<>();

        while (rs.next()) {
            role.add(new Role(rs.getInt(COLUMN_ROLE_ID), rs.getString(COLUMN_CODENAME), rs.getString(COLUMN_DESCRIPTION)));
        }

        rs.close();
        ps.close();
        connection.close();

        return role;
    }

    @Override
    public void update(Role role) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(UPDATE);

        ps.setString(1, role.getCodename());
        ps.setString(2, role.getDescription());
        ps.setInt(3,role.getIdRole());

        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    @Override
    public boolean persist(Role role) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, role.getCodename());
        ps.setString(2, role.getDescription());

        if (ps.executeUpdate() == 1) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                role.setIdRole(rs.getInt(COLUMN_ROLE_ID));
            }
            rs.close();
        }

        ps.close();
        connection.close();

        return role.getIdRole() != null;
    }

    @Override
    public void delete(Role role) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE);

        ps.setInt(1, role.getIdRole());
        ps.executeUpdate();

        ps.close();
        connection.close();
    }
}


