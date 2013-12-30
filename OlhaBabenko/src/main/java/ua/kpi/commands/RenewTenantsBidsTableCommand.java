package ua.kpi.commands;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.daoNew.BidDAO;
import ua.kpi.daoNew.DAOFactory;
import ua.kpi.manager.ConfigurationManager;
import ua.kpi.model.Bid;

/**
 * Комманда обновления таблицу заявок пользователей
 *
 * @author Оля
 */
public class RenewTenantsBidsTableCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pagePath;
        HttpSession session = request.getSession(false);
        logger.info("RenewTenantsBidsTableCommand");
        DAOFactory factory = new DAOFactory(logger);
        BidDAO bidDao = factory.getBidDAO();
        int tenantId = Integer.valueOf(session.getAttribute("idTenant").toString());
        List<Bid> listOfBids = bidDao.makeBidsListForBidsTableOfTenant(tenantId);
        session.setAttribute("datalist", listOfBids);
        // session.setAttribute("Bid1", listOfBids.get(0));
        String userType = (String) session.getAttribute("UserType");
        if (userType.equals("User")) {
            pagePath = ConfigurationManager.USERS_BIDS_PAGE_PATH;
        } else {
            pagePath = ConfigurationManager.ADMINS_BIDS_TABLE_PATH;
        }
        return ConfigurationManager.getInstance().getProperty(pagePath);
    }
}