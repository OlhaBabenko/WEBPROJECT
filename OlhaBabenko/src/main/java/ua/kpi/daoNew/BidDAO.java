package ua.kpi.daoNew;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import ua.kpi.connectionDB.ConnectionDB;
import ua.kpi.daoNew.interfaces.BidDaoInterface;
import ua.kpi.model.Bid;
import ua.kpi.model.Scale;
import ua.kpi.model.Tenant;
import ua.kpi.model.WorkType;

/**
 * Реализация методов для работы с таблицой БД Bids интерфейса BidDaoInterface
 *
 * @author Оля
 */
public class BidDAO extends MainStatements implements BidDaoInterface {

    public BidDAO(Logger log) {
        super(log);
    }
    private static final String INSERT_BID_AS_NEW_COMMAND = "INSERT INTO Bids(Tenant,WorkType,WorkScale,TimeToDoWish,DateOfFilling,Status) "
            + "VALUES (?,?,?,?,?,?)";
    private static final String SELECT_INFORMATION_FOR_USERS_BIDS_TABLE_COMMAND = "SELECT * FROM BIDS WHERE Tenant=?";
    private static final String SELECT_INFORMATION_FOR_ALL_NEW_BIDS_TABLE_COMMAND = "SELECT * FROM BIDS WHERE Status=?";
    private static final String SELECT_BIDS_BY_ID_FOR_WORK_PLAN_COMMAND = "SELECT * FROM BIDS WHERE IDBid=?";
    private static final String UPDATE_BIDS_STATUS_COMMAND = "UPDATE BIDS SET Status = ?,TimeToDoWish=? where IDBid = ?";

    //МЕТОД ЗАПОЛНЕНИЯ ЗАЯВКИ ПОЛЬЗОВАТЕЛЕМ:
    @Override
    public void insertUserIntoBids(int tenant, int workType, int workScale, String timeToDoWish, String dateOfFilling, String status) {
        Object args[] = {tenant, workType, workScale, timeToDoWish, dateOfFilling, status};
        callMainMethod.executeCommandsInsertUpdateDelete(INSERT_BID_AS_NEW_COMMAND, args);
    }

    //МЕТОД ПОЛУЧЕНИЯ ДАННЫХ О ЗАЯВКЕ ДЛЯ ВЫВОДА ТАБЛИЦЫ ЗАЯВОК ПОЛЬЗОВАТЕЛЯ:
    @Override
    public List<Bid> makeBidsListForBidsTableOfTenant(int tenantID) {
        List<Bid> listOfBids = new LinkedList();
        Object args[] = {tenantID};
        try {
            TenantDAO tenantDao = new TenantDAO(logger);
            WorkTypeDAO workTypeDao = new WorkTypeDAO(logger);
            ScaleDAO scaleDao = new ScaleDAO(logger);
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_INFORMATION_FOR_USERS_BIDS_TABLE_COMMAND, args);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    Bid bid = new Bid();
                    bid.setId(resultSet.getInt("IDBid"));
                    Tenant tenant = tenantDao.findTenantByID(resultSet.getInt("Tenant"));
                    bid.setTenant(tenant);
                    WorkType workType = workTypeDao.findWorkTypeByID(resultSet.getInt("WorkType"));
                    bid.setWorkType(workType);
                    Scale workScale = scaleDao.findWorkScaleByID(resultSet.getInt("WorkScale"));
                    bid.setWorkScale(workScale);
                    bid.setTimeToDoWish(resultSet.getString("TimeToDoWish"));
                    bid.setDateOfFilling(resultSet.getString("DateOfFilling"));
                    bid.setStatus(resultSet.getString("Status"));
                    listOfBids.add(bid);
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
        return listOfBids;
    }

    //МЕТОД ПОЛУЧЕНИЯ ДАННЫХ О ВСЕХ НОВЫХ ЗАЯВКАХ ДЛЯ ЗАНЕСЕНИЯ В ПЛАН РАБОТ:
    @Override
    public List<Bid> makeBidsListForBidsTableOfAllNewBids(String status) {
        List<Bid> listOfBids = new LinkedList();
        Object args[] = {status};
        try {
            TenantDAO tenantDao = new TenantDAO(logger);
            WorkTypeDAO workTypeDao = new WorkTypeDAO(logger);
            ScaleDAO scaleDao = new ScaleDAO(logger);
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_INFORMATION_FOR_ALL_NEW_BIDS_TABLE_COMMAND, args);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    Bid bid = new Bid();
                    bid.setId(resultSet.getInt("IDBid"));
                    Tenant tenant = tenantDao.findTenantByID(resultSet.getInt("Tenant"));
                    bid.setTenant(tenant);
                    WorkType workType = workTypeDao.findWorkTypeByID(resultSet.getInt("WorkType"));
                    bid.setWorkType(workType);
                    Scale workScale = scaleDao.findWorkScaleByID(resultSet.getInt("WorkScale"));
                    bid.setWorkScale(workScale);
                    bid.setTimeToDoWish(resultSet.getString("TimeToDoWish"));
                    bid.setDateOfFilling(resultSet.getString("DateOfFilling"));
                    bid.setStatus(resultSet.getString("Status"));
                    listOfBids.add(bid);
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
        return listOfBids;
    }

    //МЕТОД ОБНОВЛЕНИЯ ДАННЫХ О СТАТУСЕ И ВРЕМЕНИ ВЫПОЛНЕНИЯ ЗАЯВКИ ПОСЛЕ ЕЁ ЗАНЕСЕНИЯ В ПЛАН РАБОТ:
    @Override
    public void updateBidsStatus(String statusNew, String timeToDoWishNew, int bidID) {
        Object args[] = {statusNew, timeToDoWishNew, bidID};
        callMainMethod.executeCommandsInsertUpdateDelete(UPDATE_BIDS_STATUS_COMMAND, args);

    }

    //МЕТОД ПОЛУЧЕНИЯ ДАННЫХ ЗАЯВКИ ПО ID ЗАЯВКИ ДЛЯ ЗАНЕСЕНИЯ В ПЛАН РАБОТ:
    @Override
    public Bid findBidByID(int bidID) {
        Object arg[] = {bidID};
        Bid bid = new Bid();
        try {
            TenantDAO tenantDao = new TenantDAO(logger);
            WorkTypeDAO workTypeDao = new WorkTypeDAO(logger);
            ScaleDAO scaleDao = new ScaleDAO(logger);
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_BIDS_BY_ID_FOR_WORK_PLAN_COMMAND, arg);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    bid.setId(resultSet.getInt("IDBid"));
                    Tenant tenant = tenantDao.findTenantByID(resultSet.getInt("Tenant"));
                    bid.setTenant(tenant);
                    WorkType workType = workTypeDao.findWorkTypeByID(resultSet.getInt("WorkType"));
                    bid.setWorkType(workType);
                    Scale workScale = scaleDao.findWorkScaleByID(resultSet.getInt("WorkScale"));
                    bid.setWorkScale(workScale);
                    bid.setTimeToDoWish(resultSet.getString("TimeToDoWish"));
                    bid.setDateOfFilling(resultSet.getString("DateOfFilling"));
                    bid.setStatus(resultSet.getString("Status"));
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
        return bid;
    }
}
