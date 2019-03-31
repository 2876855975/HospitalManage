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
  <style>
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
      -webkit-appearance: none;
    }
    input[type="number"]{
      -moz-appearance: textfield;
    }
  </style>
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 添加科室信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="/addDepart">
      <div class="form-group">
        <div class="label">
          <label>科室编号：</label>
        </div>
        <div class="field">
          <input type="text" name="cNumber" id="cNumber" maxlength="20" class="input" style="width: 25%; float: left;" />
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>科室名字：</label>
        </div>
        <div class="field">
         <input type="text" class="input"  name="dName" maxlength="30" id="dName" style="width: 25%; float: left;"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>科室描述内容：</label>
        </div>
        <div class="field">
         <textarea name="dDec" id="dDec" maxlength="200" style="width: 50%; float: left;"></textarea>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
<c:if test="${errorMsg != null}">
  <script type="text/javascript">
    alert('${errorMsg}');
  </script>
</c:if>
<script type="text/javascript">
    $(function(){function a(){var d=$("#cNumber").val();var c=$("#dName").val();var b=$("#dDec").val();$("#cne").remove();$("#cNumber").css("border","1px solid #ddd");$("#dne").remove();$("#dName").css("border","1px solid #ddd");$("#dec").remove();$("#dDec").css("border","1px solid #ddd");if(null==d||""==d){$("#cNumber").parent().append('<div class="tipss" id="cne"><font color="red">科室编号必填项</font></div>');$("#cNumber").css("border","1px solid red")}if(null==c||""==c){$("#dName").parent().append('<div class="tipss" id="dne"><font color="red">科室名称必填项</font></div>');$("#dName").css("border","1px solid red")}if(null==b||""==b){$("#dDec").parent().append('<div class="tipss" id="dec"><font color="red">科室描述内容必填项</font></div>');$("#dDec").css("border","1px solid red")}if((null!=d&&""!=d)&&(null!=c&&""!=c)&&(null!=b&&""!=b)){return true}else{return false}}$("form:eq(0)").on("submit",function(){return a()});$("#cNumber").on("blur",function(){a()});$("#dName").on("blur",function(){a()});$("#dDec").on("blur",function(){a()})});
</script>
</body>
</html>