package ua.kpi.commands;

import java.io.IOException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.daoNew.DAOFactory;
import ua.kpi.daoNew.ScaleDAO;
import ua.kpi.daoNew.WorkTypeDAO;
import ua.kpi.manager.ConfigurationManager;

/**
 * Комманда перехода на страницу оформления заявок : получение списков типов
 * работ, масштабов работ для выпадающего меню,получения текущей даты, переход
 * на страницу внесения заявок, переход на страницу оформления
 *
 * @author Оля
 */
public class GoToBidsDoingPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //формируем приветсвие для вывода на странице
        //-----получение необходимых данных для страницы---------
        //данные для списка типа работ :
        logger.info("start GoToBidsDoing");
        HttpSession session = request.getSession(false);
        DAOFactory factory = new DAOFactory(logger);
        WorkTypeDAO workTypeDao = factory.getWorkTypeDAO();
        ScaleDAO scaleDao = factory.getScaleDAO();
        List<String> workTypesList = workTypeDao.selectWorkTypeForUsersChoice();
        session.setAttribute("workTypeList", workTypesList);
        //данные для списка масштаба работ :
        List<String> workScalesList = scaleDao.selectWorkScaleForUsersChoice();
        session.setAttribute("workScaleList", workScalesList);
        //данные для даты заполнения
        Formatter f = new Formatter();
        Calendar calendar = Calendar.getInstance();
        session.setAttribute("dateOfFilling", (f.format("%td-%tm-%tY", calendar, calendar, calendar)).toString());
        //-------------------------------------------------------

        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.BIDS_DOING_PAGE_PATH);

    }
}
