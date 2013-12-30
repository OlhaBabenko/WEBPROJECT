package ua.kpi.daoNew;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import ua.kpi.connectionDB.ConnectionDB;
import ua.kpi.daoNew.interfaces.TenantDaoInterface;
import ua.kpi.model.Tenant;

/**
 * Реализация методов для работы с таблицой БД Tenants интерфейса
 * TenantDaoInterface
 *
 * @author Оля
 */
public class TenantDAO extends MainStatements implements TenantDaoInterface {

    public TenantDAO(Logger log) {
        super(log);
    }
    private static final String FIND_TENANT_BY_LOGIN_COMMAND = "SELECT * FROM TENANTS WHERE Login =?";
    private static final String INSERT_NEW_TENANT_AS_USER_COMMAND = "INSERT INTO Tenants(LastName,FirstName,MiddleName,City,Address,Telephone,Email,Login,Password,UserType) "
            + "VALUES (?,?,?,?,?,?,?,?,?,1)";
    private static final String SELECT_TENANT_BY_ID_COMMAND = "SELECT * FROM TENANTS WHERE IDTenant =?";

    //МЕТОД ПОЛУЧЕНИЯ ПОЛЬЗОВАТЕЛЯ :
    @Override
    public Tenant findTenantByLogin(String login) {
        Tenant tenant = null;
        Object args[] = {login};
        try {
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(FIND_TENANT_BY_LOGIN_COMMAND, args);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    tenant = new Tenant();
                    tenant.setId(resultSet.getInt("IDTenant"));
                    tenant.setLastName(resultSet.getString("LastName"));
                    tenant.setFirstName(resultSet.getString("FirstName"));
                    tenant.setMiddleName(resultSet.getString("MiddleName"));
                    tenant.setCity(resultSet.getString("City"));
                    tenant.setAddress(resultSet.getString("Address"));
                    tenant.setTelephone(resultSet.getString("Telephone"));
                    tenant.setEmail(resultSet.getString("Email"));
                    tenant.setLogin(resultSet.getString("Login"));
                    tenant.setPassword(resultSet.getString("Password"));
                    if (resultSet.getInt("UserType") == 1) {
                        tenant.setUserType("User");
                    } else {
                        tenant.setUserType("Admin");
                    }
                }
            } finally {
                preparedStatement = toCloseAfterConnection.getPreparedStatement();
                Connection connection = toCloseAfterConnection.getConnection();
                ConnectionDB.closeConnection(resultSet, preparedStatement, connection);
            }
        } catch (SQLException e) {
            logger.info("SQLException was thrown");
            logger.error("Exception was thrown : ", e);
        }
        return tenant;
    }

    //МЕТОД РЕГИСТРАЦИИ НОВОГО ПОЛЬЗОВАТЕЛЯ :
    @Override
    public void registrationOfUser(String lastName, String firstName, String middleName,
            String city, String address, String telephone, String email, String login, String password) {
        Object args[] = {lastName, firstName, middleName, city, address, telephone, email, login, password};
        callMainMethod.executeCommandsInsertUpdateDelete(INSERT_NEW_TENANT_AS_USER_COMMAND, args);
    }

    //МЕТОД ПРОВЕРКИ СУЩЕСТВОВАНИЯ ЛОГИНА В БД :
    @Override
    public boolean checkLoginDuringRegistration(String login) {
        Object args[] = {login};
        try {
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(FIND_TENANT_BY_LOGIN_COMMAND, args);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                return resultSet.next();
            } finally {
                preparedStatement = toCloseAfterConnection.getPreparedStatement();
                Connection connection = toCloseAfterConnection.getConnection();
                ConnectionDB.closeConnection(resultSet, preparedStatement, connection);
            }
        } catch (SQLException e) {
            logger.info("SQLException was thrown");
            logger.error("Exception was thrown : ", e);
            return true;
        }
    }

    //МЕТОД ПОИСКА ПОЛЬЗОВАТЕЛЯ ПО ID :
    @Override
    public Tenant findTenantByID(int tenantID) {
        Tenant tenant = null;
        Object args[] = {tenantID};
        try {
            ToCloseAfterConnection toCloseAfterConnection = callMainMethod.executeCommandSelect(SELECT_TENANT_BY_ID_COMMAND, args);
            resultSet = toCloseAfterConnection.getResultSet();
            try {
                while (resultSet.next()) {
                    tenant = new Tenant();
                    tenant.setId(resultSet.getInt("IDTenant"));
                    tenant.setLastName(resultSet.getString("LastName"));
                    tenant.setFirstName(resultSet.getString("FirstName"));
                    tenant.setMiddleName(resultSet.getString("MiddleName"));
                    tenant.setCity(resultSet.getString("City"));
                    tenant.setAddress(resultSet.getString("Address"));
                    tenant.setTelephone(resultSet.getString("Telephone"));
                    tenant.setEmail(resultSet.getString("Email"));
                    tenant.setLogin(resultSet.getString("Login"));
                    tenant.setPassword(resultSet.getString("Password"));
                    if (resultSet.getInt("UserType") == 1) {
                        tenant.setUserType("User");
                    } else {
                        tenant.setUserType("Admin");
                    }
                }
            } finally {
                preparedStatement = toCloseAfterConnection.getPreparedStatement();
                Connection connection = toCloseAfterConnection.getConnection();
                ConnectionDB.closeConnection(resultSet, preparedStatement, connection);
            }
        } catch (SQLException e) {
            logger.info("SQLException was thrown");
            logger.error("Exception was thrown : ", e);
        }
        return tenant;
    }
}