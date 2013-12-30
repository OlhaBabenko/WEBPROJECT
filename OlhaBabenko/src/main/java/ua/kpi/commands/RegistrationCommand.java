package ua.kpi.commands;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.daoNew.DAOFactory;
import ua.kpi.daoNew.TenantDAO;
import ua.kpi.manager.ConfigurationManager;
import ua.kpi.manager.MessageManager;

/**
 * Комманда регистрации
 *
 * @author Оля
 */
public class RegistrationCommand implements Command {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_FIRSTNAME = "firstName";
    private static final String PARAM_NAME_MIDDLENAME = "middleName";
    private static final String PARAM_NAME_LASTNAME = "lastName";
    private static final String PARAM_NAME_CITY = "city";
    private static final String PARAM_NAME_ADDRESS = "address";
    private static final String PARAM_NAME_TELEPHONE = "telephone";
    private static final String PARAM_NAME_EMAIL = "email";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page, pagePath;
        logger.info("RegistrationCommand");
        DAOFactory factory = new DAOFactory(logger);
        TenantDAO tenantDao = factory.getTenantDAO();

        //извлечение из запроса всех данных о пользователе
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String firstName = request.getParameter(PARAM_NAME_FIRSTNAME);
        String middleName = request.getParameter(PARAM_NAME_MIDDLENAME);
        String lastName = request.getParameter(PARAM_NAME_LASTNAME);
        String city = request.getParameter(PARAM_NAME_CITY);
        String address = request.getParameter(PARAM_NAME_ADDRESS);
        String telephone = request.getParameter(PARAM_NAME_TELEPHONE);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        //------проверка данных, полученых из запроса-----------------
        //проверка на наличие пустых полей:
        if (login.equals("") || password.equals("") || firstName.equalsIgnoreCase("")
                || lastName.equals("") || city.equals("")
                || address.equalsIgnoreCase("") || telephone.equals("")) {
            //уведомление об ошибке для пользователя
            request.setAttribute("errorMessage",
                    MessageManager.getInstance().getProperty(MessageManager.EMPTY_FIELD_ERROR_MESSAGE));
            //определение перехода на страницу регистрации
            pagePath = ConfigurationManager.REGISTRATION_PAGE_PATH;
        } else {
            //шаблон для e-mail
            String regex = "^[\\w_\\-.]+@[\\w\\-]+\\.[\\w\\-.]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            String error;
            //проверка на существование логина в БД
            //+ проверка на соответствие е-mail шаблону
            if (tenantDao.checkLoginDuringRegistration(login) || !matcher.find()) {
                if (!matcher.find()) {
                    error = MessageManager.EMAIL_ERROR_MESSAGE;
                } else {
                    error = MessageManager.LOGIN_DUPLICATE_ERROR_MESSAGE;
                }
                //уведомление об ошибке для пользователя
                request.setAttribute("errorMessage",
                        MessageManager.getInstance().getProperty(error));
                //определение перехода на страницу регистрации
                pagePath = ConfigurationManager.REGISTRATION_PAGE_PATH;
            } else {
                tenantDao.registrationOfUser(lastName, firstName, middleName, city, address, telephone, email, login, password);
                //определение перехода на страницу входа
                pagePath = ConfigurationManager.LOGIN_PAGE_PATH;
            }
        }
        page = ConfigurationManager.getInstance().getProperty(pagePath);
        return page;
    }
}
