<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>激活账号</title>
</head>
<body>
<div align="center">
	<h3>尊敬的会员${user.username },您好:</h3>
	<h4>您的注册密码为：${user.password },激活账号后可凭用户名和密码在首页登陆。</h4>
	<h4>点击激活账号：<button onclick="window.location.href='UserServlet?action=activation&&username=${user.username}'">激活账号</button></h4>
</div>
</body>
</html>