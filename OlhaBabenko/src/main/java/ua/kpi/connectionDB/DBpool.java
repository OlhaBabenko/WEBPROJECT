package ua.kpi.connectionDB;

import java.sql.SQLException;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ua.kpi.jspservlet.Controller;

/*
 * Соединение с пулом соединений 
 * Использует шаблон Singleton для невозможности инициализации другого объекта этого класса 
 */
public class DBpool {

    private static DataSource dataSource;
    static Logger logger = Logger.getLogger(Controller.class);

    private DBpool() {
    }

    public static synchronized DataSource getInstance() throws SQLException {
        if (dataSource == null) {
            try {
                javax.naming.Context initialContext = new javax.naming.InitialContext();
                dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/myproject");
            } catch (NamingException e) {
                logger.info("NamingException was thrown");
                logger.error("Exception was thrown : ", e);
            }
        }
        return dataSource;
    }
}
