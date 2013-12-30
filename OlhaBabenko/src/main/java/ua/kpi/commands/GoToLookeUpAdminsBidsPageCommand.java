/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static ua.kpi.commands.Command.logger;
import ua.kpi.manager.ConfigurationManager;

/**
 * Комманда перехода на страницу заявок админа при нажатии на кнопку "Просмотр
 * Моих Заявок" страницы заявок всех пользователей
 *
 * @author Оля
 */
public class GoToLookeUpAdminsBidsPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("start GoToLookUpAdminsBidsPage");
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMINS_BIDS_TABLE_PATH);
    }
}
