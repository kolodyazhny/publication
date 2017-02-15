package ua.nure.kolodiazhny.SummaryTask04.readUser;

import ua.nure.kolodiazhny.SummaryTask04.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;

public class PagesFilterUser implements Filter {
    private static final Logger LOGGER = Logger.getLogger(PagesFilterUser.class);

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
        ArrayList<User> user = ReadUser.getList();
        servletRequest.setAttribute("userList",user);
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