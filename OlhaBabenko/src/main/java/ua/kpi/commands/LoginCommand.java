package ua.kpi.commands;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.daoNew.BidDAO;
import ua.kpi.daoNew.DAOFactory;
import ua.kpi.daoNew.TenantDAO;
import ua.kpi.daoNew.WorkPlanDAO;
import ua.kpi.locale.MenuLocale;
import ua.kpi.manager.ConfigurationManager;
import ua.kpi.manager.MessageManager;
import ua.kpi.model.Bid;
import ua.kpi.model.Tenant;
import ua.kpi.model.WorkPlan;

/**
 * Комманда входа : ввод логина, проверка логина, возможность перехода на
 * страницу регистрации, получение данных о пользователе+ о заявках
 * пользователя,получение данных для меню (локализация), переход на страницу
 * пользователя (admin/user)
 *
 * @author Оля
 */
public class LoginCommand implements Command {

    public static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("start LoginCommand");
        String page, userType, pagePath = null;

        //извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        HttpSession session = request.getSession(false);

        DAOFactory factory = new DAOFactory(logger);
        TenantDAO tenantDao = factory.getTenantDAO();
        BidDAO bidDao = factory.getBidDAO();
        WorkPlanDAO workPlanDao = factory.getWorkPlanDAO();

        Tenant tenant = tenantDao.findTenantByLogin(login);

        //данные для заполнения меню (для локализации)
        ResourceBundle resourceBundle = MenuLocale.doLocale("ru", "RU");
        session.setAttribute("MyPage", resourceBundle.getString(MenuLocale.MY_PAGE));
        session.setAttribute("WorkTypes", resourceBundle.getString(MenuLocale.WORK_TYPES));
        session.setAttribute("Price", resourceBundle.getString(MenuLocale.PRICE));
        session.setAttribute("Bids", resourceBundle.getString(MenuLocale.BIDS));
        session.setAttribute("Contacts", resourceBundle.getString(MenuLocale.CONTACTS));

        // проверка существования логина в БД
        if (tenant != null && tenant.getPassword().equals(password)) {
            session.setAttribute("login", tenant.getLogin());
            session.setAttribute("user", tenant);
            session.setAttribute("idTenant", tenant.getId());
            //получаем список заявок для таблицы заявок пользователя (user/admin)
            List<Bid> listOfBids = bidDao.makeBidsListForBidsTableOfTenant(tenant.getId());
            session.setAttribute("datalist", listOfBids);
            //формирование приветсвия
            session.setAttribute("welcomeMessage", tenant.getFirstName() + " " + tenant.getMiddleName() + ", добро пожаловать!");
            //--определение типа пользователя (user,admin)--
            userType = tenant.getUserType();
            session.setAttribute("UserType", userType);
            if (userType.equals("User")) {
                session.setAttribute("pageType", "User's Page");
                session.setAttribute("pageCommand", "goToUsersPage");
                session.setAttribute("LastFirstMiddleName", tenant.getLastName() + " " + tenant.getFirstName() + " " + tenant.getMiddleName());
                session.setAttribute("Address", "г. " + tenant.getCity() + ", " + tenant.getAddress());
                session.setAttribute("Telephone", tenant.getTelephone());
                session.setAttribute("Email", tenant.getEmail());
                //определение перехода на страницу пользователя-user
                pagePath = ConfigurationManager.USERS_PAGE_PATH;
            }
            if (userType.equals("Admin")) {
                session.setAttribute("pageType", "Admin's Page");
                List<WorkPlan> listOfBidsInWorkPlan = workPlanDao.makeBidsListForWorkPlan();
                session.setAttribute("listOfBidsInWorkPlan", listOfBidsInWorkPlan);
                //определение перехода на страницу пользователя-admin
                pagePath = ConfigurationManager.ADMINS_PAGE_PATH;
            }
        } else {
            //уведомление об ошибке для пользователя
            request.setAttribute("errorMessage",
                    MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            //определение перехода на страницу входа
            pagePath = ConfigurationManager.LOGIN_PAGE_PATH;
        }
        //переходим на указанную страницу
        page = ConfigurationManager.getInstance().getProperty(pagePath);
        return page;
    }
}
