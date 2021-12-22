<%@ page import="com.tianmo.my.bean.Reply" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tianmo.my.query.reply" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tianmo.my.bean.Inform" %>
<%@ page import="com.tianmo.my.query.inform" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户主页</title>
  <link rel="shortcut icon" href="/img/天魔1.png" type="image/x-icon">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="fly,layui,前端社区">
  <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
  <link rel="stylesheet" href="/fly/res/layui/css/layui.css">
  <link rel="stylesheet" href="/fly/res/css/global.css">
</head>
<body bgcolor="black" style="margin-top: 65px;">
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

<div class="fly-home fly-panel" style="background-image: url(/assets/images/user_bg.jpg); background-position: center; background-size: 100%;">
  <img src="/img/岳艳.jpg" alt="<%=account%>">
  <i class="iconfont icon-renzheng" title="TianMo社区认证"></i>
  <h1>
    <%=account%>
    <i class="iconfont icon-nan"></i>

  </h1>

  <p style="padding: 10px 0; color: #5FB878;">认证信息：TianMo 作者</p>

  <p class="fly-home-info">
    <i class="iconfont icon-kiss" title="飞吻"></i><span style="color: #FF7200;">66666 飞吻</span>
    <i class="iconfont icon-shijian"></i><span>2015-6-17 加入</span>
    <i class="iconfont icon-chengshi"></i><span>来自杭州</span>
  </p>

  <p class="fly-home-sign">（人生仿若一场修行）</p>

  <div class="fly-sns" data-user="">
    <a href="javascript:;" class="layui-btn layui-btn-primary fly-imActive" data-type="addFriend">加为好友</a>
    <a href="javascript:;" class="layui-btn layui-btn-normal fly-imActive" data-type="chat">发起会话</a>
  </div>

</div>

<div class="layui-container" style="background: rgba(255,255,255,0.6)">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md6 fly-home-jie">
      <div class="fly-panel" style="width: 140%">
        <h3 class="fly-panel-title"><%=account%> 最近的提问</h3>

        <ul class="jie-row">
          <%
            inform inform=new inform();
            List<Inform> informs = inform.getInformByAuthor(user);
            for (Inform infoByAuthor:informs){
          %>
          <li>
            <span class="fly-jing">精</span>
            <a href="/index" class="jie-title"> <%=infoByAuthor.getContentInfor()%></a>
            <i><%=infoByAuthor.getCreateTime()%></i>
            <em class="layui-hide-xs"><%=infoByAuthor.getInform()%></em>
          </li>
          <%
            }
          %>
        </ul>
      </div>
    </div>
    
    <div class="layui-col-md6 fly-home-da">
      <div class="fly-panel"  style="width: 140%">
        <h3 class="fly-panel-title"><%=account%> 最近的回答</h3>
        <ul class="home-jieda">
          <%
            com.tianmo.my.query.reply reply2=new reply();
            List<Reply> repliess=new ArrayList<>();
            repliess=reply2.selectquery(user);
            for (Reply r :repliess) {
          %>
          <li>
          <p>
          <span><%=r.getCreate_date()%></span>
          </p>
          <div class="home-dacontent">
            <%=r.getContentInform()%>
          </div>
        </li>
          <%
            }
          %>

          <!-- <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有回答任何问题</span></div> -->
        </ul>
      </div>
    </div>
  </div>
</div>

<div class="fly-footer">
  <p><a href="/loginPage" target="_blank">TianMo社区</a> 2021 &copy; <a href="/loginPage" target="_blank">xiaomin.com 出品</a></p>
</div>

<script src="/fly/res/layui/layui.js"></script>
<script>
layui.cache.page = 'user';
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