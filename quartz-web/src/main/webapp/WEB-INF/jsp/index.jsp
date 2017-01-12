<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<form action="add.do" method="post">
		name:<input type="text" name="name" value="name1">
		group:<input type="text" name="group" value="group1">
		trigger表达式: <input type="text" name="trigger">
		
		param:<input type="text" name="param">
		<input type="submit" value="添加">
	</form>
	-------------------------------<br/>
	<form action="delete.do" method="post">
		name:<input type="text" name="name" value="name1">
		group:<input type="text" name="group" value="group1">
		<input type="submit" value="删除">
	</form>
</body>


</html>
