package ua.nure.kolodiazhny.SummaryTask04.readUser;

import ua.nure.kolodiazhny.SummaryTask04.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ������ ��������� ��������
 */
public class IndexFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(IndexFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * ������������� ���������� publication � request scope.
     * ���� ������������ ��� ��������� � �������, �������������� ��� ����� �� ������� (��� ���������)
     * � �� �������� ����������������� (��� ���������������).
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        ArrayList<User> user = ReadUser.getList();
        servletRequest.setAttribute("userList",user);

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