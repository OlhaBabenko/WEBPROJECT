package ua.kpi.jspservlet;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import ua.kpi.commands.BidsDoingCommand;
import ua.kpi.commands.Command;
import ua.kpi.commands.GoToAdminsWorkPlanDoing;
import ua.kpi.commands.GoToBidsDoingPageCommand;
import ua.kpi.commands.LoginCommand;
import ua.kpi.commands.RegistrationCommand;
import ua.kpi.commands.NoCommand;
import ua.kpi.commands.GoToRegistrationPageCommand;
import ua.kpi.commands.GoToTenantsPageCommand;
import ua.kpi.commands.GoToBidsPageCommand;
import ua.kpi.commands.GoToLookeUpAdminsBidsPageCommand;
import ua.kpi.commands.InsertBidIntoWorkPlanCommand;
import ua.kpi.commands.LocaleCommand;
import ua.kpi.commands.LocaleCommandRU;
import ua.kpi.commands.RenewTenantsBidsTableCommand;
import ua.kpi.commands.RenewWorkPlanTableCommand;

public class RequestHelper {

    private static RequestHelper instance = null;
    HashMap<String, Command> commands = new HashMap<>();

    private RequestHelper() {
        //заполнение таблицы командами
        //комманди для страницы входа, регистрации (общие для admin,user)
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("goToRegistrationPage", new GoToRegistrationPageCommand());
        //команди для переходов user
        commands.put("goToTenantsPage", new GoToTenantsPageCommand());
        commands.put("goToBidsPage", new GoToBidsPageCommand());
        commands.put("renewTenantsBidsTable", new RenewTenantsBidsTableCommand());

        commands.put("goToBidsDoingPage", new GoToBidsDoingPageCommand());
        commands.put("bidsDoing", new BidsDoingCommand());
        commands.put("renewWorkPlanTable", new RenewWorkPlanTableCommand());

        commands.put("goToLookeUpAdminsBidsPage", new GoToLookeUpAdminsBidsPageCommand());
        commands.put("goToWorkPlanInsertingPage", new GoToAdminsWorkPlanDoing());
        commands.put("InsertIntoWorkPlan", new InsertBidIntoWorkPlanCommand());
        commands.put("LocaleCommand", new LocaleCommand());
        commands.put("LocaleCommandRU", new LocaleCommandRU());
    }

    public Command getCommand(HttpServletRequest request) {
        //извлечение команды из запроса
        String action = request.getParameter("command");
        //получение объекта, соответствующего команде
        Command command = commands.get(action);
        if (command == null) {
            //если команды не существует в текущем объекте
            command = new NoCommand();
        }
        return command;
    }
    //создание единственного объекта по шаблону Singleton

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
