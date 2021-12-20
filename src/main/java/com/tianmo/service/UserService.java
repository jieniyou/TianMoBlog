package com.tianmo.service;

import com.tianmo.bean.Page;
import com.tianmo.bean.User;
import com.tianmo.query.UserQuery;

import java.util.List;

public interface UserService {
    User login(String account,String password);

    /**
     * 用来查询用户的数据
     * 分也与条件查询
     * @return
     */
    List<User> queryList(UserQuery userQuery, Page page);

    /**
     * 用户添加
     */
    boolean save(User user);

    //用户修改
    boolean update(User user);

    //用户删除（单条与批量）
    boolean delete(int id);

    boolean delete(Integer[] ids);

    //重置密码
    boolean resetPassword(int id,String password);

    //可以尝试一下
    //String resetPassword(int id);

    Integer count();
}
