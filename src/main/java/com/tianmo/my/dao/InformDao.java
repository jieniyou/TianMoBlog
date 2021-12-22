package com.tianmo.my.dao;

import com.tianmo.my.bean.Inform;
import com.tianmo.my.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

@WebServlet("/inform/add")
public class InformDao extends HttpServlet {
    JdbcUtil jdbcUtil=new JdbcUtil();
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    Connection connection;
    String sql;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");

        String author=req.getParameter("author");
        String inform = req.getParameter("inform");
        String contentInfor = req.getParameter("contentInfor");
        String create_time = req.getParameter("create_time");
        String status = req.getParameter("status");

        Inform inform1=new Inform();
        inform1.setAuthor(author);
        inform1.setInform(inform);
        inform1.setContentInfor(contentInfor);
        inform1.setCreateTime(create_time);
        inform1.setStatus(status);

        InformDao informDao=new InformDao();
        informDao.add(inform1);
        req.getRequestDispatcher("/index").forward(req,resp);
    }

    public void add(Inform inform) {
        connection = jdbcUtil.getConnection();
        sql="insert into inform(author,inform,contentInfor,create_time,status) value(?,?,?,?,?)";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,inform.getAuthor());
            preparedStatement.setString(2,inform.getInform());
            preparedStatement.setString(3,inform.getContentInfor());
            preparedStatement.setString(4,inform.getCreateTime());
            preparedStatement.setString(5,inform.getStatus());

            preparedStatement.execute();
            System.out.println("添加成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("添加失败");
        }

    }
}
