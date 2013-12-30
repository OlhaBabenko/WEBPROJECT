package ua.kpi.manager;

import java.util.ResourceBundle;

public class MessageManager {

    private static MessageManager instance;
    private ResourceBundle resourceBundle;
    //класс извлекает информацию из файла messages.properties
    private static final String BUNDLE_NAME = "manager.messages";
    public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "SERVLET_EXCEPTION_ERROR_MESSAGE";
    public static final String IO_EXCEPTION_ERROR_MESSAGE = "IO_EXCEPTION_ERROR_MESSAGE";
    public static final String DAO_ERROR_MESSAGE = "DAO_ERROR_MESSAGE";
    public static final String LOGIN_DUPLICATE_ERROR_MESSAGE = "LOGIN_DUPLICATE_ERROR_MESSAGE";
    public static final String EMPTY_FIELD_ERROR_MESSAGE = "EMPTY_FIELD_ERROR_MESSAGE";
    public static final String EMAIL_ERROR_MESSAGE = "EMAIL_ERROR_MESSAGE";
    public static final String SUCCESS_BIDS_DOING_MESSAGE = "SUCCESS_BIDS_DOING_MESSAGE";
    public static final String MUST_BE_FILL_IN = "MUST_BE_FILL_IN";
    public static final String FILLING_INTO_WORK_PLAN_ERROR = "FILLING_INTO_WORK_PLAN_ERROR";
    public static final String FILLING_INTO_WORK_PLAN_SUCCESS = "FILLING_INTO_WORK_PLAN_SUCCESS";

    public static MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}