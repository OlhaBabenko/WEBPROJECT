package ua.kpi.daoNew;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ToCloseAfterConnection {

    private ResultSet resultSet;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ToCloseAfterConnection(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        this.resultSet = resultSet;
        this.connection = connection;
        this.preparedStatement = preparedStatement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }
}
