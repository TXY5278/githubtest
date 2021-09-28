<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        td {
            text-align: center;
            width: 125px;
        }

        .c2 {
            margin-left: 40px;
            margin-bottom: 40px;
        }
    </style>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript">

    </script>
    <script>

        $(function () {
            //全选或全不选
            $("#checkAll").click(function () {
                var check = $("#checkAll").prop("checked");
                $("input:checkbox").prop("checked", check);
            })
            $("#batchDel").click(function () {
                var len = $("input:checkbox:gt(0):checked").length;
                if (len <= 0) {
                    alert("请选择要删除的学生");
                    return;
                }
                var bo = confirm("你确定要删除选中的学生信息吗?");
                if (bo) {
                    /*
                    获取被选中的复选框对应的学生编号
                    将学生编号拼接成一个字符串1,2,3,4,...
                    然后将拼接的学生编号提交给控制层
                     */

                    var ids = "";
                    //将除第一个复选框的数据存入字符串中
                    $("input:checkbox:gt(0):checked").each(function () {
                        var id = $(this).val();
                        ids += "," + id;
                    })
                    ids = ids.substring(1);
                    console.log(ids);
                    location.href = "batchDelete.do?ids=" + ids;
                }
            })
			/*
			查询指定页面
			 */
			$("#btn").click(function () {
				var val = $("#in1").val();
				var val2=	$("#in2").val();
				var val3=	$("#in3").val();
				location.href="queryStu.do?currentPage="+val+"&sname="+val2+"&gender="+val3;
			})
			/*
			取消一个选择，则取消全选
			 */
			$("input:checkbox:gt(0)").click(function () {
				if(!$(this).is(":checked")){
					$("#checkAll").prop("checked",$(this).prop("checked") );
				}


			})
            $(".btn").click(function () {
                location.href="updateJsp.do?sid="+$(this).val();
            })
        })
    </script>
</head>
<body>
<br/>
<center>
    <form action="queryStu.do" method="get">
        姓名<input name="sname" value="${student.sname}" id="in2"/>&nbsp;&nbsp;&nbsp;
        性别
        <select name="gender" id="in3">
            <option value="-1">请选择</option>
            <option value="男" <c:if test="${student.gender=='男'}">selected</c:if>>男</option>
            <option value="女" <c:if test="${student.gender=='女'}">selected</c:if>>女</option>
        </select>&nbsp;&nbsp;&nbsp;
        <input type="submit" value="查询"/>
    </form>
    <br/>
</center>
<br/>
<input type="button" id="batchDel" value="批量删除" style="margin-left: 150px"/>
<br/>
<table border="1px" width="80%" align="center" cellpadding="0"
       cellspacing="0">
    <tr>
        <th><input type="checkbox" id="checkAll" />全选/全不选</th>
        <th>学号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>生日</th>
        <th>爱好</th>
        <th>头像</th>
        <th>操作</th>
        <c:forEach items="${page.pageData}" var="stu">
    <tr>
        <td><input type="checkbox" value="${stu.sid}"></td>
        <td>${stu.sid}</td>
        <td>${stu.sname}</td>
        <td>${stu.gender}</td>
        <td>${stu.sbir}</td>
        <td>${stu.hobby}</td>
        <td><img src="/upload/${stu.photo}" alt="" width="60px" height="40px" style="border-radius: 50%"></td>
        <td><button class="btn" value="${stu.sid}">修改</button></td>
    </tr>
    </c:forEach>
    </tr>
    <!-- 遍历学生的信息 -->

</table>
<br/><br/>
<center>
    <a href="queryStu.do?currentPage=1&sname=${student.sname}&gender=${student.gender}" class="c2">首页</a>
    <a href="queryStu.do?currentPage=${page.prePage}&sname=${student.sname}&gender=${student.gender}" class="c2">上一页</a>
    <a href="queryStu.do?currentPage=${page.nextPage}&sname=${student.sname}&gender=${student.gender}" class="c2">下一页</a>
    <a href="queryStu.do?currentPage=${page.countPage}&sname=${student.sname}&gender=${student.gender}" class="c2">尾页</a>
    <span class="c2">当前页码<input size="4" value="${page.currentPage}" style="border-style:none"/></span>
    <span class="c2">总记录数<input size="4" value="${page.countNum}" style="border-style:none"/></span>
	<span class="c2">总页数<input size="4" value="${page.countPage}" style="border-style:none"/></span>
	<span class="c2">跳转页数<input size="4" name="appoint" id="in1"/></span><button id="btn">点击跳转</button>
</center>
</body>
</html>