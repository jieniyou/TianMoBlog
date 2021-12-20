package com.tianmo.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet("/reg")
public class RegServlet extends BaseServlet{
    @Override
    protected void execute() {
        forword("reg.jsp");
    }
}
