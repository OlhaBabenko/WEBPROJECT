package ua.kpi.commands;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.daoNew.BrigadeDAO;
import ua.kpi.daoNew.DAOFactory;
import ua.kpi.manager.ConfigurationManager;

/**
 * Комманда перехода на страницу внесения заявок в план работ : получение списка
 * заявок для выпадающего меню,переход на страницу внесения заявок
 *
 * @author Оля
 */
public class GoToAdminsWorkPlanDoing implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        logger.info("start GoToAdminsWorkPlanDoing");
        DAOFactory factory = new DAOFactory(logger);
        BrigadeDAO brigadeDao = factory.getBrigadeDAO();
        List<String> brigadeList = brigadeDao.selectBrigadesForUsersChoice();
        session.setAttribute("brigadesList", brigadeList);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMINS_INSERT_BIDS_INTO_WORK_PLAN_PATH);
    }
}
