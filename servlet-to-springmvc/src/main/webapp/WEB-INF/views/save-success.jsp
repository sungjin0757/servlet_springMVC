<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
 <meta charset="UTF-8">
 <title>save-success</title>
</head>
<body>
<center>
<div>
 <span>id=${member.id}</span>
</div>
<div>
 <span>이름=${member.username}</span>
</div>
<div>
 <span>나이=${member.age}</span>
</div>
<button onclick="location.href='/index.html'" type="button">목록으로</button>
</center>
</body>
</html>