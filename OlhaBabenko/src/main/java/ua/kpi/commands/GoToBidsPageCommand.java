package ua.kpi.commands;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static ua.kpi.commands.Command.logger;
import ua.kpi.daoNew.BidDAO;
import ua.kpi.daoNew.DAOFactory;
import ua.kpi.manager.ConfigurationManager;
import ua.kpi.model.Bid;

/**
 * Комманда перехода на страницу заявок пользователя при нажатии в меню на пункт
 * "Заявки": получение типа пользователя,переход на страницу заявок пользователя
 * (admin/user)
 * (ADMIN) - + получение списка всех новых заявок
 *
 * @author Оля
 */
public class GoToBidsPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("start GoToBidsPage");
        String pagePath;
        HttpSession session = request.getSession(false);
        DAOFactory factory = new DAOFactory(logger);
        BidDAO bidDao = factory.getBidDAO();
        String userType = (String) session.getAttribute("UserType");
        if (userType.equals("User")) {
            pagePath = ConfigurationManager.USERS_BIDS_PAGE_PATH;
        } else {
            pagePath = ConfigurationManager.ADMINS_BIDS_PAGE_PATH;
            //получаем список заявок для пользователя (user/admin)
            String status = "новая";
            List<Bid> listOfBids = bidDao.makeBidsListForBidsTableOfAllNewBids(status);
            session.setAttribute("listOfNewBids", listOfBids);
        }
        return ConfigurationManager.getInstance().getProperty(pagePath);

    }
}
