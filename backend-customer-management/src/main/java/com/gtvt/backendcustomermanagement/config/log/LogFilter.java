package com.gtvt.backendcustomermanagement.config.log;



import javax.servlet.*;
import java.io.IOException;


public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
