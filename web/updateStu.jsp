<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
    <title>Insert title here</title>
</head>
<body>
<form action="updateStu.do" method="post" enctype="multipart/form-data">
    <input type="hidden" name="sid" value="${student.sid}"/>
    姓名:<input type="text" name="sname" value="${student.sname}"/><br/><br/>
    性别:男<input type="radio" value="男" name="gender"
               <c:if test="${student.gender == '男'}">checked</c:if>  />
    女<input type="radio" value="女" name="gender"
            <c:if test="${student.gender == '女'}">checked</c:if> /><br/><br/>
    生日:<input class="Wdate" value="${student.sbir}" name="sbir" type="text"
              onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"><br/><br/>
    爱好:
    抽烟<input type="checkbox" value="抽烟" name="hobby"
             <c:if test="${fn:contains(student.hobby, '抽烟')}">checked</c:if>  />
    喝酒<input type="checkbox" value="喝酒" name="hobby"
             <c:if test="${fn:contains(student.hobby, '喝酒')}">checked</c:if> />
    rap<input type="checkbox" value="rap" name="hobby"
              <c:if test="${fn:contains(student.hobby, 'rap')}">checked</c:if> />
    烫头<input type="checkbox" value="烫头" name="hobby"
             <c:if test="${fn:contains(student.hobby, '烫头')}">checked</c:if> /><br/><br/>
    头像:<input type="hidden" name="oldPhoto" value="${student.photo}">
    <input type="file" name="file">
    <img src="/upload/${student.photo}" height="50px" width="60px"/><br/><br/>
    <input type="submit" value="修改学生">
</form>
</body>
</html>