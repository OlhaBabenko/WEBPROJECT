/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.commands;

import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static ua.kpi.commands.Command.logger;
import ua.kpi.locale.MenuLocale;
import ua.kpi.manager.ConfigurationManager;

/**
 * Комманда локализации для меню
 *
 * @author Оля
 */
public class LocaleCommandRU implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("start LocaleCommand");
        //ПЕРЕХОД НА СТРАНИЦУ ПОЛЬЗОВАТЕЛЯ
        String pagePath;
        HttpSession session = request.getSession(false);
        ResourceBundle resourceBundle = MenuLocale.doLocale("ru","RU");
        session.setAttribute("MyPage", resourceBundle.getString(MenuLocale.MY_PAGE));
        session.setAttribute("WorkTypes", resourceBundle.getString(MenuLocale.WORK_TYPES));
        session.setAttribute("Price", resourceBundle.getString(MenuLocale.PRICE));
        session.setAttribute("Bids", resourceBundle.getString(MenuLocale.BIDS));
        session.setAttribute("Contacts", resourceBundle.getString(MenuLocale.CONTACTS));

        String userType = (String) session.getAttribute("UserType");
        if (userType.equals("User")) {
            pagePath = ConfigurationManager.USERS_PAGE_PATH;
        } else {
            pagePath = ConfigurationManager.ADMINS_PAGE_PATH;
        }
        String page = ConfigurationManager.getInstance().getProperty(pagePath);
        return page;
    }
}