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
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>预约挂号记录：</strong></div>
  <div class="body-content">
      <table class="table table-hover text-center">
          <tr>
              <th width="10%">挂号数量</th>
              <th width="10%">起始时间</th>
              <th width="10%">结束时间</th>
              <th width="10%">挂号价格</th>

          </tr>
          <c:forEach items="${PageInfo.datas}" var="sch">
              <tr>
                  <td>${sch.total }</td>
                  <td>${sch.sTime }</td>
                  <td>${sch.eTime }</td>
                  <td>${sch.price }</td>
              </tr>
          </c:forEach>
          <c:if test="${PageInfo.datas.size() != 0}">
              <tr>
                  <td colspan="4">
                      <div class="pagelist">
                          <c:if test="${PageInfo.pageIndex != 1}">
                              <a href="./getS?pageNum=${PageInfo.prePage}">上一页</a>
                          </c:if>
                          <c:forEach items="${PageInfo.totalPage}" var="nav">
                              <c:if test="${nav == PageInfo.pageIndex}">
                                  <span class="current">${nav}</span>
                              </c:if>
                              <c:if test="${nav != PageInfo.pageIndex}">
                                  <a href="./getS?pageNum=${nav}">${nav}</a>
                              </c:if>
                          </c:forEach>
                          <c:if test="${PageInfo.pageIndex != PageInfo.lastPage}">
                              <a href="./getS?pageNum=${PageInfo.lastPage}">下一页</a>
                          </c:if>
                      </div>
                  </td>
              </tr>
          </c:if>
          <c:if test="${PageInfo.datas.size() == 0}">
              <tr>
                  <th colspan="8">
                      您还没有设置挂号
                  </th>
              </tr>
          </c:if>
      </table>
  </div>
</div>
<script type="text/javascript" src="js/jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
</body>
</html>