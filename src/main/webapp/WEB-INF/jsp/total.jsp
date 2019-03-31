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
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 号源设置：</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="addSch">
      <div class="form-group">
        <div class="label">
          <label>挂号数量</label>
        </div>
        <div class="field">
      <input type="text" class="input" name="total" id="total" maxlength="10" style="width: 25%; float: left;" />
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>挂号时间</label>
        </div>
        <div class="field">
         <div class='input-group date date form_datetime col-md-5' id='datetimepicker9'>
                 <input type='text' class="form-control" id="start" maxlength="50" name="sTime" />
                 <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar">
                    </span>
                        起始时间
                </span>
         </div>
           <div class='input-group date date form_datetime col-md-5' maxlength="50" style="margin: 10px 0 25px" id='datetimepicker8'>
                <input type='text' class="form-control" id="end" name="eTime"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar">
                    </span>
                        结束时间
                </span>
            </div>
      </div>
       </div>
       <div class="form-group">
        <div class="label">
          <label>挂号价格</label>
        </div>
        <div class="field">
         <input type="text" class="input" name="price" id="price" maxlength="30" style="width: 25%; float: left; value=" />
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit" > 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>

<script type="text/javascript" src="js/jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
 <script type="text/javascript">
     $(function () {
         $('#datetimepicker9').datetimepicker({
             viewMode: 'years'
         });
     });	
     $(function () {
         $('#datetimepicker8').datetimepicker({
             viewMode: 'years'
         });
     });
     $(function(){function a(){var e=/^([1-9]*[1-9]+)|([1-9]+[0-9]{1,n})$/;var d=/^[1-9]+\d+(\.\d+)?$/;var g=$("#total").val();var h=$("#start").val();var c=$("#end").val();var f=$("#price").val();$("#1").remove();$("#total").css("border","1px solid #ddd");$("#2").remove();$("#start").css("border","1px solid #ddd");$("#3").remove();$("#end").css("border","1px solid #ddd");$("#4").remove();$("#price").css("border","1px solid #ddd");if(null==g||""==g){$("#total").parent().append('<div class="tipss" id="1"><font color="red">挂号数量必填项</font></div>');$("#total").css("border","1px solid red")}else{if(!g.match(e)){$("#total").parent().append('<div class="tipss" id="1"><font color="red">挂号数量格式有误</font></div>');$("#total").css("border","1px solid red")}}if(null==h||""==h){$("#start").parent().append('<div class="tipss" id="2"><font color="red">起始时间必填项</font></div>');$("#start").css("border","1px solid red")}if(null==c||""==c){$("#end").parent().append('<div class="tipss" id="3"><font color="red">结束时间必填项</font></div>');$("#end").css("border","1px solid red")}if((null!=h&&""!=h)&&(null!=c&&""!=c)){if(!b(c,h)){$("#start").parent().append('<div class="tipss" id="2"><font color="red">起始时间不能大于结束时间</font></div>');$("#start").css("border","1px solid red");$("#end").css("border","1px solid red")}}if(null==f||""==f){$("#price").parent().append('<div class="tipss" id="4"><font color="red">挂号价格必填项</font></div>');$("#price").css("border","1px solid red")}else{if(!f.match(d)){$("#price").parent().append('<div class="tipss" id="4"><font color="red">挂号价格格式有误</font></div>');$("#price").css("border","1px solid red")}}if((null!=g&&""!=g)&&g.match(e)&&(null!=h&&""!=h)&&(null!=c&&""!=c)&&b(c,h)&&(null!=f&&""!=f)&&f.match(d)){return true}else{return false}}$("form:eq(0)").on("submit",function(){return a()});function b(d,c){return(parseInt(Date.parse(d))>parseInt(Date.parse(c)))}$("#total").on("blur",function(){a()});$("#start").on("blur",function(){a()});$("#end").on("blur",function(){a()});$("#price").on("blur",function(){a()})});
 </script>
</body>
</html>