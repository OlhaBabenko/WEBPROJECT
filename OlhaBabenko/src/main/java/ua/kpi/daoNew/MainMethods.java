package ua.kpi.daoNew;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import ua.kpi.connectionDB.ConnectionDB;

/**
 * Методы для выполнения команд DML (SELECT,INSERT,UPDATE,DELETE). На вход
 * передаются : 1)команда, которую надо выполнить; 2)данные, нужные для
 * выполнения. Выполняются : 1)соединение с БД; 2)выполнение команды ;3)закрытие
 * соединения (для команды SELECT); 3)передача данных для использования\закрытия
 * в вызывающий методе(для команд INSERT,UPDATE,DELETE).
 *
 * @author Оля
 */
public class MainMethods {

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private Logger logger;

    public MainMethods(Logger log) {
        logger = log;
    }

    //МЕТОД ВЫПОЛНЕНИЯ КОМАНДЫ SELECT
    public ToCloseAfterConnection executeCommandSelect(String command, Object... args) throws SQLException {
        try {
       
        System.out.println(connection);
        connection = ConnectionDB.makeConnection();
        preparedStatement = connection.prepareStatement(command);
        inputArgumentsIntoPreparedStatement(args);
        resultSet = preparedStatement.executeQuery();
        ToCloseAfterConnection toCloseAfterConnection = new ToCloseAfterConnection(resultSet, preparedStatement, connection);
        return toCloseAfterConnection;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    //МЕТОД ВЫПОЛНЕНИЯ КОМАНД INSERT,UPDATE,DELETE  
    public void executeCommandsInsertUpdateDelete(String command, Object... args) {
        try {
            try {
                connection = ConnectionDB.makeConnection();
                preparedStatement = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
                inputArgumentsIntoPreparedStatement(args);
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();
            } finally {
                ConnectionDB.closeConnection(resultSet, preparedStatement, connection);
            }
        } catch (SQLException e) {
            logger.info("SQLexception was thrown");
            logger.error("Exception was thrown : ", e);
        } catch (ClassNotFoundException e) {
            logger.info("ClassNotFoundException was thrown");
            logger.error("Exception was thrown : ", e);
        }
    }

    //МЕТОД ПОЛУЧЕНИЯ АРГУМЕНТОВ ДЛЯ ВЫПОЛНЕНИЯ КОМАНДЫ DML
    public void inputArgumentsIntoPreparedStatement(Object... args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }
    }
}
