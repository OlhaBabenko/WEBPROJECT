/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.locale;

import java.util.*;

public class MenuLocale {

    private static MenuLocale instance;
    private ResourceBundle resourceBundle;
    //класс извлекает информацию из файла messages.properties
    private static final String BUNDLE_NAME = "locale.menu";
    public static final String MY_PAGE = "MY_PAGE";
    public static final String WORK_TYPES = "WORK_TYPES";
    public static final String PRICE = "PRICE";
    public static final String BIDS = "BIDS";
    public static final String CONTACTS = "CONTACTS";
    public static String language;
    public static String country;

    public static ResourceBundle doLocale(String language, String country) {
        Locale currentLocale;
        ResourceBundle messages;
        currentLocale = new Locale(language, country);
        messages = ResourceBundle.getBundle(BUNDLE_NAME, currentLocale);
        return messages;
    }

    public static MenuLocale getInstance() {
        if (instance == null) {
            instance = new MenuLocale();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
