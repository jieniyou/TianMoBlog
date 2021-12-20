package com.tianmo.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet("/user/index")
public class UserPageServlet extends BaseServlet{
    @Override
    protected void execute() {
        forword("page/user/index.jsp");
    }
}
