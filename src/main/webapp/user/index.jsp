<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tianmo.my.bean.Reply" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tianmo.my.query.reply" %>
<%@ page import="com.tianmo.my.bean.Inform" %>
<%@ page import="com.tianmo.my.query.inform" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户中心</title>
  <link rel="shortcut icon" href="/img/天魔1.png" type="image/x-icon">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="fly,layui,前端社区">
  <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
  <link rel="stylesheet" href="/fly/res/layui/css/layui.css">
  <link rel="stylesheet" href="/fly/res/css/global.css">
</head>
<body>
<%
  String account=request.getParameter("account");
  String user=(String) session.getAttribute("user");
%>
<div class="fly-header layui-bg-black">
  <div class="layui-container">
    <a class="fly-logo" href="/index">
      <img src="/assets/images/天魔社区图一.png" width="10%" style="margin-top: -2%" alt="天魔">
    </a>

    <ul class="layui-nav fly-nav-user">
      <!-- 登入后的状态 -->
      <li class="layui-nav-item">
        <a class="fly-nav-avatar" href="javascript:;">
          <cite class="layui-hide-xs"><%=user%></cite>
          <i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：TianMo 作者"></i>
          <%--          <i class="layui-badge fly-badge-vip layui-hide-xs">VIP3</i>--%>
          <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg">
        </a>
        <dl class="layui-nav-child">
          <dd><a href="../user/set.jsp?account=<%=user%>"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
          <dd><a href="../user/message.jsp?account=<%=user%>"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a></dd>
          <dd><a href="../user/home.jsp?account=<%=user%>"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a></dd>
          <hr style="margin: 5px 0;">
          <dd><a href="/loginPage" style="text-align: center;">退出</a></dd>
        </dl>
      </li>
    </ul>
  </div>
</div>

<div class="layui-container fly-marginTop fly-user-main">
  <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
    <li class="layui-nav-item">
      <a href="home.jsp?account=<%=session.getAttribute("user")%>">
        <i class="layui-icon">&#xe609;</i>
        我的主页
      </a>
    </li>
    <li class="layui-nav-item layui-this">
      <a href="index.jsp?account=<%=session.getAttribute("user")%>">
        <i class="layui-icon">&#xe612;</i>
        用户中心
      </a>
    </li>
    <li class="layui-nav-item">
      <a href="set.jsp?account=<%=session.getAttribute("user")%>">
        <i class="layui-icon">&#xe620;</i>
        基本设置
      </a>
    </li>
    <li class="layui-nav-item">
      <a href="message.jsp?account=<%=session.getAttribute("user")%>">
        <i class="layui-icon">&#xe611;</i>
        我的消息
      </a>
    </li>
  </ul>

  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
  
  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
  
  
  <div class="fly-panel fly-panel-user" pad20>
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title" id="LAY_mine">
        <li data-type="mine-jie" lay-id="index" class="layui-this">我发的帖</li>
      </ul>
      <div class="layui-tab-content" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <ul class="mine-view jie-row">
            <%
              inform inform=new inform();
              List<Inform> informs = inform.getInformByAuthor(user);
              for (Inform inform1:informs){
            %>
            <li>
              <a class="jie-title" href="/user/home.jsp?account=<%=user%>" target="_blank"><%=inform1.getInform()%></a>
              <i><%=inform1.getCreateTime()%></i>
              <em><%=inform1.getReviewCount()%></em>
            </li>
            <%
              }
            %>
          </ul>
          <div id="LAY_page"></div>
        </div>
        <div class="layui-tab-item">
          <ul class="mine-view jie-row">
          </ul>
          <div id="LAY_page1"></div>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="fly-footer">
  <p><a href="/index" target="_blank">TianMo社区</a> 2021 &copy; <a href="/index" target="_blank">xiaomin.com 出品</a></p>
</div>

<script src="../../res/layui/layui.js"></script>
<script>
layui.cache.page = 'user';
layui.cache.user = {
  username: '游客'
  ,uid: -1
  ,avatar: '../../res/images/avatar/00.jpg'
  ,experience: 83
  ,sex: '男'
};
layui.config({
  version: "3.0.0"
  ,base: '../../res/mods/'
}).extend({
  fly: 'index'
}).use('fly');
</script>

</body>
</html>