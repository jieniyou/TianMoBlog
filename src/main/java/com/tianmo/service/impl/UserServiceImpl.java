package com.tianmo.service.impl;

import com.xiaomin.bean.Page;
import com.xiaomin.bean.User;
import com.xiaomin.dao.UserDao;
import com.xiaomin.dao.impl.UserDaoImpl;
import com.xiaomin.query.UserQuery;
import com.xiaomin.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    /**
     * 用户添加
     * @param user
     * @return
     */
    @Override
    public boolean save(User user) {
        return userDao.insert(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(int id) {
        return userDao.delete(id);
    }

    @Override
    public boolean delete(Integer[] ids) {
        for (Integer id : ids){
            boolean delete =userDao.delete(id);
            if (!delete){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean resetPassword(int id, String password) {
        return false;
    }

    @Override
    public Integer count() {
        return userDao.count();
    }

    /**
     *用来查询用户的数据
     *
     * @return
     */
    @Override
    public List<User> queryList(UserQuery userQuery, Page page) {
//        if (userQuery==null){
//            return userDao.selectList(null,page);
//        }
        return userDao.selectList(userQuery,page);
    }

    @Override
    public User login(String account, String password) {
        // 验证参数是否正确 null  ""
        if (account == null || "".equals(account)){
            return null;
        }
        if (password == null || "".equals(password)){
            return null;
        }
        // 调用数据访问层获取数据、或修改数据
        User user= userDao.getUserByAccount(account);
        if(user == null){
            System.out.println("账号不存在");
            return null;
        }
        if(!user.getPassword().equals(password)){
            System.out.println("密码错误");
        }
        // 对结果进行处理
        return user;
    }
}
