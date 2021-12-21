package com.tianmo.controller;

import com.wf.captcha.utils.CaptchaUtil;
import com.tianmo.bean.User;
import com.tianmo.response.R;
import com.tianmo.service.UserService;
import com.tianmo.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;


/**
 * @author 解你忧
 * @description
 */
@WebServlet(value = "/login")
public class LoginServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();
    // 使用随机数生成四位数字
    // 然后将数字变成一个图片，加上颜色和干扰线

    @Override
    protected void execute() {
        //验证码是否正确
        if (!CaptchaUtil.ver(request.getParameter("code"), request)) {
            CaptchaUtil.clear(request);  // 清除session中的验证码
            //return JsonResult.error("验证码不正确");
            responseJSON(R.no("验证码不正确"));
            return;
        }
        // 获取页面中提供的数据
        User params = parseParameter(User.class);
        // 调用业务层完成功能
        User user = userService.login(params.getAccount(),params.getPassword());// null/具体的对象
        // 根据业务层的结果，作出不同的处理
        if (user == null) {
            responseJSON(R.no("账号或密码错误"));
        }else{
            //登陆成功，存储一个当前用户信息到session作用域
            session.setAttribute("user",user.getAccount());
            //构建响应对象
            responseJSON(R.ok("登录成功"));
        }
    }
}
