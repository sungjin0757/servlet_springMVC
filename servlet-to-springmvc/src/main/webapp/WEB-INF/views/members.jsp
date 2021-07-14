<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
 <meta charset="UTF-8">
 <title>members</title>
</head>
<body>
<center>
<a href="/index.html">메인</a>
<table border="1">
 <thead>
 <th>id</th>
 <th>username</th>
 <th>age</th>
 </thead>
 <tbody>
 <c:forEach var="item" items="${members}">
 <tr> <td>${item.id}</td>
 <td>${item.username}</td>
 <td>${item.age}</td>
 </tr>
 </c:forEach>
 </tbody>
</table>
</center>
</body>
</html>