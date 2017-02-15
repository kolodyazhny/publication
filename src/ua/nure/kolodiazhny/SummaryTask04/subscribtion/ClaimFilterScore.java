package ua.nure.kolodiazhny.SummaryTask04.subscribtion;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "/claim.jsp")
public class ClaimFilterScore implements Filter {
    private static final Logger LOGGER = Logger.getLogger(ClaimFilterScore.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * —читывает информаци€ю о названии периодечского издани€, его цене и id
     * из request и записывает ее в request scope.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String title = req.getParameter("title");
        String price = req.getParameter("submit");
        String perID = req.getParameter("id");
        servletRequest.setAttribute("title",title);
        servletRequest.setAttribute("price",price);
        servletRequest.setAttribute("perID",perID);
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