package dao;

import entity.Schedule;

import java.sql.SQLException;
import java.util.List;

public interface ScheduleDAO {
    public Schedule findById(Integer id) throws SQLException;
    public List<Schedule> findAll() throws SQLException;
    public void update(Schedule schedule) throws SQLException;
    public boolean persist(Schedule schedule) throws SQLException;
    public void delete(Schedule schedule) throws SQLException;
}
