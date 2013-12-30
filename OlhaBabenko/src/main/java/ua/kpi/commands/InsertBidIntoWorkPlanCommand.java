package ua.kpi.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static ua.kpi.commands.Command.logger;
import ua.kpi.daoNew.BidDAO;
import ua.kpi.daoNew.BrigadeDAO;
import ua.kpi.daoNew.DAOFactory;
import ua.kpi.daoNew.WorkPlanDAO;
import ua.kpi.manager.ConfigurationManager;
import ua.kpi.manager.MessageManager;

/**
 * Комманда перехода на страницу пользователя (admin/user)
 *
 * @author Оля
 */
public class InsertBidIntoWorkPlanCommand implements Command {

    private static final String PARAM_NAME_BRIGADE = "brigade";
    private static final String PARAM_NAME_TIME_TO_DO = "timeToDo";
    private static final String PARAM_BIDS_ID = "IDBid";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("start InsertBidIntoWorkPlan");
        DAOFactory factory = new DAOFactory(logger);
        BidDAO bidDao = factory.getBidDAO();
        WorkPlanDAO workPlanDao = factory.getWorkPlanDAO();
        BrigadeDAO brigadeDao = factory.getBrigadeDAO();
        String brigade = request.getParameter(PARAM_NAME_BRIGADE);
        String timeToDo = request.getParameter(PARAM_NAME_TIME_TO_DO);
        String bidIDstring = request.getParameter(PARAM_BIDS_ID);
        if (brigade.equals("") || timeToDo.equals("") || bidIDstring.equals("")) {
            request.setAttribute("successMessage",
                    MessageManager.getInstance().getProperty(MessageManager.EMPTY_FIELD_ERROR_MESSAGE));
        } else {
            int bidID = Integer.valueOf(bidIDstring);
            if (workPlanDao.checkBidIdBeforeInsertIntoWorkPlan(bidID)) {
                request.setAttribute("successMessage", MessageManager.getInstance().getProperty(MessageManager.FILLING_INTO_WORK_PLAN_ERROR));
            } else {
                int brigadeID = brigadeDao.selectBrigadeIdByBrigadeName(brigade);
                workPlanDao.insertBidIntoWorkPlan(bidID, brigadeID, timeToDo);
                String statusNew = "принята";
                bidDao.updateBidsStatus(statusNew, timeToDo, bidID);
                request.setAttribute("successMessage", MessageManager.getInstance().getProperty(MessageManager.FILLING_INTO_WORK_PLAN_SUCCESS));
            }
        }
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMINS_INSERT_BIDS_INTO_WORK_PLAN_PATH);
    }
}
