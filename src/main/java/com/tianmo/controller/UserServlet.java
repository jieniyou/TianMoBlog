package com.tianmo.controller;


import javax.servlet.annotation.WebServlet;

@WebServlet("/user/list")
public class UserServlet extends BaseServlet {

    @Override
    protected void execute() {
        responseJSON("HelloWorld");
    }
}
