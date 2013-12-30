package ua.kpi.daoNew.interfaces;

import java.util.List;
import ua.kpi.model.Brigade;

/**
 * Базовый интерфейс для работы с бригадами (таблица Brigades)
 *
 * @author Оля
 */
public interface BrigadeDaoInterface {

    //МЕТОД ПОЛУЧЕНИЯ СПИСКА БРИГАД ДЛЯ ВЫПАДАЮЩЕГО МЕНЮ ПРИ ЗАПОЛНЕНИИ ПЛАНА РАБОТ :
    //на вход : -
    //возвращаемое значение : список бригад
    //результат : заполненый выпадающий список выбора бригады при оформлении плана работ
    public List< String> selectBrigadesForUsersChoice();

    //МЕТОД ПОЛУЧЕНИЯ ID БРИГАДЫ ПО ТИПУ БРИГАДЫ :
    //на вход : название типа бригады
    //возвращаемое значение : ID бригады
    //результат : получение ID бригады по типу бригады
    public int selectBrigadeIdByBrigadeName(String brigade);

    //МЕТОД ПОЛУЧЕНИЯ БРИГАДЫ ПО ID БРИГАДЫ ДЛЯ ЗАПОЛЕНИЯ ПЛАНА РАБОТ :
    //на вход : ID бригады
    //возвращаемое значение : бригада
    //результат : получение бригады по ID бригады
    public Brigade findBrigadeByID(int brigadeID);
}
