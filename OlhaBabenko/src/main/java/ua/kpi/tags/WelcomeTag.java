/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.tags;

import java.io.IOException;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;

public class WelcomeTag extends TagSupport {

    private String firstname;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public int doStartTag() {
        try {
            pageContext.getOut().write("Hello, " + firstname);
        } catch (IOException e) {
//            logger.info("SQLException was thrown");
//            logger.error("Exception was thrown : ", e);
        }
        return SKIP_BODY;
    }
}
