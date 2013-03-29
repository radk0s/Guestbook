package guestbook.listener; /**
 * Created with IntelliJ IDEA.
 * User: Radek
 * Date: 29.03.13
 * Time: 10:52
 * To change this template use File | Settings | File Templates.
 */

import guestbook.model.GuestbookModel;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class GuestbookListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private GuestbookModel db;

    public GuestbookListener() {
        db = GuestbookModel.getInstance();
    }
    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        db.MsSQLinit(sce.getServletContext().getInitParameter("DatabaseDrivers"), sce.getServletContext().getInitParameter("DbAddress"),
                sce.getServletContext().getInitParameter("DbUser"), sce.getServletContext().getInitParameter("DbPassword"));
    }



    public void contextDestroyed(ServletContextEvent sce) {
        db.destroy();
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
