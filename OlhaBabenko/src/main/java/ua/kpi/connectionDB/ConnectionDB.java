package ua.kpi.connectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

    private static Connection connection;

    public static Connection makeConnection() throws SQLException, ClassNotFoundException {
        connection = DBpool.getInstance().getConnection();
        return connection;
    }

    public static void closeConnection(ResultSet resultSet, Statement statement,Connection connection) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
