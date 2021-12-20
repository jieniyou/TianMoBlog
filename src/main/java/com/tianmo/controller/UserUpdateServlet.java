package com.tianmo.controller;

import com.tianmo.bean.User;
import com.tianmo.response.R;
import com.tianmo.service.UserService;
import com.tianmo.service.impl.UserServiceImpl;

public class UserUpdateServlet extends BaseServlet{
    private UserService userService=new UserServiceImpl();
    @Override
    protected void execute() {
        User user = parseParameter(User.class);
        boolean isok = userService.update(user);
        responseJSON(isok? R.ok("修改成功"):R.no("修改失败"));
    }
}
