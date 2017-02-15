package ua.nure.kolodiazhny.SummaryTask04.listener;

import org.apache.log4j.PropertyConfigurator;

import ua.nure.kolodiazhny.SummaryTask04_2.connection.ConnectionPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

@WebListener("application context listener")
public class ContextListener implements ServletContextListener {

    /**
     * Инициализация Log4j перед запуском приложения.
     * @param event
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

        PropertyConfigurator.configure(fullPath);
    }

    /**
     * Закрытие пула соединений перед окончанием работы приложения.
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.closePool();
    }
}
