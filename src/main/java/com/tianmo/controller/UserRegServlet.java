package com.tianmo.controller;

import com.tianmo.bean.User;
import com.tianmo.service.UserService;
import com.tianmo.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;

@WebServlet("/user/reg")
public class UserRegServlet extends BaseServlet{
    private UserService userService=new UserServiceImpl();
    @Override
    protected void execute() {
        //接收参数
        User user=parseParameter(User.class);
        //调用业务层完成功能
        boolean isok = userService.save(user);
        //作出响应
        responseJSON(isok?"注册成功":"注册失败");
    }
}
