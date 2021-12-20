package com.tianmo.controller;

import com.tianmo.response.R;
import com.tianmo.service.UserService;
import com.tianmo.service.impl.UserServiceImpl;
import com.tianmo.util.StrUtil;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/user/delete")
public class UserDeleteServlet extends BaseServlet{

    /**
     * 通过构造函数创建业务层对象
     */

    private UserService userService;
    public UserDeleteServlet(){
        this.userService=new UserServiceImpl();
    }

    @Override
    protected void execute() {
        //接收参数
        String id = request.getParameter("id");
        String ids = request.getParameter("ids");
        //参数的验证
        if (StrUtil.isEmpty(id) && StrUtil.isEmpty(ids)){
            responseJSON(R.no("请传递正确的参数"));
            return;
        }
        //单条删除
        if (StrUtil.isNotEmpty(id)){
            //调用业务层完成功能
            boolean isok=userService.delete(StrUtil.parseStrToInt(id));
            //根据有任务层的结果做出不同响应
            R r=isok ? R.ok("删除成功"):R.no("删除失败");
            responseJSON(r);
            return;
        }
        //多条删除
        if (StrUtil.isNotEmpty(ids)){
            List<Integer> idList = StrUtil.parseStrToArray(ids,",");

            Integer[] integers=idList.toArray(new Integer[1]);

            boolean delete=userService.delete(integers);

            responseJSON(delete?R.ok("全部删除成功"):R.no("部分数据删除失败"));
        }
    }
}
