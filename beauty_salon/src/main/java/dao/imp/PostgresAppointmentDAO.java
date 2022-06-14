package dao.imp;

import com.ua.kpi.beauty_salon.connection.Postgres_Connector;
import dao.AppointmentDAO;
import entity.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresAppointmentDAO implements AppointmentDAO {
    private static final String COLUMN_APP_ID = "app_id";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_PROCEDURE = "procedures";

    private static final String SELECT = "SELECT * FROM public.\"Appointment\"";
    private static final String UPDATE = "UPDATE public.\"Appointment\" SET "+COLUMN_USER_ID+"=?," +
            COLUMN_PROCEDURE+"=?"+ " WHERE "+ COLUMN_APP_ID  +"=?";
    private static final String INSERT = "INSERT INTO public.\"Appointment\" ("+COLUMN_USER_ID+", "+COLUMN_PROCEDURE+") " +
            "VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM public.\"Appointment\" WHERE "+ COLUMN_APP_ID +"=?";
    @Override
    public Appointment findById(Integer id) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT + " WHERE " +  COLUMN_APP_ID  + "=?");

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        Appointment role = rs.next()? new Appointment(id, rs.getInt(COLUMN_USER_ID), rs.getString(COLUMN_PROCEDURE )) : null;

        rs.close();
        ps.close();
        connection.close();

        return role;
    }

    @Override
    public List<Appointment> findAll() throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT);
        ResultSet rs = ps.executeQuery();

        List<Appointment> role = new ArrayList<>();

        while (rs.next()) {
            role.add(new Appointment(rs.getInt(COLUMN_APP_ID), rs.getInt(COLUMN_USER_ID), rs.getString(COLUMN_PROCEDURE)));
        }

        rs.close();
        ps.close();
        connection.close();

        return role;
    }

    @Override
    public void update(Appointment appointment) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(UPDATE);

        ps.setInt(1, appointment.getUserId());
        ps.setString(2, appointment.getProcedure());
        ps.setInt(3,appointment.getIdApp());
        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    @Override
    public boolean persist(Appointment appointment) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, appointment.getUserId());
        ps.setString(2, appointment.getProcedure());

        if (ps.executeUpdate() == 1) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                appointment.setIdApp(rs.getInt(COLUMN_APP_ID));
            }
            rs.close();
        }

        ps.close();
        connection.close();

        return appointment.getIdApp() != null;
    }

    @Override
    public void delete(Appointment appointment) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE);

        ps.setInt(1, appointment.getIdApp());
        ps.executeUpdate();

        ps.close();
        connection.close();
    }
}
