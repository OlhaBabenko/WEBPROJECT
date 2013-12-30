package ua.kpi.daoNew;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import ua.kpi.connectionDB.ConnectionDB;
import ua.kpi.daoNew.interfaces.WorkTypeDaoInterface;
import ua.kpi.model.WorkType;

/**
 * Реализация методов для работы с таблицой БД WorkTypes интерфейса
 * WorkTypeDaoInterface
 *
 * @author Оля
 */
public class WorkTypeDAO extends MainStatements implements WorkTypeDaoInterface {

    public WorkTypeDAO(Logger log) {
        super(log);
    }
    private static final String SELECT_WORK_TYPES_FOR_USERS_CHOICE_COMMAND = "SELECT WType FROM WORKTYPES";
    private static final String SELECT_WORK_TYPE_ID_FOR_BIDS_DOING_COMMAND = "SELECT IDWType FROM  WORKTYPES WHERE WType = ?";
    private static final String SELECT_WORK_TYPE_BY_ID_COMMAND = "SELECT * FROM  WORKTYPES WHERE IDWType = ?";

    //МЕТОД ПОЛУЧЕНИЯ СПИСКА ТИПОВ РАБОТ ДЛЯ ВЫПАДАЮЩЕГО МЕНЮ ПРИ ОФОРМЛЕНИИ ЗАЯВКИ :
    @Override
    public List< String> selectWorkTypeForUsersChoice() {
        List< String> workTypesArray = new ArrayList();
        try {
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_WORK_TYPES_FOR_USERS_CHOICE_COMMAND);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    workTypesArray.add(resultSet.getString(1));
                }
            } finally {
                preparedStatement = toCloseAfterConnection.getPreparedStatement();
                Connection connection = toCloseAfterConnection.getConnection();
                ConnectionDB.closeConnection(resultSet, preparedStatement, connection);
            }
        } catch (SQLException e) {
            logger.info("SQLException was thrown");
            logger.error("Exception was thrown : ", e);
        }
        return workTypesArray;
    }

    //МЕТОД ПОЛУЧЕНИЯ ID ТИПА РАБОТ ДЛЯ ВНЕСЕНИЯ ЗАЯВКИ В ТАБЛИЦУ BIDS ПРИ ОФОРМЛЕНИИ ЗАЯВКИ :
    @Override
    public int selectWorkTypeID(String workType) {
        Object arg[] = {workType};
        int workTypeId = 0;
        try {
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_WORK_TYPE_ID_FOR_BIDS_DOING_COMMAND, arg);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    workTypeId = resultSet.getInt(1);
                }
            } finally {
                preparedStatement = toCloseAfterConnection.getPreparedStatement();
                Connection connection = toCloseAfterConnection.getConnection();
                ConnectionDB.closeConnection(resultSet, preparedStatement, connection);
            }
        } catch (SQLException e) {
            logger.info("SQLException was thrown");
            logger.error("Exception was thrown : ", e);
        }
        return workTypeId;
    }

    //МЕТОД ПОЛУЧЕНИЯ ТИПА РАБОТ ПО ID ТИПА РАБОТ :
    @Override
    public WorkType findWorkTypeByID(int workTypeID) {
        Object arg[] = {workTypeID};
        WorkType workType = new WorkType();
        try {
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_WORK_TYPE_BY_ID_COMMAND, arg);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    workType.setId(resultSet.getInt("IDWType"));
                    workType.setWorkType(resultSet.getString("WType"));
                }
            } finally {
                preparedStatement = toCloseAfterConnection.getPreparedStatement();
                Connection connection = toCloseAfterConnection.getConnection();
                ConnectionDB.closeConnection(resultSet, preparedStatement, connection);
            }
        } catch (SQLException e) {
            logger.info("SQLException was thrown");
            logger.error("Exception was thrown : ", e);
        }
        return workType;
    }
}
