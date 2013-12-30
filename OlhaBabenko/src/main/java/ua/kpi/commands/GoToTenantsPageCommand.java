package ua.kpi.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static ua.kpi.commands.Command.logger;
import ua.kpi.manager.ConfigurationManager;

/**
 * Комманда перехода на страницу пользователя (admin/user)
 *
 * @author Оля
 */
public class GoToTenantsPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("start GoToTenantsPageCommand");
        String pagePath;
        HttpSession session = request.getSession(false);
        String userType = (String) session.getAttribute("UserType");
        if (userType.equals("User")) {
            pagePath = ConfigurationManager.USERS_PAGE_PATH;
        } else {
            pagePath = ConfigurationManager.ADMINS_PAGE_PATH;
        }
        return ConfigurationManager.getInstance().getProperty(pagePath);
    }
}
