package ua.kpi.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.manager.ConfigurationManager;

public class NoCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //в случае прямого обращения к контроллеру переадресация на страницу входа
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        return page;
    }
}