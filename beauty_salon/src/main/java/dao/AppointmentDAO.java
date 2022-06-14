package dao;

import entity.Appointment;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentDAO {
    public Appointment  findById(Integer id) throws SQLException;
    public List<Appointment> findAll() throws SQLException;
    public void update(Appointment appointment) throws SQLException;
    public boolean persist(Appointment appointment) throws SQLException;
    public void delete(Appointment appointment) throws SQLException;
}
