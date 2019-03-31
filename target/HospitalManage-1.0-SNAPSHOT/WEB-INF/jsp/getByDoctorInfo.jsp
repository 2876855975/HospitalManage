<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String context = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>添加医生</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/transition.js"></script>
<script src="js/collapse.js"></script>
<script src="js/ie-emulation-modes-warning.js"></script>
<script type="text/javascript" src="js/dropdown.js"></script>
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
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-pencil-square-o"></span> 修改医生信息</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" action="modifyDoctor" >
				<div class="form-group">
					<div class="label">
						<label>医生工号</label>
					</div>
					<div class="field">
						<input type="text" name="dNumber" maxlength="20" readonly="readonly" value="${user.dNumber }" style= "width: 25%; float: left;" class="input" />
						<div class="tipss"><font color="red">医生工号不可更改</font></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>医生姓名：</label>
					</div>
					<div class="field">
						<input type="text" name="name" maxlength="20" id="name" value="${user.name }"  style="width: 25%; float: left;" class="input" />
					</div>
					
				</div>
				<div class="form-group">
					<div class="label">
						<label>密码：</label>

					</div>
					<div class="field">
						<input type="text" name="dPwd" id="dPwd" maxlength="15" style="width: 25%; float: left;" class="input"
							   value="${user.dPwd}" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>所属科室编号：</label>
					</div>
					<div class="field">
						<input type="text" class="input" readonly="readonly" name="department.cNumber" value="${user.department.cNumber}"  style="width: 25%; float: left" type="text"  />
						<div class="tipss"><font color="red">所属科室不可更改</font></div>
					</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>医生描述：</label>
						</div>
						<div class="field">
							<textarea class="input" name="dInfo" id="dInfo" value="" maxlength="200" style="width: 50%; float: left" >${user.dInfo }</textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>职称：</label>
						</div>
						<div class="field">
							<input type="text" class="input" name="dResume" maxlength="100" id="dResume" maxlength="30" value="${user.dResume}"  style="width: 25%; float: left" type="text"  />
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>联系方式：</label>
						</div>
						<div class="field">
							<input type="text" class="input" name="dTel" maxlength="11" id="dTel" value="${user.dTel}"  style="width: 25%; float: left;" class="input"  />
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>邮箱：</label>
						</div>
						<div class="field">
							<input type="text" class="input" maxlength="30" readonly style="width: 25%; float: left" value="${user.dEmail }" />
							<button class="btn btn-default" style="width: 180px;height: 42px;margin-left: 5px" type="button" id="sendEmail">获取验证码&nbsp;</button>
							<button class="btn btn-default" style="width: 180px;height: 42px;margin-left: 5px" type="button" id="restEmail">更换邮箱&nbsp;</button>
							<c:if test="${errorEmail != null}"><div class="tipss" id="7"><font color="red">${errorEmail}</font></div></c:if>
						</div>
					</div>
					<div class="form-group" id="vdal" style="display: none">
						<div class="label">
							<label>验证码：</label>
						</div>
						<div class="field">
							<input type="text" class="input" name="code" id="code" maxlength="6" style="width: 25%; float: left;" />
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>快捷登录状态：</label>
						</div>
						<div class="field">
							<div class="tipss" style="font-weight: bold">
								<c:if test="${isQRUser == '未启用' }">${isQRUser}</c:if>
								<c:if test="${isQRUser == '已启用' }"><font style="color: green">${isQRUser}</font></c:if>
								</div>
							<button class="btn btn-default" style="width: 180px;height: 42px;margin-left: 5px" type="button" id="qrLog">
								<c:if test="${isQRUser == '未启用' }">立即启用快捷登录</c:if>
								<c:if test="${isQRUser == '已启用' }">更换快捷登录</c:if>&nbsp;</button>
							<c:if test="${isQRUser == '已启用' }">
								<button class="btn btn-default" style="width: 180px;height: 42px;margin-left: 5px" type="button" id="delQR">禁用快捷登录&nbsp;</button>
							</c:if>
						</div>
					</div>
					<div class="field" align="center">
						<button class="button bg-main icon-check-square-o" type="submit">
							提交</button>
					</div>
			</form>
		</div>

	<div id="model" style="position:fixed;top: 0px;width: 100%;height: 100%;background: rgba(0,0,0,0.6);z-index: 1;display: none">
		<div style="width: 290px;height: 300px;background: #fff;margin: 15% auto;z-index: 2;position: relative">
			<div>
				<p style="margin:0;padding-top:10px;text-align:center;font-weight: bold;font-size: 16px">请用微信扫一扫 匹配手机
				</p>
				<div style="text-align: center">
					<img id="imgCode"/>
				</div>
			</div>
			<div style="text-align: center;position: absolute;top: 0;margin: 45% 17%;width: 200px;display: none">
				<div style="font-size: 1.8rem;font-weight: 500">正在匹配...</div>
				<div>
					<div class="icon icon-spinner" style="display: inline-block;animation: springs 2s infinite linear;font-size: 1.5rem;transform-origin: center"></div>
				</div>
			</div>
			<div style="text-align: center;position: absolute;top: 0;margin: 45% 17%;width: 200px;display: none">
				<div style="font-size: 1.8rem;font-weight: 500"><span class="am-icon-check"></span>&nbsp;匹配成功！</div>
			</div>
			<div style="text-align: center;position: absolute;top: 0;margin: 45% 17%;width: 200px;display: none">
				<div style="font-size: 1.8rem;font-weight: 500">匹配失败！</div>
			</div>
			<div style="text-align: center;position: absolute;top: 0;margin: 45% 17%;width: 200px;display: none">
				<div style="font-size: 1.8rem;font-weight: 500">匹配被取消！</div>
			</div>
		</div>
	</div>
	<div id="model2" style="position:fixed;top: 0px;width: 100%;height: 100%;background: rgba(0,0,0,0.6);z-index: 1;display: none">
		<div style="width: 40%;height: 50%;background: #fff;margin: 10% auto;padding:10px;z-index: 2;position: relative" id="al">
			<div style="text-align: center">
				<h2>更换邮箱</h2>
			</div>
			<div style="margin: 5px 0;text-align: center">
				<div style="padding: 5px 0px 5px 10px;display: inline-block">
					<label>原邮箱：</label>
				</div>
				<div style="padding: 5px 0;display: inline-block;width: 40%;" >
					<input type="text" class="input" readonly value="${user.dEmail}" style="width: 100%;height: 35px" type="text"  />
				</div>
				<button class="btn btn-default" style="width: 30%;height: 35px" type="button" id="sendEmail2">获取验证码&nbsp;</button>
			</div>
			<div style="margin: 5px 0 0 8%">
				<div style="padding: 5px 0px 5px 10px;display: inline-block">
					<label>新邮箱：</label>
				</div>
				<div style="padding: 5px 0;display: inline-block;width: 43%;" >
					<input type="text" class="input" id="newEmail" style="width: 100%;height: 35px" type="text"  />
				</div>
			</div>
			<div style="margin: 5px 0 0 8%">
				<div style="padding: 5px 0px 5px 10px;display: inline-block">
					<label>验证码：</label>
				</div>
				<div style="padding: 5px 0;display: inline-block;width: 43%;" >
					<input type="text" class="input" id="newCode" style="width: 100%;height: 35px" type="text"  />
				</div>
			</div>
			<div style="margin: 30px 0 0 40%;">
				<button class="btn btn-default icon-check-square-o" style="background: #ccc;width: 100px" id="doRest" type="button">
					更改</button>
			</div>
		</div>
		<div style="width: 40%;height: 50%;background: #fff;margin: 10% auto;padding:10px;z-index: 2;position: relative;display: none" id="al2">
			<div style="text-align: center;margin-top: 15%">
				<h2>更改成功</h2>
			</div>
		</div>
		<div style="width: 40%;height: 50%;background: #fff;margin: 10% auto;padding:10px;z-index: 2;position: relative;display: none" id="al3">
			<div style="text-align: center;margin-top: 15%">
				<h2>更改失败</h2>
			</div>
			<div style="text-align: center;margin: 5px">
				<p >原因可能：验证码输入有误、服务器异常、动态码已经使用</p>
			</div>
		</div>
</div>
	<script type="text/javascript" src="js/indexR.js"></script>
	<c:if test="${succ != null}">
			<script type="text/javascript">
				alert("保存成功！");
                window.parent.location.href="/";
			</script>
	</c:if>
	<c:if test="${errorMsg != null}">
			<script type="text/javascript">
				alert("${errorMsg}");
			</script>
	</c:if>
	<script type="text/javascript">
        $(function(){function b(){var f=/^[1][3,4,5,7,8][0-9]{9}$/;var g=$("#name").val();var e=$("#dPwd").val();var i=$("#dInfo").val();var d=$("#dResume").val();var c=$("#dTel").val();var h=$("#code").val();$("#2").remove();$("#name").css("border","1px solid #ddd");$("#3").remove();$("#dPwd").css("border","1px solid #ddd");$("#4").remove();$("#dInfo").css("border","1px solid #ddd");$("#5").remove();$("#dResume").css("border","1px solid #ddd");$("#6").remove();$("#dTel").css("border","1px solid #ddd");$("#8").remove();$("#code").css("border","1px solid #ddd");if(null==g||""==g){$("#name").parent().append('<div class="tipss" id="2"><font color="red">医生姓名必填项</font></div>');$("#name").css("border","1px solid red")}if(null==e||""==e){$("#dPwd").parent().append('<div class="tipss" id="3"><font color="red">初始密码必填项</font></div>');$("#dPwd").css("border","1px solid red")}if(null==i||""==i){$("#dInfo").parent().append('<div class="tipss" id="4"><font color="red">医生描述必填项</font></div>');$("#dInfo").css("border","1px solid red")}if(null==d||""==d){$("#dResume").parent().append('<div class="tipss" id="5"><font color="red">职称必填项</font></div>');$("#dResume").css("border","1px solid red")}if(null==c||""==c){$("#dTel").parent().append('<div class="tipss" id="6"><font color="red">联系方式必填项</font></div>');$("#dTel").css("border","1px solid red")}else{if(!c.match(f)){$("#dTel").parent().append('<div class="tipss" id="6"><font color="red">手机号码格式有误</font></div>');$("#dTel").css("border","1px solid red")}}if(null==h||""==h){$("#vdal").show();$("#code").parent().append('<div class="tipss" id="8"><font color="red">验证码必填项</font></div>');$("#code").css("border","1px solid red")}if((null!=g&&""!=g)&&(null!=e&&""!=e)&&(null!=i&&""!=i)&&(null!=d&&""!=d)&&(null!=c&&""!=c)&&(c.match(f))&&(null!=h&&""!=h)){return true}else{return false}}$("form:eq(0)").on("submit",function(){return b()});$("#name").on("blur",function(){b()});$("#dPwd").on("blur",function(){b()});$("#dInfo").on("blur",function(){b()});$("#dResume").on("blur",function(){b()});$("#dTel").on("blur",function(){b()});$("#code").on("blur",function(){b()});$("#sendEmail").on("click",function(){$("#sendEmail").append('<div class="icon icon-spinner" style="display: inline-block;animation: springs 2s infinite linear;font-size: 1.4rem;transform-origin: center" id="fe"></div>');$("#sendEmail").addClass("disabled");$.ajax({type:"post",url:"/valiEmail.json",dataType:"json",success:function(d){if(d.status=="1"){var c=120;var e=setInterval(function(){c--;$("#sendEmail").html(c+"秒后重新获取")},1000);setTimeout(function(){clearInterval(e);$("#sendEmail").html("获取验证码&nbsp;");$("#sendEmail").removeClass("disabled")},120000);setTimeout(function(){$("#fe").remove();$("#vdal").show()},1000)}else{$("#fe").remove();$("#sendEmail").removeClass("disabled");alert("获取验证码出现问题！请过一会再试")}},error:function(c){$("#fe").remove();$("#sendEmail").removeClass("disabled");alert("服务器异常！")}})});$("#sendEmail2").on("click",function(){$("#sendEmail2").append('<div class="icon icon-spinner" style="display: inline-block;animation: springs 2s infinite linear;font-size: 1.4rem;transform-origin: center" id="fe2"></div>');$("#sendEmail2").addClass("disabled");$.ajax({type:"post",url:"/valiEmail.json",dataType:"json",success:function(d){if(d.status=="1"){var c=120;var e=setInterval(function(){c--;$("#sendEmail2").html(c+"秒后重新获取")},1000);setTimeout(function(){clearInterval(e);$("#sendEmail2").html("获取验证码&nbsp;");$("#sendEmail2").removeClass("disabled")},120000);setTimeout(function(){$("#fe2").remove();$("#vdal").show()},1000)}else{$("#fe2").remove();$("#sendEmail2").removeClass("disabled");alert("获取验证码出现问题！请过一会再试")}},error:function(c){$("#fe2").remove();$("#sendEmail2").removeClass("disabled");alert("服务器异常！")}})});$("#restEmail").on("click",function(){$("#model2").fadeIn(100)});$("#model2").on("click",function(){$("#model2").fadeOut(200)});$("#model2 > div").on("click",function(c){c.stopPropagation()});$("#newEmail").on("blur",function(){a()});$("#newCode").on("blur",function(){a()});function a(){var d="^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";var c=$("#newEmail").val();var e=$("#newCode").val();$("#r1").remove();$("#newEmail").css("border","1px solid #ddd");$("#r2").remove();$("#newCode").css("border","1px solid #ddd");if(null==c||""==c){$("#newEmail").parent().parent().append('<div class="tipss" id="r1" style="display: inline-block"><font color="red">请填写新邮箱</font></div>');$("#newEmail").css("border","1px solid red")}else{if(!c.match(d)){$("#newEmail").parent().parent().append('<div class="tipss" id="r1" style="display: inline-block"><font color="red">请填写正确邮箱格式</font></div>');$("#newEmail").css("border","1px solid red")}}if(null==e||""==e){$("#newCode").parent().parent().append('<div class="tipss" id="r2" style="display: inline-block"><font color="red">请填写验证码</font></div>');$("#newCode").css("border","1px solid red")}if((null!=c||""!=c)&&c.match(d)&&(null!=e||""!=e)){return true}else{return false}}$("#doRest").on("click",function(){if(a()){var c=$("#newEmail").val();var d=$("#newCode").val();$.ajax({type:"post",url:"/modifyDoctorEmail",data:{"email":c,"code":d},dataType:"json",success:function(e){if(e.status=="1"){$("#newEmail").val("");$("#newCode").val("");$("#al").hide();$("#al3").hide();$("#al2").fadeIn(100);setTimeout(function(){$("#model2").fadeOut(200);window.location.href="/getByDoctor"},10000)}else{$("#al").hide();$("#al2").hide();$("#al3").fadeIn(100);setTimeout(function(){$("#al3").hide();$("#al2").hide();$("#al").fadeIn(200)},10000)}}})}})});
	</script>
</body>
</html>