<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 修改科室信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="update">
      <div class="form-group">
        <div class="label">
          <label>科室编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" maxlength="20" value="${department.cNumber}" disabled onfocus=this.blur() style="width: 25%; float: left"  />
          <div class="tipss"><font color="red">不可修改</font></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>科室名字：</label>
        </div>
        <div class="field">
         <input type="text" class="input" maxlength="30" name="dName" id="dName" value="${department.dName}" style="width: 25%; float: left"  />
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>科室描述内容：</label>
        </div>
        <div class="field">
         <textarea name="dDec" id="dDec" maxlength="200" style="width: 50%; float: left" class="input" >${department.dDec}</textarea>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
            <input type="hidden" name="cNumber" value="${department.cNumber}"/>
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript">
    $(function(){function a(){var c=$("#dName").val();var b=$("#dDec").val();$("#dne").remove();$("#dName").css("border","1px solid #ddd");$("#dec").remove();$("#dDec").css("border","1px solid #ddd");if(null==c||""==c){$("#dName").parent().append('<div class="tipss" id="dne"><font color="red">科室名称必填项</font></div>');$("#dName").css("border","1px solid red")}if(null==b||""==b){$("#dDec").parent().append('<div class="tipss" id="dec"><font color="red">科室描述内容必填项</font></div>');$("#dDec").css("border","1px solid red")}if((null!=c&&""!=c)&&(null!=b&&""!=b)){return true}else{return false}}$("form:eq(0)").on("submit",function(){return a()});$("#dName").on("blur",function(){a()});$("#dDec").on("blur",function(){a()})});
</script>
</body></html>