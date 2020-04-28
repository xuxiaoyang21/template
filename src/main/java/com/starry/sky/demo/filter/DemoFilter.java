package com.starry.sky.demo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/**
 *
 *@author 徐晓阳
 *@创建日期（ 2019-10-23 11:14 ）
 *@description 自定义过滤器
 * 必须实现三个接口
 * 1。init初始化
 * 2。dofilter过滤逻辑
 * 3。destory销毁
 */
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;

        //执行过滤器链
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
