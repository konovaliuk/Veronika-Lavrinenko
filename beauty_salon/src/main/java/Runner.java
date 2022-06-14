import com.ua.kpi.beauty_salon.connection.Postgres_Connector;
import dao.imp.*;
import entity.*;

import java.sql.*;

public class Runner {
    public static void main(String[] args) throws SQLException {
        PostgresUserDAO postgresUserDAO = new PostgresUserDAO();
        PostgresUserRoleDAO postgresUserRoleDAO = new PostgresUserRoleDAO();
        PostgresRoleDAO postgresRoleDAO = new PostgresRoleDAO();
        PostgresScheduleDAO postgresScheduleDAO = new PostgresScheduleDAO();
        PostgresAppointmentDAO postgresAppointmentDAO = new PostgresAppointmentDAO();
        //User
        System.out.println("*************************************************");
        System.out.println(postgresUserDAO.findAll());
        System.out.println("===============================================");
        User user = new User("lavrinka", "password");
        System.out.println(postgresUserDAO.persist(user));
        System.out.println(postgresUserDAO.findAll());
        System.out.println("===============================================");
        user.setPassword("new_password");
        postgresUserDAO.update(user);
        System.out.println(postgresUserDAO.findById(user.getUserId()));
        System.out.println(postgresUserDAO.findById(10));
        System.out.println("===============================================");
        postgresUserDAO.delete(user);
        System.out.println(postgresUserDAO.findAll());

        //Role
        System.out.println("*************************************************");
        System.out.println(postgresRoleDAO.findAll());
        System.out.println("===============================================");
        Role role = new Role("manikure master", "makes nails");
        System.out.println(postgresRoleDAO.persist(role));
        System.out.println(postgresRoleDAO.findAll());
        System.out.println("===============================================");
        role.setCodename("pedikure master");
        role.setDescription("makes nails on legs");
        postgresRoleDAO.update(role);
        System.out.println(postgresRoleDAO.findById(role.getIdRole()));
        System.out.println(postgresRoleDAO.findById(10));
        System.out.println("===============================================");
        postgresRoleDAO.delete(role);
        System.out.println(postgresRoleDAO.findAll());

        //UserRole
        System.out.println("*************************************************");
        System.out.println(postgresUserRoleDAO.findAll());
        System.out.println("===============================================");
        UserRole userRole = new UserRole(1, 1);
        System.out.println(postgresUserRoleDAO.persist(userRole));
        System.out.println(postgresUserRoleDAO.findAll());
        System.out.println("===============================================");
        userRole.setIdrole(2);
        userRole.setIduser(2);
        postgresUserRoleDAO.update(userRole);
        System.out.println(postgresUserRoleDAO.findById(userRole.getUserroleId()));
        System.out.println(postgresUserRoleDAO.findById(10));
        System.out.println("===============================================");
        postgresUserRoleDAO.delete(userRole);
        System.out.println(postgresUserRoleDAO.findAll());

        //Appointment
        System.out.println("*************************************************");
        System.out.println(postgresAppointmentDAO.findAll());
        System.out.println("===============================================");
        Appointment appointment = new Appointment(1, "pedikure");
        System.out.println(postgresAppointmentDAO.persist(appointment));
        System.out.println(postgresAppointmentDAO.findAll());
        System.out.println("===============================================");
        appointment.setUserId(2);
        appointment.setProcedure("haircut");
        postgresAppointmentDAO.update(appointment);
        System.out.println(postgresAppointmentDAO.findById(appointment.getIdApp()));
        System.out.println(postgresAppointmentDAO.findById(10));

        //Schedule
        System.out.println("*************************************************");
        System.out.println(postgresScheduleDAO.findAll());
        System.out.println("===============================================");
        Schedule schedule = new Schedule(1, Timestamp.valueOf("2022-06-15 12:00:00"),1);
        System.out.println(postgresScheduleDAO.persist(schedule));
        System.out.println(postgresScheduleDAO.findAll());
        System.out.println("===============================================");
        schedule.setMasterId(2);
        schedule.setTime(Timestamp.valueOf("2022-06-15 16:00:00"));
        postgresScheduleDAO.update(schedule);
        System.out.println(postgresScheduleDAO.findById(schedule.getIdSchedule()));
        System.out.println(postgresScheduleDAO.findById(10));

    }
}
