package ua.kpi.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static ua.kpi.commands.Command.logger;
import ua.kpi.manager.ConfigurationManager;

/**
 * Комманда перехода на страницу регистрации
 *
 * @author Оля
 */
public class GoToRegistrationPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("start GoToRegistrationPage");
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.REGISTRATION_PAGE_PATH);
    }
}
