package ua.nure.kolodiazhny.SummaryTask04.encoding;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class EncodingFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class);

    /**
     * Параметр кодировки.
     */
    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Устанавливает параметр кодировки для каждого запроса/ответа.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        try{
            servletRequest.setCharacterEncoding(encoding);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage());
        }
        try{
            filterChain.doFilter(servletRequest,servletResponse);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        servletResponse.setCharacterEncoding(encoding);
    }

    @Override
    public void destroy() {

    }
}