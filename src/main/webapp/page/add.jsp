<%@ page import="com.tianmo.my.bean.Inform" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: 解你忧
  Date: 2021/12/22
  Time: 2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>问题或观念</title>
    <link rel="shortcut icon" href="/img/天魔1.png" type="image/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <link rel="stylesheet" href="/fly/res/layui/css/layui.css">
    <link rel="stylesheet" href="/fly/res/css/global.css">
</head>
<body>

<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a class="fly-logo" href="/index">
            <img src="/assets/images/天魔社区图一.png" width="10%" style="margin-top: -2%" alt="layui">
        </a>

        <ul class="layui-nav fly-nav-user">

            <!-- 登入后的状态 -->

            <jsp:useBean id="informDao" class="com.tianmo.my.bean.Inform"></jsp:useBean>
            <jsp:useBean id="informQuery" class="com.tianmo.my.query.inform"></jsp:useBean>
            <%
                String user=(String) session.getAttribute("user");
                Inform userinform = informQuery.getInfoByAuthor(user);
            %>
            <li class="layui-nav-item">
                <a class="fly-nav-avatar" href="javascript:;">
                    <cite class="layui-hide-xs"><%=user%></cite>
                    <i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：layui 作者"></i>
                    <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg">
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="../user/set.html"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
                    <dd><a href="../user/message.html"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a></dd>
                    <dd><a href="../user/home.html"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a></dd>
                    <hr style="margin: 5px 0;">
                    <dd><a href="" style="text-align: center;">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</div>

<div class="layui-container fly-marginTop">
    <div class="fly-panel" pad20 style="padding-top: 5px;">
        <!--<div class="fly-none">没有权限</div>-->
        <div class="layui-form layui-form-pane">
            <div class="layui-tab layui-tab-brief" lay-filter="user">
                <ul class="layui-tab-title">
                    <li class="layui-this">发表新帖<!-- 编辑帖子 --></li>
                </ul>
                <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                    <div class="layui-tab-item layui-show">

                        <%
                            Date date=new Date();
                            String now=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
                        %>
                        <form action="/inform/add" method="post">
                            <input type="hidden" name="author" value="<%=user%>">
                            <input type="hidden" name="create_time" value="<%=now%>">
                            <div class="layui-row layui-col-space15 layui-form-item">
                                <div class="layui-col-md3">
                                    <label class="layui-form-label">所在专栏</label>
                                    <div class="layui-input-block">
                                        <select lay-verify="required" name="status" lay-filter="column">
                                            <option></option>
                                            <option value="提问">提问</option>
                                            <option value="分享">分享</option>
                                            <option value="讨论">讨论</option>
                                            <option value="建议">建议</option>
                                            <option value="公告">公告</option>
                                            <option value="动态">动态</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-md9">
                                <label for="L_title" class="layui-form-label">标题</label>
                                <div class="layui-input-block">
                                    <input type="text" id="L_title" name="inform" required lay-verify="required" autocomplete="off" class="layui-input">
                                    <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                                </div>
                            </div>
                            <div class="layui-form-item layui-form-text">
                                <div class="layui-input-block">
                                    <textarea id="L_content" name="contentInfor" required lay-verify="required" placeholder="详细描述" class="layui-textarea fly-editor" style="height: 260px;"></textarea>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <input type="submit"  class="layui-btn" lay-filter="*" value="立即发布">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="fly-footer">
    <p><a href="/index" target="_blank">TianMo社区</a> 2021 &copy; <a href="/index" target="_blank">xiaomin.com 出品</a></p>
</div>

<script src="/fly/res/layui/layui.js"></script>
<script>
    layui.cache.page = 'jie';
    layui.cache.user = {
        username: '游客'
        ,uid: -1
        ,avatar: '/fly/res/images/avatar/00.jpg'
        ,experience: 83
        ,sex: '男'
    };
    layui.config({
        version: "3.0.0"
        ,base: '/fly/res/mods/'
    }).extend({
        fly: 'index'
    }).use('fly');
</script>

</body>
</html>
