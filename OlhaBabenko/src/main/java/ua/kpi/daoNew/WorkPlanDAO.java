package ua.kpi.daoNew;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import ua.kpi.connectionDB.ConnectionDB;
import ua.kpi.daoNew.interfaces.WorkPlanDaoInterface;
import ua.kpi.model.Bid;
import ua.kpi.model.Brigade;
import ua.kpi.model.WorkPlan;

/**
 * Реализация методов для работы с таблицой БД WorkPlan интерфейса
 * WorkPlanDAOInterface
 *
 * @author Оля
 */
public class WorkPlanDAO extends MainStatements implements WorkPlanDaoInterface {

    public WorkPlanDAO(Logger log) {
        super(log);
    }
    private static final String INSERT_BID_INTO_WORK_PLAN = "INSERT INTO WORKPLAN(Bid,Brigade,TimeToDo) "
            + "VALUES (?,?,?)";
    private static final String FIND_BID_BY_BID_ID_IN_WORK_PLAN_COMMAND = "SELECT * FROM WORKPLAN WHERE BID =?";
    private static final String SELECT_BIDS_FOR_WORK_PLAN_COMMAND = "SELECT * FROM WORKPLAN";

    //МЕТОД ВСТАВКИ ЗАЯВКИ В ПЛАН РАБОТ:
    @Override
    public void insertBidIntoWorkPlan(int bidID, int brigadeID, String timeToDo) {
        Object args[] = {bidID, brigadeID, timeToDo};
        callMainMethod.executeCommandsInsertUpdateDelete(INSERT_BID_INTO_WORK_PLAN, args);
    }

    //МЕТОД ПРОВЕРКИ НАЛИЧИЯ ЗАЯВКИ В ПЛАНЕ РАБОТ:
    @Override
    public boolean checkBidIdBeforeInsertIntoWorkPlan(int bidId) {
        Object args[] = {bidId};
        try {
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(FIND_BID_BY_BID_ID_IN_WORK_PLAN_COMMAND, args);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                return resultSet.next();
            } finally {
                preparedStatement = toCloseAfterConnection.getPreparedStatement();
                Connection connection = toCloseAfterConnection.getConnection();
                ConnectionDB.closeConnection(resultSet, preparedStatement, connection);
            }
        } catch (SQLException e) {
            logger.info("SQLException was thrown");
            logger.error("Exception was thrown : ", e);
            return true;
        }
    }

    //МЕТОД ПОЛУЧЕНИЯ СПИСКА ЗАЯВОК, КОТОРЫЕ СОДЕРЖАТСЯ В ПЛАНЕ РАБОТ:
    @Override
    public List<WorkPlan> makeBidsListForWorkPlan() {
        List<WorkPlan> listOfWorks = new LinkedList();
        try {
            BidDAO bidDao = new BidDAO(logger);
            BrigadeDAO brigadeDao = new BrigadeDAO(logger);
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_BIDS_FOR_WORK_PLAN_COMMAND);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    WorkPlan work = new WorkPlan();
                    work.setId(resultSet.getInt("IDWork"));
                    Bid bid = bidDao.findBidByID(resultSet.getInt("Bid"));
                    work.setBid(bid);
                    Brigade brigade = brigadeDao.findBrigadeByID(resultSet.getInt("Brigade"));
                    brigade.setId(resultSet.getInt("Brigade"));
                    work.setBrigade(brigade);
                    work.setTimeToDo(resultSet.getString("TimeToDo"));
                    listOfWorks.add(work);
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
        return listOfWorks;
    }
}
