package com.tianmo.my.query;

import com.tianmo.bean.User;
import com.tianmo.my.bean.Inform;
import com.tianmo.my.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class inform {
    JdbcUtil jdbcUtil=new JdbcUtil();
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    Inform inform=null;
    Connection conn;
    User user=null;
    public List<Inform> getInformByStatus(String status){
        conn=jdbcUtil.getConnection();
        String sql="select * from inform where status = ? ";
        List<Inform> informs=new ArrayList<>();
        try {
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setString(1,status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                inform=new Inform();
                inform.setAuthor(resultSet.getString(2));
                inform.setInform(resultSet.getString(3));
                inform.setContentInfor(resultSet.getString(4));
                inform.setCreateTime(resultSet.getString(5));
                inform.setStatus(resultSet.getString(6));
                inform.setReviewCount(resultSet.getInt(7));
                informs.add(inform);
            }
            return informs;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }


    }

    public List<Inform> getInformByAuthor(String author){
        conn=jdbcUtil.getConnection();
        String sql="select * from inform where author = ? ";
        List<Inform> informs=new ArrayList<>();
        try {
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setString(1,author);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                inform=new Inform();
                inform.setAuthor(resultSet.getString(2));
                inform.setInform(resultSet.getString(3));
                inform.setContentInfor(resultSet.getString(4));
                inform.setCreateTime(resultSet.getString(5));
                inform.setStatus(resultSet.getString(6));
                inform.setReviewCount(resultSet.getInt(7));
                informs.add(inform);
            }
            return informs;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    public List<User> execute2(){
        List<User> users=new ArrayList<>();
        String sql="select avatar from user";
        try {
            conn=jdbcUtil.getConnection();
            statement=conn.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                user=new User();
                user.setAvatar(resultSet.getString(1));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Inform> execute(){
        List<Inform> informs = new ArrayList<>();
        String sql="select * from inform";
        try {
            conn=jdbcUtil.getConnection();
            statement=conn.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                inform=new Inform();
                inform.setAuthor(resultSet.getString(2));
                inform.setInform(resultSet.getString(3));
                inform.setContentInfor(resultSet.getString(4));
                inform.setCreateTime(resultSet.getString(5));
                inform.setStatus(resultSet.getString(6));
                inform.setReviewCount(resultSet.getInt(7));
                informs.add(inform);
            }
            return informs;

        } catch (SQLException e) {
            System.out.println("没有数据");
            return Collections.emptyList();
        }
    }
    public Inform getInfoByAuthor(String author){
        String sql="select * from inform where author=?";
        try {
            conn=jdbcUtil.getConnection();
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,author);
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                inform=new Inform();
                inform.setAuthor(resultSet.getString(2));
                inform.setInform(resultSet.getString(3));
                inform.setContentInfor(resultSet.getString(4));
                inform.setCreateTime(resultSet.getString(5));
                inform.setStatus(resultSet.getString(6));
                inform.setReviewCount(resultSet.getInt(7));
            }
            return inform;
        } catch (SQLException e) {
            System.out.println("没有数据");
            return (Inform) Collections.emptyList();
        }
    }

    public static void main(String[] args) {
        List<User> users=new ArrayList<>();
        inform inform=new inform();
        users=inform.execute2();
        System.out.println();
        for (User user:users){
            System.out.println(user);
        }
    }
}
