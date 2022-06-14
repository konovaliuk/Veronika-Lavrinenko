package entity;
import java.util.Objects;

import java.sql.Timestamp;

public class Schedule {
    private Integer idSchedule;
    private Integer masterId;
    private Timestamp time;
    private Integer appId;


    public Schedule(Integer idSchedule, Integer masterId, Timestamp time, Integer appId)
    {
        this.idSchedule = idSchedule;
        this.masterId = masterId;
        this.time = time;
        this.appId = appId;
    }

    public Schedule(Integer masterId, Timestamp time,Integer appId ) {
        this.masterId = masterId;
        this.time = time;
        this.appId = appId;
    }
    public Integer getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Integer idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return idSchedule == schedule.idSchedule;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSchedule);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + idSchedule +
                ", username='" + masterId + '\'' +
                ", password='" + time + '\'' +
                ",appointment id='" + appId +'\''+
                '}';
    }
}
