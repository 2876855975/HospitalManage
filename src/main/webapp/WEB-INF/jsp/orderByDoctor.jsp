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
  </div>
  <table class="table table-hover text-center">
    <tr>
      <th width="10%">预约编号</th>
      <th width="10%">用户名</th>
      <th width="10%">医生名</th>
      <th width="10%">预约费用</th>
      <th width="25%">预约时间</th>
      <th width="15%">预约状态</th>
      
    </tr>
     <c:forEach items="${PageInfo.datas}" var="allOrder">
        <tr>
           <td><%--<input type="checkbox" name="id[]" value="1" />--%>${allOrder.oNumber}</td>
           <td>${allOrder.user.uname}</td>
           <td>${allOrder.sch.doctor.name}</td>
           <td>${allOrder.price}</td>
           <td>${allOrder.oTime}</td>
           <td><c:if test="${allOrder.status == 1}">成功</c:if><c:if test="${allOrder.status == 0}">失败</c:if></td>
        </tr>
     </c:forEach>
      <c:if test="${PageInfo.datas.size() != 0}">
        <tr>
            <td colspan="8">
                <div class="pagelist">
                    <c:if test="${PageInfo.pageIndex != 1}">
                        <a href="./getOrederByDoctor?pageNum=${PageInfo.prePage}&pageSize=${PageInfo.pageSize}">上一页</a>
                    </c:if>
                     <c:forEach items="${PageInfo.totalPage}" var="nav">
                        <c:if test="${nav == PageInfo.pageIndex}">
                          <span class="current">${nav}</span>
                        </c:if>
                        <c:if test="${nav != PageInfo.pageIndex}">
                            <a href="./getOrederByDoctor?pageNum=${nav}&pageSize=${PageInfo.pageSize}">${nav}</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${PageInfo.pageIndex != PageInfo.lastPage}">
                        <a href="./getOrederByDoctor?pageNum=${PageInfo.lastPage}&pageSize=${PageInfo.pageSize}">下一页</a>
                    </c:if>
                </div>
            </td>
      </tr>
      </c:if>
      <c:if test="${PageInfo.datas.size() == 0}">
        <tr>
            <th colspan="8">
                您还没有预约名单
            </th>
      </tr>
      </c:if>
  </table>
</div>
</body>
</html>