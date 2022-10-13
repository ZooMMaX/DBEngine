package ru.zoommax.DBEngine.api;

import lombok.AllArgsConstructor;
import ru.zoommax.DBEngine.core.configs.HikariConfMySQL;
import ru.zoommax.DBEngine.core.configs.HikariConfSQLite;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
public class DB {
    @NotNull private DBType dataBaseType;
    @NotNull private String sql;
    private List<Object> setData;

    private Connection connection() {
        switch (dataBaseType) {
            case SQLITE:
                return HikariConfSQLite.getCon();


            case MYSQL:
                return HikariConfMySQL.getCon();

            default:
                return HikariConfSQLite.getCon();
        }
    }

    /**
     * <p>execSQL.</p>
     *
     * @return a {@link java.lang.Boolean}
     */
    public boolean execSQL(){
        try(PreparedStatement stmt = connection().prepareStatement(sql)){
            if (setData != null && !setData.isEmpty()) {
                for(int x = 0; x < setData.size(); x++) {
                    stmt.setObject(x+1, setData.get(x));
                }
            }
            stmt.execute();
            return true;
        }catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }


    /**
     * <p>getMultiResultSet.</p>
     *
     * @return a {@link java.util.ArrayList} of the {@link java.util.HashMap}
     */
    public ArrayList<HashMap<String, Object>> getMultiResultSet() {
        ArrayList<HashMap<String, Object>> tmpHashMaps = new ArrayList<>();
        ArrayList<String> colNames = new ArrayList<>();
        try(PreparedStatement stmt = connection().prepareStatement(sql)){
            if (setData != null && !setData.isEmpty()) {
                for(int x = 0; x < setData.size(); x++) {
                    stmt.setObject(x+1, setData.get(x));
                }
            }
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            HashMap<String, Object> tmpHashMap = new HashMap<>();
            for(int x = 1; x < rsmd.getColumnCount()+1; x++) {
                colNames.add(rsmd.getColumnName(x));
            }
            while(rs.next()) {
                for(String s : colNames) {
                    tmpHashMap.put(s, rs.getObject(s));
                }
                tmpHashMaps.add(tmpHashMap);
            }
            return tmpHashMaps;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }
}
