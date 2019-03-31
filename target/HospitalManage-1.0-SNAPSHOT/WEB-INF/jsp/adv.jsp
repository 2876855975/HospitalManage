<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 科室信息</strong></div>
    <div class="padding border-bottom">
        <form id="formm" action="/find" method="post">
            <input type="hidden" id="pageNum" name="pageNum" value="1"/>
            <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}"/>
            <select id="chose" name="chose" id="s_istop" class="input" style="width:100px; line-height:17px;display:inline-block">
            <option <c:if test="${chose==1}">selected</c:if> value="1">科室编号</option>
            <option <c:if test="${chose==2}">selected</c:if> value="2">科室名字</option>
        </select>
        <input type="text" placeholder="请输入搜索关键字" id="info" name="info" id="keywords" maxlength="30" value="${info}" class="input"
               style="width:250px; line-height:17px;display:inline-block"/>
            <input class="button border-main icon-search"type="submit" value="搜索"/>
        </form>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th width="10%">科室编号</th>
            <th width="15%">名称</th>
            <th width="20%">描述</th>
            <th width="15%">操作</th>
        </tr>
        <c:if test="${PageInfo.datas.size() == 0}">
            <tr>
                <th colspan="4">未找到一条数据</th>
            </tr>
        </c:if>
        <c:forEach items="${PageInfo.datas}" var="department">
            <tr>

                <td>${department.cNumber}</td>
                <td>${department.dName}</td>
                <td style="word-break: break-all;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;padding-bottom: 0;
        overflow: hidden;">${department.dDec}</td>

                <td>
                    <div class="button-group">
                        <a class="button border-main" href="javascript:void(0)" onclick="update('${department.cNumber}')"><span
                                class="icon-edit"></span> 修改</a>
                        <a class="button border-red" href="javascript:void(0)"
                           onclick="del('${department.cNumber}')"><span class="icon-trash-o"></span> 删除</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${PageInfo.datas.size() != 0}">
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
                            <a href="javascript:nav(${nav},${chose},'${info}')">${nav}</a>
                        </c:if>
                        </c:forEach>
                            <c:if test="${PageInfo.pageIndex != PageInfo.lastPage}">
                                <a href="javascript:lastpage(${PageInfo.lastPage},${chose},'${info}')">下一页</a>
                            </c:if>
                    </div>
                </td>
            </tr>
        </c:if>
    </table>
</div>
<script type="text/javascript">
    function del(id) {
        if (confirm("您确定要删除吗?")) {
            if (id != "") {
                window.location.href = "delByid?cNumber=" + id + "";
            }
        }
    }

    function update(id) {
        if (id != ""){
            window.location.href = "updateByid?cNumber=" + id + "";
        }
    }

    function changesearch() {
        var chose = document.getElementById("s_istop").value;
        var info = document.getElementById("keywords").value;
        /* alert(chose+"  "+info) */
        window.location.href = "find?info=" + info + "&chose=" + chose + "&pageNum="+${PageInfo.pageIndex}+
        "&pageSize=" +${PageInfo.pageSize};

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

</body>
</html>