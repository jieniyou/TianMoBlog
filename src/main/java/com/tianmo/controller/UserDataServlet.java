package com.tianmo.controller;

import com.tianmo.bean.Page;
import com.tianmo.bean.User;
import com.tianmo.query.UserQuery;
import com.tianmo.response.R;
import com.tianmo.service.UserService;
import com.tianmo.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/user/data.json")
public class UserDataServlet extends BaseServlet{
    private UserService userService=new UserServiceImpl();
    @Override
    protected void execute() {
        //接收搜索条件，封装成对象 UserQuery account、roleID
        //将搜索条件传递给业务层
        UserQuery userQuery = parseParameter(UserQuery.class);
        //接收分页的数据
        Page page =parseParameter(Page.class);
        Integer count = userService.count(userQuery);
        //查询数据 从业务层获取数据
        List<User> list=userService.queryList(userQuery,page);
        //作出响应 将业务层返回的数据，封装成JSON的字符出
        R r = R.ok(0, "查询成功", list, count);
        responseJSON(r);

    }
}
