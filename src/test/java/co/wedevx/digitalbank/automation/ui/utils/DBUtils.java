package co.wedevx.digitalbank.automation.ui.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.ConfigReader.getPropertiesValue;

public class DBUtils {
    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet resultSet = null;


    //1method to establish connection with DB
    public static void establishConnection() {

          // url, userName, password are in properties

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    getPropertiesValue("digitalbank.db.url"),
                    getPropertiesValue("digitalbank.db.username"),
                    getPropertiesValue("digitalbank.db.password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //2method that can dynamically send select statements
    //and returns a list(rows) of map(columns) of all columns

    public static List<Map<String, Object>> runSQLSelectQuery(String sqlQuery){

        List<Map<String, Object>> dbResultList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            //return info about info
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()){
                Map<String, Object> rowMap = new HashMap<>();
                for (int i = 1; i <= columnCount; i++){
                    rowMap.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
                }
                dbResultList.add(rowMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbResultList;
    }

    //3method that updates or inserts into DB
    //and returns the nums of the rows updated or 0 when action is not taken

    public static int runSQLUpdateQuery(String sqlQuery) {
        int rowsAffected = 0;
        try {
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sqlQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowsAffected;
    }

    //4method to close(close connection method)

    public static void closeConnection() {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
