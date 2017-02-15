package ua.nure.kolodiazhny.SummaryTask04.read;

import ua.nure.kolodiazhny.SummaryTask04.entities.Publication;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Фильтр начальной страницы
 */
public class IndexFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(IndexFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Устанавливает переменную publication в request scope.
     * Если пользователь уже залогинен в системе, перенаправляет его сразу на каталог (для читателей)
     * и на страницу администрирования (для администраторов).
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        ArrayList<Publication> user = ReadPublication.getList();
        servletRequest.setAttribute("publicationList",user);

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String users = (String) session.getAttribute("currentUser");
        if (users != null){
            if (users.equals("admin")){
                try{
                    req.getRequestDispatcher("admin_page.jsp").forward(req,resp);
                } catch (ServletException e) {
                    LOGGER.error(e.getMessage());
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }else{
                try {
                    req.getRequestDispatcher("catalog.jsp").forward(req,resp);
                } catch (ServletException e) {
                    LOGGER.error(e.getMessage());
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }else{
            try{
                filterChain.doFilter(servletRequest,servletResponse);
            } catch (ServletException e) {
                LOGGER.error(e.getMessage());
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public void destroy() {

    }
}