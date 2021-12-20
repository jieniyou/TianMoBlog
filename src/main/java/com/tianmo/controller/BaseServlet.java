package com.tianmo.controller;

import com.alibaba.fastjson.JSONObject;
import com.tianmo.util.StrUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 这个类是所有servlet类的基类
 */
public abstract class BaseServlet extends HttpServlet {
    //请求方式
    public static String requestType="";
    public static final String POST = "post";
    public static final String GET = "get";
    //经常使用的Servlet对象
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestType=GET;

        initObject(req,resp);

        execute();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestType=POST;

        initObject(req,resp);

        execute();
    }

    protected <T> T parseParameter(Class<T> tClass){
        T object=null;
        try {
            // 创建一个该类型空的对象
            object=tClass.newInstance();//调用空的构造函数创建对象，必须提供一个无参构造函数
            //要求：对象的属性名 与 页面中的表单name保持一致
            //获取到该对象中所有的属性
            Field[] fields=tClass.getDeclaredFields();
            for (Field field : fields) {
                //获取到页面中的数据
                String parameter = request.getParameter(field.getName());
                //排除掉参数为空的情况
                if (parameter == null || "".equals(parameter)){
                    continue;
                }
                //调用对象的set方法给对象进行赋值
                Method method = tClass.getDeclaredMethod(
                        "set" + StrUtil.getMethodName(field.getName()), field.getType());
                //调用该方法
                System.out.println(method.getName());
                if (Integer.class==field.getType()){
                    method.invoke(object,Integer.parseInt(parameter));
                    continue;
                }
                //
                method.invoke(object,parameter);
            }
            return object;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

    private void initObject(HttpServletRequest req, HttpServletResponse resp){
        this.request=req;
        this.response=resp;
        this.session=req.getSession();
    }

    //当前类中的某个方法必须被子类实现  接口、抽象类
    //抽象方法
    protected abstract void execute();

    /**
     * 专门用来对Ajax进行响应的方法
     * 将对象转换为Json字符串通过写对象，将字符串进行输出、响应
     * @param obj
     */
    protected void responseJSON(Object obj){
        response.setContentType("application/json");
        try {
            PrintWriter writer=response.getWriter();

            String jsonString= JSONObject.toJSONString(obj);
            writer.write(jsonString);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 重定向到html页面
     * @param htmlPath
     */
    protected void forword(String htmlPath){
        try {
            request.getRequestDispatcher("/WEB-INF/"+htmlPath).forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
