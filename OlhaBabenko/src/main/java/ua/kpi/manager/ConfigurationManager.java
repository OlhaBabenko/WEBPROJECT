package ua.kpi.manager;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;
    //класс извлекает информацию из файла config.properties
    private static final String BUNDLE_NAME = "manager.config";
    public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";
    public static final String DATABASE_URL = "DATABASE_URL";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    /*страницы : входа,регистрации (общие для admin, user)*/
    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String REGISTRATION_PAGE_PATH = "REGISTRATION_PAGE_PATH";
    public static final String BIDS_DOING_PAGE_PATH = "BIDS_DOING_PAGE_PATH";
    /*страницы user : главная,просмотра своих заявок,оформления заявки*/
    public static final String USERS_PAGE_PATH = "USERS_PAGE_PATH";
    public static final String USERS_BIDS_PAGE_PATH = "USERS_BIDS_PAGE_PATH";
    /*страницы admin : главная,просмотра заявок всех пользователей,оформления заявки*/
    public static final String ADMINS_PAGE_PATH = "ADMINS_PAGE_PATH";
    public static final String ADMINS_BIDS_PAGE_PATH = "ADMINS_BIDS_PAGE_PATH";
    public static final String ADMINS_BIDS_TABLE_PATH = "ADMINS_BIDS_TABLE_PATH";
    public static final String ADMINS_INSERT_BIDS_INTO_WORK_PLAN_PATH="ADMINS_INSERT_BIDS_INTO_WORK_PLAN_PATH";

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}