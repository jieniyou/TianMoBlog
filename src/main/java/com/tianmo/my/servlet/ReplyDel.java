package com.tianmo.my.servlet;

import com.tianmo.my.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/reply/del")
public class ReplyDel extends HttpServlet {
    JdbcUtil jdbcUtil=new JdbcUtil();
    PreparedStatement preparedStatement;
    Connection connection;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ReplyDel replyDel=new ReplyDel();
            String contentInform=req.getParameter("contentInform");
            replyDel.del(contentInform);
        System.out.println("这个"+contentInform);
        resp.sendRedirect(req.getHeader("Referer"));//返回并刷新
    }

    public void del(String contentInform){
        String sql="delete from reply where contentInform= ? ";
        connection= jdbcUtil.getConnection();
        try {
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,contentInform);
            preparedStatement.executeUpdate();
            System.out.println("这个22"+contentInform);
            System.out.println("删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("删除失败");
        }

    }
}
