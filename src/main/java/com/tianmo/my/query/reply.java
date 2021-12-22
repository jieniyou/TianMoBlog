package com.tianmo.my.query;

import com.tianmo.my.bean.Reply;
import com.tianmo.my.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@WebServlet("/reply/add")
public class reply extends HttpServlet {
    JdbcUtil jdbcUtil=new JdbcUtil();
    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    Reply reply=null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");

        String author = req.getParameter("author");
        String author2 = req.getParameter("author2");
        String create_date = req.getParameter("create_date");
        String contentInform = req.getParameter("contentInform");

        reply reply=new reply();
        Reply reply1=new Reply();

        reply1.setAuthor(author);
        reply1.setAuthor2(author2);
        reply1.setCreate_date(create_date);
        reply1.setContentInform(contentInform);
        reply.add(reply1);

//        req.getRequestDispatcher("/page/detail.jsp?account="+author2).forward(req,resp);
        resp.sendRedirect(req.getHeader("Referer"));//返回并刷新
    }

    public void add(Reply reply){
         connection= jdbcUtil.getConnection();
         String sql="insert into reply(author,create_date,contentInform,author2) values(?,?,?,?)";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,reply.getAuthor());
            preparedStatement.setString(2,reply.getCreate_date());
            preparedStatement.setString(3,reply.getContentInform());
            preparedStatement.setString(4,reply.getAuthor2());
            preparedStatement.execute();
            System.out.println("添加成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("添加失败");
        }

    }
    public List<Reply> executeQuery(){
        connection= jdbcUtil.getConnection();
        String sql="select * from reply";
        List<Reply> replies=new ArrayList<>();
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                reply=new Reply();
                reply.setAuthor(resultSet.getString(1));
                reply.setCreate_date(resultSet.getString(2));
                reply.setContentInform(resultSet.getString(3));
                reply.setAuthor2(resultSet.getString(4));
                replies.add(reply);
            }
            return replies;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Reply> executeQuery(String author2){
        connection= jdbcUtil.getConnection();
        String sql="select * from reply where author2 = ? ";
        List<Reply> replies=new ArrayList<>();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,author2);
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                reply=new Reply();
                reply.setAuthor(resultSet.getString(1));
                reply.setCreate_date(resultSet.getString(2));
                reply.setContentInform(resultSet.getString(3));
                reply.setAuthor2(resultSet.getString(4));
                replies.add(reply);
            }
            return replies;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Reply> selectquery(String author){
        connection= jdbcUtil.getConnection();
        String sql="select * from reply where author = ? ";
        List<Reply> replies=new ArrayList<>();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,author);
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                reply=new Reply();
                reply.setAuthor(resultSet.getString(1));
                reply.setCreate_date(resultSet.getString(2));
                reply.setContentInform(resultSet.getString(3));
                reply.setAuthor2(resultSet.getString(4));
                replies.add(reply);
            }
            return replies;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
