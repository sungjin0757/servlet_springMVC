<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>멤버 저장 Form</title>
    </head>

    <body>
        <center>
        <div>
            <form action="save" method="post">
                <div>
                    <label for="username">이름</label>
                    <input type="text" name="username" id="username" value="이름"/>
                </div>

                <div>
                    <label for="age">나이</label>
                    <input type="text" name="age" id="age" value="나이"/>
                </div>
                <div>
                     <button type="submit">전송</button>
                </div>
            </form>
        </div>
            <div class="col">
                <button onclick="location.href='/index.html'" type="button">목록으로</button>
             </div>
         </center>
    </body>
</html>