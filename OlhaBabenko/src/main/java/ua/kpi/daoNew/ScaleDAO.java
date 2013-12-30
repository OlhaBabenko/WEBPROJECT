package ua.kpi.daoNew;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import ua.kpi.connectionDB.ConnectionDB;
import ua.kpi.daoNew.interfaces.ScaleDaoInterface;
import ua.kpi.model.Scale;

/**
 * Реализация методов для работы с таблицой БД Scales интерфейса
 * ScaleDaoInterface
 *
 * @author Оля
 */
public class ScaleDAO extends MainStatements implements ScaleDaoInterface {

    public ScaleDAO(Logger log) {
        super(log);
    }
    private static final String SELECT_WORK_SCALES_FOR_USERS_CHOICE_COMMAND = "SELECT WScale FROM SCALES";
    private static final String SELECT_WORK_SCALE_ID_FOR_BIDS_DOING_COMMAND = "SELECT IDScale FROM SCALES WHERE WScale = ?";
    private static final String SELECT_WORK_SCALE_BY_ID_COMMAND = "SELECT * FROM SCALES WHERE IDScale = ?";

    //МЕТОД ПОЛУЧЕНИЯ СПИСКА МАСШТАБОВ РАБОТ ДЛЯ ВЫПАДАЮЩЕГО МЕНЮ ПРИ ОФОРМЛЕНИИ ЗАЯВКИ :
    @Override
    public List<String> selectWorkScaleForUsersChoice() {
        List<String> workScalesList = new LinkedList();
        try {
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_WORK_SCALES_FOR_USERS_CHOICE_COMMAND);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    workScalesList.add(resultSet.getString(1));
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
        return workScalesList;
    }

    //МЕТОД ПОЛУЧЕНИЯ ID МАСШТАБА РАБОТ ДЛЯ ВНЕСЕНИЯ ЗАЯВКИ В ТАБЛИЦУ BIDS ПРИ ОФОРМЛЕНИИ ЗАЯВКИ :
    @Override
    public int selectWorkScaleID(String workScale) {
        Object arg[] = {workScale};
        int workScaleID = 0;
        try {
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_WORK_SCALE_ID_FOR_BIDS_DOING_COMMAND, arg);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    workScaleID = resultSet.getInt(1);
                }
                return workScaleID;
            } finally {
                preparedStatement = toCloseAfterConnection.getPreparedStatement();
                Connection connection = toCloseAfterConnection.getConnection();
                ConnectionDB.closeConnection(resultSet, preparedStatement, connection);
            }
        } catch (SQLException e) {
            logger.info("SQLException was thrown");
            logger.error("Exception was thrown : ", e);
            return 0;
        }
    }

    //МЕТОД ПОЛУЧЕНИЯ МАСШТАБА РАБОТ ПО ID МАСШТАБА РАБОТ :
    @Override
    public Scale findWorkScaleByID(int workScaleID) {
        Object arg[] = {workScaleID};
        Scale workScale = new Scale();
        try {
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_WORK_SCALE_BY_ID_COMMAND, arg);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    workScale.setId(resultSet.getInt("IDScale"));
                    workScale.setWorkScale(resultSet.getString("WScale"));
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
        return workScale;
    }
}
