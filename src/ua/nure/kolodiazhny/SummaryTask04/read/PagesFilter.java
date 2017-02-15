package ua.nure.kolodiazhny.SummaryTask04.read;

import ua.nure.kolodiazhny.SummaryTask04.entities.Publication;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;

public class PagesFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(PagesFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Устанавливает значение аттрибута periodicalList в request scope.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        ArrayList<Publication> publication = ReadPublication.getList();
        servletRequest.setAttribute("publicationList",publication);
        try{
            filterChain.doFilter(servletRequest,servletResponse);
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