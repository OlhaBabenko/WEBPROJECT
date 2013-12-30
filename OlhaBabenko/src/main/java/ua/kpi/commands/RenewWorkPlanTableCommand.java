package ua.kpi.commands;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.daoNew.DAOFactory;
import ua.kpi.daoNew.WorkPlanDAO;
import ua.kpi.manager.ConfigurationManager;
import ua.kpi.model.WorkPlan;

/**
 * Комманда обновления плана работ
 *
 * @author Оля
 */
public class RenewWorkPlanTableCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pagePath;
        HttpSession session = request.getSession(false);
        logger.info("RenewWorkPlanTableCommand");
        DAOFactory factory = new DAOFactory(logger);
        WorkPlanDAO workPlanDao = factory.getWorkPlanDAO();
        List<WorkPlan> listOfBidsInWorkPlan = workPlanDao.makeBidsListForWorkPlan();
        session.setAttribute("listOfBidsInWorkPlan", listOfBidsInWorkPlan);
        pagePath = ConfigurationManager.ADMINS_PAGE_PATH;
        return ConfigurationManager.getInstance().getProperty(pagePath);
    }
}
