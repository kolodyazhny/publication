package ua.nure.kolodiazhny.SummaryTask04.readScore;

import ua.nure.kolodiazhny.SummaryTask04.entities.Score;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

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
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException{
        ArrayList<Score> score = ReadScore.getList();
        servletRequest.setAttribute("scoreList",score);
    	/*ArrayList<Score> score = ReadScore.getList();
    	servletRequest.setAttribute("scoreList", score);
    	RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("catalog.jsp");

    	if(dispatcher != null) {dispatcher.forward(servletRequest, servletResponse);}*/

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