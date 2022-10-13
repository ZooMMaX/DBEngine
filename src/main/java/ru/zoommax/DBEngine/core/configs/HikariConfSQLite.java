package ru.zoommax.DBEngine.core.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.util.Random;

public class HikariConfSQLite {
    @Getter
    private static Connection con;
    @SneakyThrows
    public void init(String dbName) {
        HikariConfig config = new HikariConfig();
        config.setPoolName("DBEnginePool"+new Random().nextInt());
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:" + dbName + ".db");
        config.setConnectionTestQuery("SELECT 1");
        config.setMaxLifetime(60000); // 60 Second
        config.setIdleTimeout(45000); // 45 Second
        config.setMaximumPoolSize(50); // 50 Connections (including idle connections)
        HikariDataSource ds = new HikariDataSource(config);
        con = ds.getConnection();
    }
}
