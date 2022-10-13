package ru.zoommax.DBEngine.core.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

public class HikariConfMySQL {
    @Getter
    private static Connection con;
    @SneakyThrows
    public void init(String dbName, String dbUserName, String dbUserPass) {

        String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull&useSSL=false";
        Connection connection = DriverManager.getConnection(url, dbUserName, dbUserPass);

        String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        HikariConfig config = new HikariConfig();
        config.setPoolName("DBEnginePool"+new Random().nextInt());
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false");
        config.setUsername(dbUserName);
        config.setPassword(dbUserPass);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        HikariDataSource ds = new HikariDataSource(config);
        con = ds.getConnection();

    }
}
