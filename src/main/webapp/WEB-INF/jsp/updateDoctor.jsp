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
			<form method="post" class="form-x" action="updateDoctor">
				<div class="form-group">
					<div class="label">
						<label>医生工号</label>
					</div>
					<div class="field">
						<input type="text" name="dN" id="dNumber" maxlength="20" value="${doctor.dNumber }" style= "width: 25%; float: left;" disabled onfocus=this.blur() class="input"/>
						<div class="tipss"><font color="red">工号不可更改</font></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>医生姓名：</label>
					</div>
					<div class="field">
						<input type="text" name="name" id="name" maxlength="20" value="${doctor.name }"  style="width: 25%; float: left;" class="input" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>所属科室：</label>
					</div>
					<div class="field">
						<select name="department.cNumber" id="cNumber" class="input w50">
							<option value="-1">请选择</option>
							<c:forEach items="${department}" var="department">
								<option value="${department.cNumber }">${department.dName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
					<div class="form-group">
						<div class="label">
							<label>医生描述：</label>
						</div>
						<div class="field">
							<textarea class="input" name="dInfo" id="dInfo" value="" maxlength="200" style="width: 50%; float: left" >${doctor.dInfo }</textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>职称：</label>
						</div>
						<div class="field">
							<input type="text" class="input" name="dResume" id="dResume" maxlength="150" value="${doctor.dResume}"  style="width: 25%; float: left" type="text"  />
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>联系方式：</label>
						</div>
						<div class="field">
							<input type="text" class="input" name="dTel" id="dTel" maxlength="11" value="${doctor.dTel}"  style="width: 25%; float: left;" class="input"  />
						</div>
					</div>

				<div class="form-group">
					<div class="label">
						<label>邮箱：</label>
					</div>
					<div class="field">
						<input type="text" class="input" name="dEmail" maxlength="30" id="dEmail" style="width: 25%; float: left" value="${doctor.dEmail }" />
						<button class="btn btn-default" style="width: 180px;height: 42px;margin-left: 5px" type="button" id="sendEmail">获取验证码&nbsp;</button>
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
					<div class="field" align="center">
						<input type="hidden" name="dNumber" value="${doctor.dNumber }"/>
						<button class="button bg-main icon-check-square-o" type="submit">
							提交</button>
					</div>
			</form>
		</div>
	</div>
	</div>

	<script type="text/javascript">
        $(function(){$("#cNumber").val("${doctor.department.cNumber}");function a(){var b="^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";var j=/^[1][3,4,5,7,8][0-9]{9}$/;var c=$("#name").val();var f=$("#cNumber").val();var i=$("#dInfo").val();var h=$("#dResume").val();var g=$("#dTel").val();var e=$("#dEmail").val();var d=$("#code").val();$("#2").remove();$("#name").css("border","1px solid #ddd");$("#cne").remove();$("#cNumber").css("border","1px solid #ddd");$("#4").remove();$("#dInfo").css("border","1px solid #ddd");$("#5").remove();$("#dResume").css("border","1px solid #ddd");$("#6").remove();$("#dTel").css("border","1px solid #ddd");$("#7").remove();$("#dEmail").css("border","1px solid #ddd");$("#8").remove();$("#code").css("border","1px solid #ddd");if(null==c||""==c){$("#name").parent().append('<div class="tipss" id="2"><font color="red">医生姓名必填项</font></div>');$("#name").css("border","1px solid red")}if(null==f||""==f||"-1"==f){$("#cNumber").parent().append('<div class="tipss" id="cne"><font color="red">所属科室必填项</font></div>');$("#cNumber").css("border","1px solid red")}if(null==i||""==i){$("#dInfo").parent().append('<div class="tipss" id="4"><font color="red">医生描述必填项</font></div>');$("#dInfo").css("border","1px solid red")}if(null==h||""==h){$("#dResume").parent().append('<div class="tipss" id="5"><font color="red">职称必填项</font></div>');$("#dResume").css("border","1px solid red")}if(null==g||""==g){$("#dTel").parent().append('<div class="tipss" id="6"><font color="red">联系方式必填项</font></div>');$("#dTel").css("border","1px solid red")}else{if(!g.match(j)){$("#dTel").parent().append('<div class="tipss" id="6"><font color="red">手机号码格式有误</font></div>');$("#dTel").css("border","1px solid red")}}if(null==e||""==e){$("#dEmail").parent().append('<div class="tipss" id="7"><font color="red">邮箱必填项</font></div>');$("#dEmail").css("border","1px solid red")}else{if(!e.match(b)){$("#dEmail").parent().append('<div class="tipss" id="7"><font color="red">邮箱格式有误</font></div>');$("#dEmail").css("border","1px solid red")}}if(null==d||""==d){$("#vdal").show();$("#code").parent().append('<div class="tipss" id="8"><font color="red">验证码必填项</font></div>');$("#code").css("border","1px solid red")}if((null!=c&&""!=c)&&(null!=f&&""!=f)&&(null!=i&&""!=i)&&(null!=h&&""!=h)&&(null!=g&&""!=g)&&(g.match(j))&&(null!=e&&""!=e)&&e.match(b)&&(null!=d&&""!=d)){return true}else{return false}}$("form:eq(0)").on("submit",function(){return a()});$("#cNumber").on("blur",function(){a()});$("#name").on("blur",function(){a()});$("#dInfo").on("blur",function(){a()});$("#dResume").on("blur",function(){a()});$("#dTel").on("blur",function(){a()});$("#dEmail").on("blur",function(){a()});$("#code").on("blur",function(){a()});$("#sendEmail").on("click",function(){var c=$("#dEmail").val();var b="^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";if(c.match(b)){$("#sendEmail").append('<div class="icon icon-spinner" style="display: inline-block;animation: springs 2s infinite linear;font-size: 1.4rem;transform-origin: center" id="fe"></div>');$("#sendEmail").addClass("disabled");$.ajax({type:"post",url:"/valiEmail.json",dataType:"json",data:{"email":c},success:function(f){if(f.status=="1"){var e=120;var d=setInterval(function(){e--;$("#sendEmail").html(e+"秒后重新获取")},1000);setTimeout(function(){clearInterval(d);$("#sendEmail").html("获取验证码&nbsp;");$("#sendEmail").removeClass("disabled")},120000);setTimeout(function(){$("#fe").remove();$("#vdal").show()},1000)}else{$("#fe").remove();$("#sendEmail").removeClass("disabled");alert("获取验证码出现问题！请过一会再试")}},error:function(d){$("#fe").remove();$("#sendEmail").removeClass("disabled");alert("服务器异常！")}})}else{alert("请输入正确邮箱格式！")}})});
	</script>
</body>
</html>