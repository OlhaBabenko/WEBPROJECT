package ua.kpi.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.daoNew.BidDAO;
import ua.kpi.daoNew.DAOFactory;
import ua.kpi.daoNew.ScaleDAO;
import ua.kpi.daoNew.TenantDAO;
import ua.kpi.daoNew.WorkTypeDAO;
import ua.kpi.manager.ConfigurationManager;
import ua.kpi.manager.MessageManager;
import ua.kpi.model.Tenant;

/**
 * Комманда внесения оформленой заявки : проверка данных в оформленой заявке,
 * переход на страницу заявок пользователя (admin/user) или на страницу
 * оформления заявок (проверка прошла \ не прошла)
 *
 * @author Оля
 */
public class BidsDoingCommand implements Command {

    private static final String PARAM_NAME_WORK_TYPE = "workType";
    private static final String PARAM_NAME_WORK_SCALES = "workScale";
    private static final String PARAM_NAME_TIME_TO_DO_WISH = "timeToDoWish";
    private static final String PARAM_NAME_DATE_OF_FILLING = "dateOfFilling";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("start BidsDoingCommand");
        String page, pagePath;

        HttpSession session = request.getSession(false);

        String tenantLogin = (String) session.getAttribute("login");

        int tenantID = Integer.valueOf(session.getAttribute("idTenant").toString());
        DAOFactory factory = new DAOFactory(logger);
        TenantDAO tenantDao = factory.getTenantDAO();
        BidDAO bidDao = factory.getBidDAO();
        WorkTypeDAO workTypeDao = factory.getWorkTypeDAO();
        ScaleDAO scaleDao = factory.getScaleDAO();

        Tenant tenant = tenantDao.findTenantByLogin(tenantLogin);

        //извлечение из запроса всех данных для оформления заявки
        String workType = request.getParameter(PARAM_NAME_WORK_TYPE);
        String workScale = request.getParameter(PARAM_NAME_WORK_SCALES);
        String timeToDoWish = request.getParameter(PARAM_NAME_TIME_TO_DO_WISH);
        String dateOfFilling = (String) session.getAttribute(PARAM_NAME_DATE_OF_FILLING);

        //------проверка данных, полученых из запроса------------
        //проверка на наличие пустых полей:
        if (tenant == null || workType.equals("-") || workScale.equals("-") || dateOfFilling.equals("")) {
            //уведомление об ошибке для пользователя
            request.setAttribute("errorMessage",
                    MessageManager.getInstance().getProperty(MessageManager.EMPTY_FIELD_ERROR_MESSAGE));
            //определение перехода на страницу оформления заявки
            pagePath = ConfigurationManager.BIDS_DOING_PAGE_PATH;
        } else {
            int workTypeID = workTypeDao.selectWorkTypeID(workType);
            int workScaleID = scaleDao.selectWorkScaleID(workScale);
            String status = "новая";
            //-----------вставка данных в БД-------------------------
            //вставка данных в БД (отправление заявки)
            bidDao.insertUserIntoBids(tenantID, workTypeID, workScaleID, timeToDoWish, dateOfFilling, status);
            //определение перехода на страницу входа
            //уведомление об успешном оформлении заявок для пользователя
            request.setAttribute("SuccessBidsDoingMessage",
                    MessageManager.getInstance().getProperty(MessageManager.SUCCESS_BIDS_DOING_MESSAGE));
            pagePath = ConfigurationManager.USERS_BIDS_PAGE_PATH;
            //--------------------------------------------------------
        }
        page = ConfigurationManager.getInstance().getProperty(pagePath);
        return page;
    }
}
