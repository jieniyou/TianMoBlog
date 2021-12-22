<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.tianmo.my.query.reply" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tianmo.my.bean.Reply" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="shortcut icon" href="/img/天魔1.png" type="image/x-icon">
  <title>信息查看页</title>
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
  String inform=request.getParameter("inform");

  Date date=new Date();
  String now=new SimpleDateFormat("yyyy-MM-dd").format(date);//yyyy-MM-dd hh:mm:ss
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

<div class="layui-hide-xs">
  <div class="fly-panel fly-column">
    <div class="layui-container">
      <ul class="layui-clear">
        <li class="layui-hide-xs"><a href="/index">首页</a></li>
        <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li> 
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
</div>

<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md8 content detail">
      <div class="fly-panel detail-box">
        <h1><%=inform%></h1>
        <div class="fly-detail-info">
          <span class="fly-list-nums">
            <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> 66</a>
            <i class="iconfont" title="人气">&#xe60b;</i> 99999
          </span>
        </div>
        <div class="detail-about">
          <a class="fly-avatar" href="../user/home.jsp?account=<%=account%>">
            <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="<%=account%>">
          </a>
          <div class="fly-detail-user">
            <a href="../user/home.html" class="fly-link">
              <cite><%=account%></cite>
              <i class="iconfont icon-renzheng" title="认证信息：{{ rows.user.approve }}"></i>
            </a>
            <span>2017-11-30</span>
          </div>
          <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
          </div>
        </div>
        <div class="detail-body photos">
          <p>
            “我认识你，永远记得你。那时候，你还很年轻，人人都说你美。现在，我是特意来告诉你，对我
            来说，我觉得现在你比年轻的时候更美。与你那时的面貌相比，我更爱你现在备受摧残的面容。”
            多年以后，他对她说。
          </p>
          <p>更新日志：</p>
<pre>
# 2021-12-23
* 加油努力
</pre>
        </div>
      </div>

      <div class="fly-panel detail-box" id="flyReply">
        <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
          <legend>回帖</legend>
        </fieldset>
<jsp:useBean id="reply" class="com.tianmo.my.bean.Reply"></jsp:useBean>
          <%
            com.tianmo.my.query.reply reply1=new reply();
            List<Reply> replies=new ArrayList<>();
            replies=reply1.executeQuery(account);
            for (Reply r :replies) {
          %>
        <ul class="jieda" id="jieda">
          <li data-id="111" class="jieda-daan">
            <a name="item-1111111111"></a>
            <div class="detail-about detail-about-reply">
              <a class="fly-avatar" href="">
                <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt=" ">
              </a>
              <div class="fly-detail-user">
                <a href="" class="fly-link">
                  <cite><%=r.getAuthor()%></cite>
                </a>
              </div>
              <div class="detail-hits">
                <span><%=r.getCreate_date()%></span>
              </div>
              <i class="iconfont icon-caina" title="最佳答案"></i>
            </div>
            <div class="detail-body jieda-body photos">
              <p><%=r.getContentInform()%></p>
            </div>
            <div class="jieda-reply">
              <span class="jieda-zan zanok" type="zan">
                <i class="iconfont icon-zan"></i>
                <em>66</em>
              </span>
              <span type="reply">
                <i class="iconfont icon-svgmoban53"></i>
                回复
              </span>
              <div class="jieda-admin">
<%--                <span type="del">删除</span>--%>
                <a href="/reply/del?contentInform=<%=r.getContentInform()%>">删除</a>
              </div>
            </div>
          </li>
        </ul>
<%
  }
%>

        <div class="layui-form layui-form-pane">

          <form action="/reply/add" method="post">
            <div class="layui-form-item layui-form-text">
              <a name="comment"></a>
              <div class="layui-input-block">
                <textarea id="L_content" name="contentInform" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
              </div>
            </div>
            <div class="layui-form-item">
              <input type="hidden" name="author" value="<%=user%>">
              <input type="hidden" name="create_date" value="<%=now%>">
              <input type="hidden" name="author2" value="<%=account%>">
              <input type="submit" class="layui-btn" lay-filter="*" value="提交回复">
            </div>
          </form>

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
}).use(['fly', 'face'], function(){
  var $ = layui.$
  ,fly = layui.fly;
  //如果你是采用模版自带的编辑器，你需要开启以下语句来解析。
  /*
  $('.detail-body').each(function(){
    var othis = $(this), html = othis.html();
    othis.html(fly.content(html));
  });
  */
});
</script>

</body>
</html>