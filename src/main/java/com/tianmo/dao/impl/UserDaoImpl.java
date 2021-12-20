package com.tianmo.dao.impl;

import com.xiaomin.bean.Page;
import com.xiaomin.bean.User;
import com.xiaomin.dao.UserDao;
import com.xiaomin.query.UserQuery;
import com.xiaomin.util.JdbcUtil;
import com.xiaomin.util.StrUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcUtil jdbcUtil=new JdbcUtil();

    /**
     * 用户添加
     * @param user
     * @return
     */
    @Override
    public boolean insert(User user) {
        //SQL
        String sql="insert into user(account,password,role_id) value(?,?,?)";
        //执行SQL
        boolean execute = jdbcUtil.execute(sql, user.getAccount(), user.getPassword(), user.getRoleId());
        //根据结果作响应
        return execute;
    }

    @Override
    public boolean update(User user) {
        String sql="update user set account=?,password=?,role_id=? where id=?";
        boolean execute = jdbcUtil.execute(sql, user.getAccount(), user.getPassword(), user.getRoleId(),user.getId());
        return execute;
    }

    @Override
    public boolean delete(int id) {
        //SQL语句
        //物理删除
        String sql="delete from user where id=?";
        //状态删除
        sql ="update user set delete_status=1 where id=?";
        //执行SQL
        boolean execute = jdbcUtil.execute(sql, id);
        //对结果进行处理
        return execute;
    }

    @Override
    public boolean delete(int[] ids) {
        return false;
    }

    @Override
    public Integer count() {
        String sql="select count(*) from user where delete_status=0";
        if (userQuery==null){
            return jdbcUtil.executeCount(sql);
        }
        //如果查询条件查询为空
        if (userQuery==null){
            stringBuffer.append(" limit ?,?");
            return jdbcUtil.executeQuery(stringBuffer.toString(),User.class,page.getStartRow(),page.getLimit());
        }
        //查询条件不为空
        if (StrUtil.isNotEmpty(userQuery.getAccount())){
            sql+=" where account ='"+userQuery.getAccount()+"'";
        }
        if (userQuery.getRoleID()!=null&&userQuery.getRoleID()>0){
            sql+=" and role_id="+userQuery.getRoleID();
        }//判断哪些情况需要添加Where，那些情况添加and

        Integer integers=jdbcUtil.executeCount(sql);
        return integers;
    }

    public User getUserByAccount(String account){
        // 编写SQL
        String sql = "select * from user where account = ?";
        // 执行SQL
        List<User> users = jdbcUtil.executeQuery(sql, User.class, account);
        if (users == null || users.size() <= 0){
            return null;
        }
        //对结果进行处理
        return users.get(0);
    }
    @Override
    public List<User> selectList(UserQuery userQuery,Page page) {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("select * from user where 1=1 ");//where delete_status = 0
        //如果查询条件查询为空
        if (userQuery==null){
            stringBuffer.append(" limit ?,?");
            return jdbcUtil.executeQuery(stringBuffer.toString(),User.class,page.getStartRow(),page.getLimit());
        }
        //查询条件不为空
        if (StrUtil.isNotEmpty(userQuery.getAccount())){
            stringBuffer.append(" where account ='"+userQuery.getAccount()+"'");
        }
        if (userQuery.getRoleID()!=null&&userQuery.getRoleID()>0){
            stringBuffer.append(" and role_id="+userQuery.getRoleID());
        }//判断哪些情况需要添加Where，那些情况添加and
        stringBuffer.append(" limit ?,?");//select *from user where account ='zhangsan'and role_id=1 limit 0,10
        return jdbcUtil.executeQuery(stringBuffer.toString(),User.class,page.getStartRow(),page.getLimit());
    }
}
