<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script>if (window !== top) top.location.replace(location.href);</script>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="/img/天魔1.png" rel="icon">
    <title>TianMo用户登录</title>
    <link rel="stylesheet" href="/assets/libs/layui/css/layui.css"/>
    <link rel="stylesheet" href="/assets/module/admin.css?v=318">
    <style>
        body {
            background-image: url("/assets/images/bg-login02.jpg");
            background-repeat: no-repeat;
            background-size: cover;
            min-height: 100vh;
        }

        body:before {
            content: "";
            background-color: rgba(0, 0, 0, .2);
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
        }

        .login-wrapper {
            max-width: 420px;
            padding: 20px;
            margin: 0 auto;
            position: relative;
            box-sizing: border-box;
            z-index: 2;
        }

        .login-wrapper > .layui-form {
            padding: 25px 30px;
            background-color: rgba(255, 255, 255, 0.1);
            box-shadow: 0 3px 6px -1px rgba(0, 0, 0, 0.19);
            box-sizing: border-box;
            border-radius: 4px;
        }

        .login-wrapper > .layui-form > h2 {
            color: #008aa9;
            font-size: 18px;
            text-align: center;
            margin-bottom: 25px;
        }

        .login-wrapper > .layui-form > .layui-form-item {
            margin-bottom: 25px;
            position: relative;
        }

        .login-wrapper > .layui-form > .layui-form-item:last-child {
            margin-bottom: 0;
        }

        .login-wrapper > .layui-form > .layui-form-item > .layui-input {
            background: rgba(0,0,0,.4);
            color: lime;
            height: 46px;
            line-height: 46px;
            border-radius: 22px !important;
        }

        .login-wrapper .layui-input-icon-group > .layui-input {
            padding-left: 46px;
        }

        .login-wrapper .layui-input-icon-group > .layui-icon {
            width: 46px;
            height: 46px;
            line-height: 46px;
            font-size: 20px;
            color: #909399;
            position: absolute;
            left: 0;
            top: 0;
            text-align: center;
        }

        .login-wrapper > .layui-form > .layui-form-item.login-captcha-group {
            padding-right: 135px;
        }

        .login-wrapper > .layui-form > .layui-form-item.login-captcha-group > .login-captcha {
            height: 46px;
            width: 120px;
            cursor: pointer;
            box-sizing: border-box;
            border: 1px solid #e6e6e6;
            border-radius: 22px !important;
            position: absolute;
            right: 0;
            top: 0;
        }

        .login-wrapper > .layui-form > .layui-form-item > .layui-form-checkbox {
            margin: 0 !important;
            padding-left: 25px;
        }

        .login-wrapper > .layui-form > .layui-form-item > .layui-form-checkbox > .layui-icon {
            width: 15px !important;
            height: 15px !important;
        }

        .login-wrapper > .layui-form .layui-btn-fluid {
            height: 48px;
            line-height: 48px;
            font-size: 16px;
            border-radius: 2px !important;
        }

        .login-wrapper > .layui-form > .layui-form-item.login-oauth-group > a > .layui-icon {
            font-size: 26px;
        }

        .login-copyright {
            color: #eee;
            padding-bottom: 20px;
            text-align: center;
            position: relative;
            z-index: 1;
        }

        @media screen and (min-height: 550px) {
            .login-wrapper {
                margin: -250px auto 0;
                position: absolute;
                top: 50%;
                left: 0;
                right: 0;
                width: 100%;
            }

            .login-copyright {
                position: absolute;
                bottom: 0;
                right: 0;
                left: 0;
            }
        }

        .layui-btn {
            background-color: #5FB878;
            border-color: #5FB878;
        }

        .layui-link {
            color: #a220c3 !important;
        }
    </style>
</head>
<body>
<div class="login-wrapper layui-anim layui-anim-scale layui-hide">
    <form class="layui-form">
        <h2>SIGN IN</h2>
        <div class="layui-form-item layui-input-icon-group">
            <i class="layui-icon layui-icon-username"></i>
            <input class="layui-input" name="account" placeholder="请输入登录账号" value="林敏" autocomplete="off"
                   lay-verType="tips" lay-verify="required" required/>
        </div>
        <div class="layui-form-item layui-input-icon-group">
            <i class="layui-icon layui-icon-password"></i>
            <input class="layui-input" name="password" placeholder="请输入登录密码" value="123456" type="password"
                   lay-verType="tips" lay-verify="required" required/>
        </div>
       <div class="layui-form-item layui-input-icon-group login-captcha-group">
            <i class="layui-icon layui-icon-auz"></i>
            <input id="code" class="layui-input" name="code" placeholder="请输入验证码" autocomplete="off"
                   lay-verType="tips" lay-verify="required" required/>
            <img class="login-captcha" alt="" src="/captcha"/>
        </div>
        <div class="layui-form-item">
            <input type="checkbox" name="remember" title="remember" lay-skin="primary" checked>
            <a href="/reg" class="layui-link pull-right">sign up</a>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn layui-btn-fluid" lay-filter="loginSubmit" lay-submit>SIGN IN</button>
        </div>
    </form>
</div>
<div class="login-copyright">copyright © 2021 TianMo all rights reserved.</div>

<!-- js部分 -->
<script type="text/javascript" src="/assets/libs/layui/layui.js"></script>
<script type="text/javascript" src="/assets/js/common.js?v=318"></script>
<script>
    layui.use(['layer', 'form'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        $('.login-wrapper').removeClass('layui-hide');

        /* 表单提交  Ajax */
        form.on('submit(loginSubmit)', function (obj) {
            // 打开一个加载动画
            var loadIndex = layer.load(2);

            $.post('/login', obj.field, function (res) {
                layer.close(loadIndex);
                if (res.code === 200) {
                    layer.msg('登录成功', {icon: 1, time: 1500}, function () {
                        location.replace('/index');
                    });
                } else {
                  // 清空验证码输入框
                  $('#code').val("");
                  // 刷新验证码
                  $('img.login-captcha').attr("src", captchaUrl + '?t=' + (new Date).getTime());
                  layer.msg(res.msg, {icon: 2, anim: 6});
                }
            }, 'json');
            return false;
        });

        /* 图形验证码 */
        var captchaUrl = '/captcha';
        $('img.login-captcha').click(function () {
            this.src = captchaUrl + '?t=' + (new Date).getTime();
        }).trigger('click');

    });
</script>
</body>
</html>