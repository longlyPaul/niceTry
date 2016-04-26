package com.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with filter
 * User: pual
 * Date: 2016/4/26
 * Desc:
 */
public class XssFilter implements Filter {
    private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(XssFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request),response);
    }

    @Override
    public void destroy() {

    }
}
