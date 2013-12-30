package ua.kpi.jspservlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.kpi.commands.Command;
import ua.kpi.manager.ConfigurationManager;
import ua.kpi.manager.MessageManager;

public class Controller extends HttpServlet implements javax.servlet.Servlet {

    //объект, содержащий список возможных команд
    RequestHelper requestHelper = RequestHelper.getInstance();
    static Logger logger = Logger.getLogger(Controller.class);

    public Controller() {
        super();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        try {
            //определение команды, пришедшей из JSP
            Command command = requestHelper.getCommand(request);
            /*вызов реализованного метода execute() интерфейса Command и передача параметров
             классу-обработчику конкретной команды*/
            page = command.execute(request, response);
            //метод возвращает страницу ответа
        } catch (ServletException e) {
            logger.info("ServletException was thrown");
            logger.error("Exception was thrown:", e);
            //генерация сообщения об ошибке
            request.setAttribute("errorMessage",
                    MessageManager.getInstance().getProperty(
                    MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
            //вызов JSP-страницы с сообщением об ошибке
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } catch (IOException e) {
            logger.info("IOException was thrown");
            logger.error("Exception was thrown:", e);
            request.setAttribute("errorMessage",
                    MessageManager.getInstance().getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        //вызов страницы ответа на запрос
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}