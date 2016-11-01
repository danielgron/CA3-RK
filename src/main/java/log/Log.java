/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author rasmu
 */
public class Log {
    
    public final static String logName = "logger";
    
    public static void startLogFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String fileName = "LogFile" + timeStamp + ".txt";
         Logger logger = Logger.getLogger(logName);
         FileHandler file = new FileHandler(fileName);
         // This places the log file in the bin folder where you have your 
         // CatalinaBase if you are using an Tomcat Server
         file.setFormatter(new java.util.logging.SimpleFormatter());
         logger.addHandler(file);
    }
    
    public static void closeLogger() {
        for (Handler h : Logger.getLogger(logName).getHandlers()) {
            System.out.println("Closing logger");
            h.close();
        }
    }
    
    public static void writeToLog(String s){
        Logger.getLogger(Log.logName).log(Level.INFO, s);
    }
    
}
