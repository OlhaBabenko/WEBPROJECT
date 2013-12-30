package ua.kpi.daoNew.interfaces;

import java.util.List;
import ua.kpi.model.WorkType;

/**
 * Базовый интерфейс для работы с масштабами работ (таблица Scales)
 *
 * @author Оля
 */
public interface WorkTypeDaoInterface {

    //МЕТОД ПОЛУЧЕНИЯ СПИСКА ТИПОВ РАБОТ ДЛЯ ВЫПАДАЮЩЕГО МЕНЮ ПРИ ОФОРМЛЕНИИ ЗАЯВКИ :
    //на вход : -
    //возвращаемое значение : список типов работ
    //результат : заполненый выпадающий список выбора типа работ при оформлении заявки
    public List<String> selectWorkTypeForUsersChoice();

    //МЕТОД ПОЛУЧЕНИЯ ID ТИПА РАБОТ ДЛЯ ВНЕСЕНИЯ ЗАЯВКИ В ТАБЛИЦУ BIDS ПРИ ОФОРМЛЕНИИ ЗАЯВКИ :
    //на вход : название типа работ
    //возвращаемое значение : ID типа работ
    //результат : получение ID типа работ для внесения заявки в таблицу BIDS при оформлении заявки
    public int selectWorkTypeID(String workScale);

    //МЕТОД ПОЛУЧЕНИЯ ТИПА РАБОТ ПО ID ТИПА РАБОТ :
    //на вход : ID типа работ
    //возвращаемое значение : тип работ
    //результат : тип работ
    public WorkType findWorkTypeByID(int workScaleID);
}
