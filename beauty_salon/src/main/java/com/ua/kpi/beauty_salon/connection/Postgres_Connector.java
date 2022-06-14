package com.ua.kpi.beauty_salon.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Postgres_Connector {
    private static BasicDataSource dataSource;
    public Postgres_Connector(){}
    public static void initDataSource(){
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("25283948");
        dataSource.setMaxTotal(5);
    }

    public static  Connection getConnection() throws SQLException {
        if (dataSource == null) {
            initDataSource();
        }
            return dataSource.getConnection();
        }
    }

