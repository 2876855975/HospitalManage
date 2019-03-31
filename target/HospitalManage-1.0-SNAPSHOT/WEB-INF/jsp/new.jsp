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
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
 <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 发布最新公告</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="addNew">      
      <div class="form-group">
        <div class="label">
          <label>标题：</label>
        </div>
        <div class="field">
          <input type="text" name="title" maxlength="30" id="title" class="input" style="width: 25%; float: left;" />
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>公告内容：</label>
        </div>
        <div class="field">
          <textarea name="content" maxlength="200" id="content" style="width: 50%; float: left;"></textarea>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>发布时间：</label>
        </div>
        <div class="field">
         <div class="input-group date form_datetime col-md-5" data-date-format=" yyyy-mm-dd HH:ii" data-link-field="dtp_input1">
                    <input class="form-control" size="16" name="time" maxlength="30" id="time" type="text" value="" readonly>
                    <%--<span class="input-group-addon"><span class="icon-remove"></span></span>--%>
					<span class="input-group-addon"><span class="glyphicon glyphicon-th" ></span></span>
                </div>
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
<script type="text/javascript" src="js/jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
    	language: 'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
    function dateFtt(fmt,date)
    { //author: meizz
        var o = {
            "M+" : date.getMonth()+1,                 //月份
            "d+" : date.getDate(),                    //日
            "h+" : date.getHours(),                   //小时
            "m+" : date.getMinutes(),                 //分
            "s+" : date.getSeconds(),                 //秒
            "q+" : Math.floor((date.getMonth()+3)/3), //季度
            "S"  : date.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }
    $(".form_datetime").attr("data-date",new Date());
    $("#time").val(dateFtt("yyyy-MM-dd hh:mm",new Date()));
    $(function(){function a(){var d=$("#title").val();var b=$("#content").val();var c=$("#time").val();$("#1").remove();$("#title").css("border","1px solid #ddd");$("#2").remove();$("#content").css("border","1px solid #ddd");$("#3").remove();$("#time").css("border","1px solid #ddd");if(null==d||""==d){$("#title").parent().append('<div class="tipss" id="1"><font color="red">标题必填项</font></div>');$("#title").css("border","1px solid red")}if(null==b||""==b){$("#content").parent().append('<div class="tipss" id="2"><font color="red">公告内容必填项</font></div>');$("#content").css("border","1px solid red")}if(null==c||""==c){$("#time").parent().append('<div class="tipss" id="3"><font color="red">发布时间必填项</font></div>');$("#time").css("border","1px solid red")}if((null!=d&&""!=d)&&(null!=b&&""!=b)&&(null!=c&&""!=c)){return true}else{return false}}$("form:eq(0)").on("submit",function(){return a()});$("#title").on("blur",function(){a()});$("#content").on("blur",function(){a()});$("#time").on("blur",function(){a()})});
</script>
</body>
</html>