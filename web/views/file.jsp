<%--
  Created by IntelliJ IDEA.
  User: eastj
  Date: 2024/4/28
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/mall/fileUploadServlet" method="post"enctype="multipart/form-data">
    请选择图片：<input type="file" name="imgName"><br>
    <button>提交</button>

</form>

</body>
</html>
