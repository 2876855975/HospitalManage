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
  <div class="panel-head"><strong class="icon-reorder"> 反馈管理</strong></div>
        <ul class="search">
        <li>
            <form action="/muliDelete" method="post">
              <button type="button"  class="button border-green" id="checkall"><span class="icon-check"></span> 全选</button>
              <button type="button" class="button border-red" id="allDel" ><span class="icon-all"></span> 批量删除</button>
                <input type="hidden" name="dels" id="de">
            </form>
        </li>
      </ul>
  </div>
  <table class="table table-hover text-center">
    <tr>
      <th width="5%">序号</th>
      <th width="10%">姓名</th>
      <th width="15%">反馈时间</th>
      <th width="50%">反馈内容</th>
      
    </tr>
     <c:forEach items="${PageInfo.datas}" var="Feedback">
    <tr>
       <td><input type="checkbox" name="id[]" value="${Feedback.id}" />${Feedback.id}</td>
       <td>${Feedback.fname}</td>
       <td>${Feedback.ftime}</td>
       <td>${Feedback.message}</td>
       </tr>
   
     </c:forEach>
     <tr>
        <td colspan="8">
            <div class="pagelist">
                <c:if test="${PageInfo.pageIndex != 1}">
                    <a href="./getFeedBack?pageNum=${PageInfo.prePage}&pageSize=${PageInfo.pageSize}">上一页</a>
                </c:if>
                <c:forEach items="${PageInfo.totalPage}" var="nav">
                    <c:if test="${nav == PageInfo.pageIndex}">
                        <span class="current">${nav}</span>
                    </c:if>
                    <c:if test="${nav != PageInfo.pageIndex}">
                        <a href="/getFeedBack?pageNum=${nav}&pageSize=${PageInfo.pageSize}">${nav}</a>
                    </c:if>
                </c:forEach>
                <c:if test="${PageInfo.pageIndex != PageInfo.lastPage}">
                    <a href="./getFeedBack?pageNum=${PageInfo.lastPage}&pageSize=${PageInfo.pageSize}">下一页</a>
                </c:if>
            </div>
         </td>
      </tr>
  </table>
</div>
<script type="text/javascript">
    var checkall = false;
    $("#checkall").click(function(){
        if(checkall){
            $("input[name='id[]']").each(function () {
                this.checked = false;
            });
            checkall = false;
        }else {
            $("input[name='id[]']").each(function () {
                this.checked = true;
            });
            checkall = true;
        }
    });
    $("#allDel").on("click",function () {
        var all = [];
        $("input[name='id[]']").each(function () {
            if(this.checked == true){
                all.push(this.value);
            }
        });
        if(all.length==0){
            alert("请先选择一条数据！");
        }else{
            if(confirm("确定要批量删除"+all.length+"条数据吗？")){
                $("#de").val(all);
                $("form:eq(0)").submit();
            }
        }
    });
</script>
</body></html>