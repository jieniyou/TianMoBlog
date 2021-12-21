package com.tianmo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    /**
     * 放行的URL
     */
    private static final String[] urls={"/loginPage","/login","/reg","/captcha"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filter) throws IOException, ServletException {
        System.out.println("执行登陆滤器");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 通过判断路径的方式，对静态资源进行放行
        // 获取当前的请求路径
        String path = request.getServletPath();
        System.out.println(path);
        // 判断当前这个路径是否应该被放行
        for (String url : urls) {
            if(url.equals(path)||path.startsWith("/assets/")||path.startsWith("/fly/")){
                //放行
                filter.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        //加入登陆的验证
        Object user = request.getSession().getAttribute("user");
        System.out.println("当前用户信息："+user);
        if (user != null) {
            // 将拦截到的内容，进行放行
            filter.doFilter(servletRequest,servletResponse);
            return;
        }
        //request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        response.sendRedirect("/loginPage");
    }
}
