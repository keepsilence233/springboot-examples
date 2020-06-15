<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <td>学生编号</td>
        <td>学生姓名</td>
        <td>学生年龄</td>
        <td>学生地址</td>
    </tr>
    <#list students as student>
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.age}</td>
            <td>${student.address}</td>
        </tr>
    </#list>
</table>
</body>
</html>