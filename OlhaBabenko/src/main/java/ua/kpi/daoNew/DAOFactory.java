package ua.kpi.daoNew;

import org.apache.log4j.Logger;
import ua.kpi.logger.Log4JInitServlet;

/**
 * Получение объектов для работы с DAO
 *
 * @author Оля
 */
public class DAOFactory {

    static Logger logger = Logger.getLogger(DAOFactory.class);

    public DAOFactory(Logger log) {
        logger = log;
    }

    public TenantDAO getTenantDAO() {
        return new TenantDAO(logger);
    }

    public BidDAO getBidDAO() {
        return new BidDAO(logger);
    }

    public BrigadeDAO getBrigadeDAO() {
        return new BrigadeDAO(logger);
    }

    public ScaleDAO getScaleDAO() {
        return new ScaleDAO(logger);
    }

    public WorkPlanDAO getWorkPlanDAO() {
        return new WorkPlanDAO(logger);
    }

    public WorkTypeDAO getWorkTypeDAO() {
        return new WorkTypeDAO(logger);
    }
}
