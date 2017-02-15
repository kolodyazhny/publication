package ua.nure.kolodiazhny.SummaryTask04.subscribtion;

import ua.nure.kolodiazhny.SummaryTask04.entities.Score;
import ua.nure.kolodiazhny.SummaryTask04.security.InvalidInfo;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = "/Subscribe")
public class ScoreFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(ScoreFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Проверка введенной о карте информации.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String cardNumber = req.getParameter("number");
        int cvc = Integer.parseInt(req.getParameter("cvc"));
        String name = req.getParameter("name");

        Score score = new Score(name,cardNumber,cvc);
        if(ScoreChecker.check(score)){
            try{
                filterChain.doFilter(servletRequest,servletResponse);
            } catch (ServletException e) {
                LOGGER.error(e.getMessage());
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }else{
            InvalidInfo.invalid(req,resp, "claim.jsp", "Information about your card is wrong!");
        }

    }

    @Override
    public void destroy() {
    }
}