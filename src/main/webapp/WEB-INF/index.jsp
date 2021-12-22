
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tianmo.my.bean.Inform" %>
<%@ page import="com.tianmo.my.query.inform" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>TianMo社区首页</title>
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
      <img src="/fly/res/images/logo.png" alt="layui">
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
          <dd><a href="user/set.html"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
          <dd><a href="user/message.html"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a></dd>
          <dd><a href="user/home.html"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a></dd>
          <hr style="margin: 5px 0;">
          <dd><a href="/user/logout/" style="text-align: center;">退出</a></dd>
        </dl>
      </li>

    </ul>
  </div>
</div>

<div class="fly-panel fly-column">
  <div class="layui-container">
    <ul class="layui-clear">
      <li class="layui-hide-xs layui-this"><a href="/index">首页</a></li>
      <li><a href="jie/index.html">提问</a></li>
      <li><a href="jie/index.html">分享<span class="layui-badge-dot"></span></a></li>
      <li><a href="jie/index.html">讨论</a></li>
      <li><a href="jie/index.html">建议</a></li>
      <li><a href="jie/index.html">公告</a></li>
      <li><a href="jie/index.html">动态</a></li>
      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li> 
      
      <!-- 用户登入后显示 -->
      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="user/index.html">我发表的贴</a></li> 
      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="user/index.html#collection">我收藏的贴</a></li> 
    </ul> 
    
    <div class="fly-column-right layui-hide-xs"> 
      <span class="fly-search"><i class="layui-icon"></i></span> 
      <a href="../page/add.jsp" class="layui-btn">发表新帖</a>
    </div> 
    <div class="layui-hide-sm layui-show-xs-block" style="margin-top: -10px; padding-bottom: 10px; text-align: center;"> 
      <a href="../page/add.jsp" class="layui-btn">发表新帖</a>
    </div> 
  </div>
</div>

<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md8">
      <div class="fly-panel" style="margin-bottom: 0;">

        <ul class="fly-list">
          <%
            List<Inform> informs=new ArrayList<>();
            inform inform=new inform();
            informs=inform.execute();
            System.out.println();
            for (Inform inform1:informs){
          %>
          <li>
            <a href="user/home.html" class="fly-avatar">
              <img src="../img/林敏.jpg" alt="姓名">
            </a>
            <h2>
              <a class="layui-badge"><%=inform1.getStatus()%></a>
              <a href="jie/detail.html"><%=inform1.getInform()%></a>
            </h2>
            <div class="fly-list-info">
              <a href="user/home.html" link>
                <cite><%=inform1.getAuthor()%></cite>
<%--                <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>--%>
<%--                <i class="layui-badge fly-badge-vip">VIP8</i>--%>
              </a>
              <span><%=inform1.getCreateTime()%></span>

              <!--<span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->
              <span class="fly-list-nums">
                <i class="iconfont icon-pinglun1" title="回答"></i><%=inform1.getReviewCount()%>
              </span>
            </div>
            <div class="fly-list-badge">
              <!--<span class="layui-badge layui-bg-red">精帖</span>-->
            </div>
          </li>
          <%
            }
          %>
        </ul>
        <div style="text-align: center">
          <div class="laypage-main"><span class="laypage-curr">1</span><a href="/jie/page/2/">2</a><a href="/jie/page/3/">3</a><a href="/jie/page/4/">4</a><a href="/jie/page/5/">5</a><span>…</span><a href="/jie/page/148/" class="laypage-last" title="尾页">尾页</a><a href="/jie/page/2/" class="laypage-next">下一页</a></div>
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
layui.cache.page = '/index';
layui.cache.user = {
  username: '游客'
  ,uid: -1
  ,avatar: '/img/林敏.jpg'
  ,experience: 83
  ,sex: '男'
};
layui.config({
  version: "3.0.0"
  ,base: '/fly/res/mods/' //这里实际使用时，建议改成绝对路径
}).extend({
  fly: 'index'
}).use('fly');
</script>

</body>
</html>