package entity;

import java.util.Objects;

public class Appointment {
    private Integer idApp;
    private Integer userId;
    private String procedure;



    public Appointment(Integer idApp, Integer userId, String procedure)
    {
        this.idApp = idApp;
        this.userId = userId;
        this.procedure = procedure;
    }

    public Appointment(Integer userId, String procedure) {
        this.userId = userId;
        this.procedure = procedure;
    }

    public Integer getIdApp() {
        return idApp;
    }

    public void setIdApp(Integer idApp) {
        this.idApp = idApp;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment appointment = (Appointment) o;
        return idApp ==  appointment.idApp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idApp);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + idApp +
                ", username='" + userId + '\'' +
                ", password='" + procedure + '\'' +
                '}';
    }
}
