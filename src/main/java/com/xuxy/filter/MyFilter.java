package com.xuxy.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义过滤器
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器：init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器：doFilter...");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器：destroy...");
    }
}
