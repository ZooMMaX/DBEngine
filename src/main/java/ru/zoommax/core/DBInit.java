package ru.zoommax.core;

import lombok.SneakyThrows;
import ru.zoommax.api.DBType;
import ru.zoommax.core.configs.HikariConfMySQL;
import ru.zoommax.core.configs.HikariConfSQLite;
import ru.zoommax.core.exceptions.ParamDBException;

import javax.validation.constraints.NotNull;

public class DBInit {

    public void init(@NotNull DBType type, @NotNull String dbName){
        init(type, dbName, null, null);
    }
    @SneakyThrows
    public void init(@NotNull DBType type, @NotNull String dbName, String dbUserName, String dbUserPass) {
        if (type == DBType.SQLITE){
            if (dbName != null && !dbName.equals("")) {
                new HikariConfSQLite().init(dbName);
            }else {
                throw new ParamDBException(type, "dbName");
            }
        }

        if (type == DBType.MYSQL) {
            if ((dbName != null && !dbName.equals("")) &&
                    (dbUserName != null && !dbUserName.equals("")) &&
                    (dbUserPass != null && !dbUserPass.equals(""))) {
                new HikariConfMySQL().init(dbName, dbUserName, dbUserPass);
            }else {
                throw new ParamDBException(type, "dbName, dbUserName, dbUserPass");
            }
        }
    }
}
