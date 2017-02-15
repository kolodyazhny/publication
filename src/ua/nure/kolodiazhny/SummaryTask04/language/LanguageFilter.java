package ua.nure.kolodiazhny.SummaryTask04.language;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Интернационализация пользовательского интерфейса.
 */
public class LanguageFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(LanguageFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Установка локаля, полученного методом GET, в сессию.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
    	HttpServletRequest req = (HttpServletRequest) servletRequest;
        req.getSession(true).setAttribute("local",req.getParameter("locale"));
        try{
            filterChain.doFilter(req,servletResponse);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}