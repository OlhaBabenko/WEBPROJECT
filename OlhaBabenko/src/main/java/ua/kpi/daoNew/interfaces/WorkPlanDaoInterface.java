package ua.kpi.daoNew.interfaces;

import java.util.List;
import ua.kpi.model.WorkPlan;

/**
 * Базовый интерфейс для работы с заявками пользователей (таблица Bids)
 *
 * @author Оля
 */
public interface WorkPlanDaoInterface {

    //МЕТОД ВСТАВКИ ЗАЯВКИ В ПЛАН РАБОТ:
    //на вход : значения, которые надо занести в таблицу БД WorkPlan
    //возвращаемое значение : -
    //результат : занесение в таблицу WorkPlan нужных данных о заявке конкретного пользователя
    public void insertBidIntoWorkPlan(int bidID, int brigadeID, String timeToDo);

    //МЕТОД ПРОВЕРКИ НАЛИЧИЯ ЗАЯВКИ В ПЛАНЕ РАБОТ:
    //на вход : ID заявки
    //возвращаемое значение : true(заявка с таки ID уже есть в БД); false(-\\-нету в БД)
    //результат : недопустимость повторного занесения заявки в план работ
    public boolean checkBidIdBeforeInsertIntoWorkPlan(int bidId);

    //МЕТОД ПОЛУЧЕНИЯ СПИСКА ЗАЯВОК, КОТОРЫЕ СОДЕРЖАТСЯ В ПЛАНЕ РАБОТ:
    //на вход : -
    //возвращаемое значение : список заявок с плана работ
    //результат : получение списка заявок с плана работ для вывода плана работ на страницу админа
    public List<WorkPlan> makeBidsListForWorkPlan();
}
