/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.daoNew;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import ua.kpi.logger.Log4JInitServlet;

/**
 *
 * @author Оля
 */
public abstract class MainStatements {

    protected Logger logger = Logger.getLogger(Log4JInitServlet.class);
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    protected MainMethods callMainMethod;

    public MainStatements(Logger log) {
        callMainMethod = new MainMethods(log);
    }
}
