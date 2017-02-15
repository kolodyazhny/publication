package ua.nure.kolodiazhny.SummaryTask04.subscribtion;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "/claims.jsp")
public class ClaimFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(ClaimFilter.class);

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
        String name = req.getParameter("name");
        String number = req.getParameter("number");
        String cvc = req.getParameter("cvc");
        servletRequest.setAttribute("name",name);
        servletRequest.setAttribute("number",number);
        servletRequest.setAttribute("cvc",cvc);
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