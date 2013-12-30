package ua.kpi.daoNew.interfaces;

import ua.kpi.model.Tenant;

/**
 * Базовый интерфейс для работы с пользователями (таблица Tenants)
 *
 * @author Оля
 */
public interface TenantDaoInterface {

    //МЕТОД ПОЛУЧЕНИЯ ПОЛЬЗОВАТЕЛЯ :
    //на вход : логин потенциального пользователя
    //возвращаемое значение : пользователь с указаным логином (есть в БД); null (нет в БД)
    //результат : проверка наличия пользователя в БД при попытке входа
    public Tenant findTenantByLogin(String login);

    //МЕТОД РЕГИСТРАЦИИ НОВОГО ПОЛЬЗОВАТЕЛЯ :
    //на вход : данные,необходимые для регистрации нового пользователя
    //возвращаемое значение : -
    //результат : регистрация нового пользователя со сатусом USER путём внесения информации о нём в таблицу БД Tenants
    public void registrationOfUser(String lastName, String firstName, String middleName,
            String city, String address, String telephone, String email, String login, String password);

    //МЕТОД ПРОВЕРКИ СУЩЕСТВОВАНИЯ ЛОГИНА В БД :
    //на вход : логин потенциального нового пользователя
    //возвращаемое значение : true(пользователь с таким логином уже существует);false(-//- не существует)
    //результат : недопустимость регистрации пользователей с одинаковыми логинами
    public boolean checkLoginDuringRegistration(String login);

    //МЕТОД ПОИСКА ПОЛЬЗОВАТЕЛЯ ПО ID :
    //на вход : ID пользователя
    //возвращаемое значение : пользователь
    //результат : получение пользователя по ID пользователя
    public Tenant findTenantByID(int tenantID);
}
