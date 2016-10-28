
package startup;

import entityconnection.EntityConnector;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import log.Log;

/**
 * This class will run when the Tomcat Server Starts, and when the
 * Tomcat Server is started. ContextDestroyed Runs when the server is destroyed
 * @author Daniel
 */
public class ServerStart implements ServletContextListener  {

    //Notification that the web application initialization process is starting
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EntityConnector.createEntityManagerFactory();
        try {
            Log.startLogFile();
        } catch (IOException ex) {
            System.out.println("Could Not Start the Log File!" + ex.getMessage());
        }
        Log.writeToLog("Server has Started!");
    }

    //Notification that the servlet context is about to be shut down.
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Log.closeLogger();
    }
    
   
    
}
