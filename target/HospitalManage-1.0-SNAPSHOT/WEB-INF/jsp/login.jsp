<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>欢迎登录 Graduate医院预约后台管理中心</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/jquery.cookie.js"></script>
    <style>
        @keyframes springs{
            0% {
                -webkit-transform: rotate(0);
                transform: rotate(0);
            }
            100% {
                -webkit-transform: rotate(359deg);
                transform: rotate(359deg);
            }
        }
    </style>
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <form id="for" action="/doLogin" method="post">
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1>Graduate医院预约后台管理中心</h1></div>
                <div class="panel-body" style="padding:10px 30px">
                    <div class="form-group">
                        <c:if test="${error==1}"><div style="color: red;padding: 0 10px 5px">用户名或密码不正确</div></c:if>
                        <c:if test="${error4==1}"><div style="color: red;padding: 0 10px 5px">用户名不能为空</div></c:if>
                        <c:if test="${error6==1}"><div style="color: red;padding: 0 10px 5px">该用户已经登录</div></c:if>
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big" name="name" id="name" <c:if test="${error==1 || error4==1}">style="border: 1px solid red" </c:if> maxlength="25" placeholder="登录账号" data-validate="required:请填写账号" value="${name}" />
                            <span class="icon icon-user margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <c:if test="${error2==1}"><div style="color: red;padding: 0 10px 5px">密码不能为空</div></c:if>
                        <div class="field field-icon-right">
                            <input type="password" class="input input-big" name="pwd" id="pwd" placeholder="登录密码" <c:if test="${error==1 || error2==1}">style="border: 1px solid red"</c:if> maxlength="25" data-validate="required:请填写密码" />
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <c:if test="${error5==1}"><div style="color: red;padding: 0 10px 5px">验证码不能为空</div></c:if>
                        <c:if test="${error5==2}"><div style="color: red;padding: 0 10px 5px">验证码不正确</div></c:if>
                        <div class="field">
                            <input type="text" class="input input-big" name="code" autocomplete="off" placeholder="填写右侧的验证码" <c:if test="${error5==1 || error5==2}">style="border: 1px solid red"</c:if> maxlength="4" data-validate="required:请填写右侧的验证码" />
                           <img id="vacode" src="/validatecode1.jpg" title="看不清？点击换一张" class="passcode" style="height:43px;cursor:pointer;width: 40%;height: 2.75rem" onclick="this.src='validatecode'+Math.random()+'.jpg'">
                        </div>
                    </div>
                    <div class="form-group">
					&nbsp;&nbsp;
                        <label> <input id="rememberUser" name="rememberUser" type="checkbox" value="remember-me">
						记住密码
						</label>
                        &nbsp;&nbsp;&nbsp;&nbsp;
						 <label> <input type="radio" id="ad" checked name="status" value="1">
						管理员
						</label> &nbsp;&nbsp;&nbsp;&nbsp;
						<label> <input type="radio" id="do" name="status" value="2">
						医生
						</label>
					</div>
                </div>

                <div style="padding:15px 30px 15px"><input type="submit" id="login" class="button button-block bg-main text-big input-big" value="登录"/>
                </div>
                <div style="text-align: center;padding: 15px 30px 30px">
                    <a style="cursor: pointer" id="qrLog">《快捷二维码登录》</a>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>
<div id="model" style="position:fixed;top: 0px;width: 100%;height: 100%;background: rgba(0,0,0,0.6);z-index: 1;display: none">
    <div style="width: 290px;height: 300px;background: #fff;margin: 15% auto;z-index: 2;position: relative">
        <div>
            <p style="margin:0;padding-top:10px;text-align:center;font-weight: bold;font-size: 16px">请用微信扫一扫 验证手机
            </p>
            <div style="text-align: center">
                <img id="imgCode"/>
            </div>
        </div>
        <div style="text-align: center;position: absolute;top: 0;margin: 45% 17%;width: 200px;display: none">
            <div style="font-size: 1.5rem;font-weight: 500">正在验证...</div>
            <div>
                <div class="icon icon-spinner" style="display: inline-block;animation: springs 2s infinite linear;font-size: 1.4rem;transform-origin: center"></div>
            </div>
        </div>
        <div style="text-align: center;position: absolute;top: 0;margin: 45% 17%;width: 200px;display: none">
            <div style="font-size: 1.5rem;font-weight: 500"><span class="am-icon-check"></span>&nbsp;验证成功！</div>
        </div>
        <div style="text-align: center;position: absolute;top: 0;margin: 45% 17%;width: 200px;display: none">
            <div style="font-size: 1.5rem;font-weight: 500">验证失败！</div>
        </div>
        <div style="text-align: center;position: absolute;top: 0;margin: 45% 17%;width: 200px;display: none">
            <div style="font-size: 0.9rem;font-weight: 500">没有找到该手机的匹配帐号！</div>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/indexE.js"></script>
<script type="text/javascript">
    /*记住用户名和密码*/
    $(function() {
        if ($.cookie("rememberUser")) {
            $("#rememberUser").attr("checked", true);
            $("#name").val($.cookie("name"));
            $("#pwd").val($.cookie("pwd"));
            if(null != $.cookie("ads") && "" != $.cookie("ads") && $.cookie("ads")  == "true"){
                $("#ad")[0].checked = true;
                $("#do")[0].checked = false;
            }
            if(null != $.cookie("doc") && "" != $.cookie("doc") && $.cookie("doc") == "true"){
                $("#ad")[0].checked = false;
                $("#do")[0].checked = true;
            }
        }
        $("#login").on("click",function () {
            saveUserInfo();
            $("#for").submit();
        })
    });
    //如果$.cookie("rememberUser")为true,即把cookie里存的userName，passWord的值赋给id是username,userpwd的input；
    function saveUserInfo() {
        if ($("#rememberUser").prop("checked") == true) {
            var userName = $("#name").val();
            var passWord = $("#pwd").val();
            var ad = $("#ad")[0].checked;
            var doc = $("#do")[0].checked;
            $.cookie("ads", "", {
                expires: -1
            }); // 存储一个带7天期限的 cookie
            $.cookie("doc", "", {
                expires: -1
            }); // 存储一个带7天期限的 cookie
            $.cookie("rememberUser", "true", {
                expires: 7
            }); // 存储一个带7天期限的 cookie
            $.cookie("name", userName, {
                expires: 7
            }); // 存储一个带7天期限的 cookie
            $.cookie("pwd", passWord, {
                expires: 7
            }); // 存储一个带7天期限的 cookie
            $.cookie("ads", ad, {
                expires: 7
            }); // 存储一个带7天期限的 cookie
            $.cookie("doc", doc, {
                expires: 7
            }); // 存储一个带7天期限的 cookie
        } else {
            $.cookie("rememberUser", "false", {
                expires: -1
            }); // 删除 cookie
            $.cookie("name", '', {
                expires: -1
            });
            $.cookie("pwd", '', {
                expires: -1
            });
            $.cookie("ads", "", {
                expires: -1
            }); // 存储一个带7天期限的 cookie
            $.cookie("doc", "", {
                expires: -1
            }); // 存储一个带7天期限的 cookie
        }
    }
    //如果$("#rememberUser").prop("checked") == true，把id为username，userpwd的值赋给cookie里的userName，passWord，并设置有效期是七天
</script>
</body>
</html>