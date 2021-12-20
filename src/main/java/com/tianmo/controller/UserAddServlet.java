package com.tianmo.controller;

import com.tianmo.bean.User;
import com.tianmo.response.R;
import com.tianmo.service.UserService;
import com.tianmo.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;

@WebServlet("/user/add")
public class UserAddServlet extends BaseServlet{
    private UserService userService=new UserServiceImpl();
    @Override
    protected void execute() {
        //接收参数
        User user = parseParameter(User.class);
        //调用业务层完成功能
        boolean isok = userService.save(user);
        //作出响应
        responseJSON((isok? R.ok("添加成功"):R.no("添加失败")));


    }
}
