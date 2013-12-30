package ua.kpi.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import org.apache.log4j.Logger;
import ua.kpi.jspservlet.Controller;

public interface Command {

    static Logger logger = Logger.getLogger(Controller.class);

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
