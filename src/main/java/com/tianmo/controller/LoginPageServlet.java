package com.tianmo.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/loginPage")
public class LoginPageServlet extends BaseServlet{
    @Override
    protected void execute() {
        try {
            // 在JSP的项目中所有页面的格式最好都统一为JSP
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
