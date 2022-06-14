package dao.imp;

import com.ua.kpi.beauty_salon.connection.Postgres_Connector;
import entity.Schedule;
import dao.ScheduleDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresScheduleDAO implements ScheduleDAO{
    private static final String COLUMN_SCHEDULE_ID = "id_schedule";
    private static final String COLUMN_MASTER_ID = "master_id";
    private static final String COLUMN_TIME = "datatime";
    private static final String COLUMN_APP_ID = "appoint_id";

    private static final String SELECT = "SELECT * FROM public.\"Schedule\"";
    private static final String UPDATE = "UPDATE public.\"Schedule\" SET "+COLUMN_MASTER_ID+"=?," +
            COLUMN_TIME+"=?,"+COLUMN_APP_ID+"=?"+ " WHERE "+ COLUMN_SCHEDULE_ID +"=?";
    private static final String INSERT = "INSERT INTO public.\"Schedule\" ("+COLUMN_MASTER_ID+", "+COLUMN_TIME+", "+
            COLUMN_APP_ID+") " + "VALUES (?, ?,?)";
    private static final String DELETE = "DELETE FROM public.\"Schedule\" WHERE "+COLUMN_SCHEDULE_ID+"=?";
    @Override
    public Schedule findById(Integer id) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT + " WHERE " + COLUMN_SCHEDULE_ID + "=?");

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        Schedule schedule = rs.next()? new Schedule(id, rs.getInt(COLUMN_MASTER_ID), rs.getTimestamp(COLUMN_TIME),
                rs.getInt(COLUMN_APP_ID)) : null;

        rs.close();
        ps.close();
        connection.close();

        return schedule;
    }

    @Override
    public List<Schedule> findAll() throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT);
        ResultSet rs = ps.executeQuery();

        List<Schedule> role = new ArrayList<>();

        while (rs.next()) {
            role.add(new Schedule(rs.getInt(COLUMN_SCHEDULE_ID), rs.getInt(COLUMN_MASTER_ID), rs.getTimestamp(COLUMN_TIME),
                    rs.getInt(COLUMN_APP_ID)));
        }

        rs.close();
        ps.close();
        connection.close();

        return role;
    }

    @Override
    public void update(Schedule schedule) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(UPDATE);

        ps.setInt(1, schedule.getMasterId());
        ps.setTimestamp(2, schedule.getTime());
        ps.setInt(3, schedule.getAppId());

        ps.setInt(4,schedule.getIdSchedule());

        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    @Override
    public boolean persist(Schedule schedule) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, schedule.getMasterId());
        ps.setTimestamp(2, schedule.getTime());
        ps.setInt(3, schedule.getAppId());


        if (ps.executeUpdate() == 1) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                schedule.setIdSchedule(rs.getInt(COLUMN_SCHEDULE_ID));
            }
            rs.close();
        }

        ps.close();
        connection.close();

        return schedule.getIdSchedule() != null;
    }

    @Override
    public void delete(Schedule schedule) throws SQLException {
        Connection connection = Postgres_Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE);

        ps.setInt(1, schedule.getIdSchedule());
        ps.executeUpdate();

        ps.close();
        connection.close();
    }
}



