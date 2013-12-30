package ua.kpi.daoNew;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import ua.kpi.connectionDB.ConnectionDB;
import ua.kpi.daoNew.interfaces.BrigadeDaoInterface;
import ua.kpi.model.Brigade;

/**
 * Реализация методов для работы с таблицой БД Brigades интерфейса
 * BrigadeDaoInterface
 *
 * @author Оля
 */
public class BrigadeDAO extends MainStatements implements BrigadeDaoInterface {

    public BrigadeDAO(Logger log) {
        super(log);
    }
    private static final String SELECT_BRIGADES_FOR_USERS_CHOICE_COMMAND = "SELECT BrigadeKind FROM BRIGADES";
    private static final String SELECT_BRIGADE_ID_BY_BRIGADE_NAME_COMMAND = "SELECT IDBrigade FROM BRIGADES WHERE BrigadeKind=?";
    private static final String SELECT_BRIGADE_BY_ID_FOR_WORK_PLAN_COMMAND = "SELECT BrigadeKind FROM BRIGADES WHERE IDBrigade=?";

    //МЕТОД ПОЛУЧЕНИЯ СПИСКА БРИГАД ДЛЯ ВЫПАДАЮЩЕГО МЕНЮ ПРИ ЗАПОЛНЕНИИ ПЛАНА РАБОТ :
    @Override
    public List< String> selectBrigadesForUsersChoice() {
        List< String> brigadesList = new LinkedList();
        try {
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_BRIGADES_FOR_USERS_CHOICE_COMMAND);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    brigadesList.add(resultSet.getString(1));
                }
            } finally {
                preparedStatement = toCloseAfterConnection.getPreparedStatement();
                Connection connection = toCloseAfterConnection.getConnection();
                ConnectionDB.closeConnection(resultSet, preparedStatement, connection);
            }
        } catch (SQLException e) {
            logger.info("SQLexception was thrown");
            logger.error("Exception was thrown : ", e);
        }
        return brigadesList;
    }

    //МЕТОД ПОЛУЧЕНИЯ ID БРИГАДЫ ПО ТИПУ БРИГАДЫ ДЛЯ ЗАНЕСЕНИЯ В ТАБЛИЦУ WORKPLAN ПРИ ОФОРМЛЕНИИ ПЛАНА РАБОТ:
    @Override
    public int selectBrigadeIdByBrigadeName(String brigade) {
        Object arg[] = {brigade};
        int brigadeId = 0;
        try {
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_BRIGADE_ID_BY_BRIGADE_NAME_COMMAND, arg);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    brigadeId = resultSet.getInt(1);
                }
            } finally {
                preparedStatement = toCloseAfterConnection.getPreparedStatement();
                Connection connection = toCloseAfterConnection.getConnection();
                ConnectionDB.closeConnection(resultSet, preparedStatement, connection);
            }
        } catch (SQLException e) {
            logger.info("SQLexception was thrown");
            logger.error("Exception was thrown : ", e);
        }
        return brigadeId;
    }

    //МЕТОД ПОЛУЧЕНИЯ БРИГАДЫ ПО ID БРИГАДЫ ДЛЯ ЗАПОЛЕНИЯ ПЛАНА РАБОТ :
    @Override
    public Brigade findBrigadeByID(int brigadeID) {
        Object arg[] = {brigadeID};
        Brigade brigade = new Brigade();
        try {
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_BRIGADE_BY_ID_FOR_WORK_PLAN_COMMAND, arg);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    brigade.setId(brigadeID);
                    brigade.setBrigadeKind(resultSet.getString("BrigadeKind"));
                }
            } finally {
                preparedStatement = toCloseAfterConnection.getPreparedStatement();
                Connection connection = toCloseAfterConnection.getConnection();
                ConnectionDB.closeConnection(resultSet, preparedStatement, connection);
            }
        } catch (SQLException e) {
            logger.info("SQLexception was thrown");
            logger.error("Exception was thrown : ", e);
        }
        return brigade;
    }
}
