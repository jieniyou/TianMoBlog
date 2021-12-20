package com.tianmo.dao;

import com.tianmo.bean.Page;
import com.tianmo.bean.User;
import com.tianmo.query.UserQuery;

import java.util.List;

public interface UserDao {
    User getUserByAccount(String account);
    List<User> selectList(UserQuery userQuery,Page page);
    /**
     * 用户添加
     */
    boolean insert(User user);

    //用户修改
    boolean update(User user);

    //用户删除（单条与批量）
    boolean delete(int id);

    boolean delete(int[] ids);

    Integer count(UserQuery userQuery);
}
