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
  <div class="panel-head"><strong class="icon-reorder"> 预约信息</strong></div>
    <form id="formm" action="getAllOreder" method="post">
        <input type="hidden" id="pageNum" name="pageNum" value="1"/>
        <input type="hidden" id="pageSize" name="pageSize" value="11"/>
        <select id="chose" name="chose" id="s_istop" class="input" style="width:100px; line-height:17px;display:inline-block">
            <option <c:if test="${chose==3}">selected</c:if> value="3">医生名</option>
            <option <c:if test="${chose==2}">selected</c:if> value="2">用户名</option>
            <option <c:if test="${chose==1}">selected</c:if> value="1">预约编号</option>
        </select>
        <input type="text" placeholder="请输入搜索关键字" name="info" value="${info}" id="info" class="input"
               style="width:250px; line-height:17px;display:inline-block" maxlength="30"/>
        <input type="submit" class="button border-main icon-search" value="搜索"/>
    </form>
  </div>
  <table class="table table-hover text-center">
    <tr>
      <th width="10%">预约编号</th>
      <th width="10%">用户名</th>
      <th width="10%">医生名</th>
      <th width="10%">预约费用</th>
      <th width="25%">预约时间</th>
      <th width="15%">预约状态<!--<font color="red" size="1">*1:成功;0:失败</font>--></th>
      
    </tr>
     <c:forEach items="${PageInfo.datas}" var="allOrder">
    <tr>
       <td><%--<input type="checkbox" name="id[]" value="1" />--%>${allOrder.oNumber}</td>
       <td>${allOrder.user.uname}</td>
       <td>${allOrder.sch.doctor.name}</td>
       <td>${allOrder.price}</td>
       <td>${allOrder.oTime}</td>
       <td><c:if test="${allOrder.status==1}">成功</c:if>
           <c:if test="${allOrder.status==0}">失败</c:if></td>
    </tr>
     </c:forEach>
        <tr>
        <td colspan="8">
            <div class="pagelist">
                <c:if test="${PageInfo.pageIndex != 1}">
                    <a href="javascript:prepage(${PageInfo.prePage},${chose},'${info}')">上一页</a>
                </c:if>
                <c:forEach items="${PageInfo.totalPage}" var="nav">
                    <c:if test="${nav == PageInfo.pageIndex}">
                        <span class="current">${nav}</span>
                    </c:if>
                    <c:if test="${nav != PageInfo.pageIndex}">
                        <a href="javascript:nav(${nav},${chose},'${info}')" >${nav}</a>
                    </c:if>
                </c:forEach>
                <c:if test="${PageInfo.pageIndex != PageInfo.lastPage}">
                    <a href="javascript:lastpage(${PageInfo.lastPage},${chose},'${info}')">下一页</a>
                </c:if>
            </div>
        </td>
      </tr>
  </table>
</div>
<script type="text/javascript">

$("#checkall").click(function(){ 
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

function DelSelect(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	
}

function nav(nav,chose,info){
    $("#pageNum").val(nav);
    $("#pageSize").val(6);
    $("#chose").val(chose);
    $("#info").val(info);

//    alert($("#info").val())
    $("#formm").submit();
}

function prepage(pre,chose,info){
    $("#pageNum").val(pre);
    $("#pageSize").val(6);
    $("#chose").val(chose);
    $("#info").val(info);
    $("#formm").submit();

}

function lastpage(last,chose,info){
    $("#pageNum").val(last);
    $("#pageSize").val(6);
    $("#chose").val(chose);
    $("#info").val(info);
    $("#formm").submit();
}

</script>
</body></html>