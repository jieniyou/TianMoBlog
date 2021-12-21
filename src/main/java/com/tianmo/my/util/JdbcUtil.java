package com.tianmo.my.util;

import com.tianmo.annotation.FieldName;
import com.tianmo.bean.User;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class JdbcUtil {
    private static String driver;
    private static String username;
    private static String password;
    private static String url;
    // XML配置 、 JSON 配置文件、数据传输的一种格式、
    // Preperties key=value
    /**
     * 连接数据库的对象
     */
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;

    static{//静态代码块： 随着类的加载而加载，并且只加载一次
        try {
            //获取到文件的内容
            InputStream inputStream= JdbcUtil.class.getClassLoader().getResourceAsStream(
                    "jdbc.properties");
            //创建解析文件的工具类
            Properties properties=new Properties();
            //使用工具类解析配置文件
            properties.load(inputStream);

            url=properties.getProperty("url");
            username=properties.getProperty("username");
            password=properties.getProperty("password");
            driver=properties.getProperty("driver");

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载驱动失败");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("加载配置文件失败");
        }
    }
    public Connection getConnection(){
        // 通过驱动管理器获取连接
        try {
            connection=DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取连接失败");
        }
        return connection;
    }
    //普通通道、预编译通道、存储过程通道
    private void creatStatement(){
        getConnection();
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("创建通道失败");
        }
    }

    private void createPreStatement(String sql){
        getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("创建预编译通道失败");
        }
    }
    public boolean execute(String sql,Object...params){//可变的参数,代表一个或多个参数，只能方参数最后
        // 创建通道
        createPreStatement(sql);
        // 绑定参数
        bandle(params);
        try {
            int i= preparedStatement.executeUpdate();
            return i>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public <T> List<T> executeQuery(String sql,Class<T> typeClass,Object... params){
        createPreStatement(sql);
        bandle(params);
        try {
            resultSet=preparedStatement.executeQuery();
            return parseResultSet(resultSet,typeClass);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("预编译通道执行查询失败");
        }
        return null;
    }

    public int executeCount(String sql){
        creatStatement();
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 用来给预编译通道绑定数据
     * @param params
     */
    private void bandle(Object[] params) {
        if (params == null){
            return;
        }
        try {
            for (int i = 0; i <params.length ; i++) {
                preparedStatement.setObject(i+1,params[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("绑定参数失败");
        }
    }

    public boolean execute(String sql){
        creatStatement();
        try {
            return statement.executeUpdate(sql)>0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("执行SQL异常");
        }finally {
            closeRes();
        }
        return false;
    }
    public <T> List<T> executeQuery(String sql, Class<T> typeClass){
        creatStatement();
        //sql语句由用户去编写 select * from user
        //范型
        try {// 反射/离线结果集
            resultSet = statement.executeQuery(sql);

            return parseResultSet(resultSet,typeClass);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("执行查询语句异常");
        }finally {
            closeRes();
        }
        return Collections.emptyList();
    }
    private <T> List<T> parseResultSet(ResultSet resultSet,Class<T> typeClass){
        // 获取到所有的字段，获取所有的set方法、get方法
        Field[] fields = typeClass.getDeclaredFields();

        List<T> objectList = new ArrayList<>();
        try {
            // 获取数据库中的数据
            while (resultSet.next()) {
                // 每一行数据 === 一个对象
                T object = typeClass.newInstance();
                // 获取一条数据
                for (Field field : fields) {

                    //获取到该字段的注解
                    FieldName fieldName = field.getAnnotation(FieldName.class);

                    // 通过判断字段上是否添加了注解：来获取具体使用哪个字段名称
                    String sqlFieldName = fieldName != null ? fieldName.value() : field.getName();
                    Object filedValue = resultSet.getObject(sqlFieldName);

                    //将数据set到对象中
                    Method setMethod = typeClass.getDeclaredMethod(
                            "set" + StrUtil.getMethodName(field.getName()), field.getType());
                    //调用set方法
                    setMethod.invoke(object, filedValue);
                }
                objectList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("执行出错");
        }
        return objectList;
    }
    // 5. 对结果进行处理                // 对玩耍的事务进行总结
    // 6. 关闭资源、释放资源             // 断开联系
    private void closeRes(){
        //就近原则
        try {
            if (resultSet!=null){
                resultSet.close();
            }
            if (preparedStatement!=null){
                preparedStatement.close();
            }
            if (statement!=null) {
                statement.close();
            }
            if (connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
