<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册账号</title>
</head>
<body>
	<form action="UserServlet?action=regist" method="post">
		<table>
			<tr>
				<td>用户名</td><td><input id="username" type="text" name="username" onblur="checkUsername()"/></td>
				<td><div id="usernameMsg"></div></td>
			</tr>
			<tr>
				<td>密码</td><td><input id="pwd" type="password" name="password" onblur="ckpwd()"/></td>
				<td><div id="pwdMsg"></div></td>
			</tr>
			<tr>
				<td>邮箱</td><td><input id="email" type="email" name="email" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="注册" onclick="return ck()"></td>
			</tr>
		</table>
	</form>
	<button onclick="javascript:history.back(-1);">返回上一页</button>
</body>
<script type="text/javascript">
	/** 
	 * 得到XMLHttpRequest对象 
	 */
	function getajaxHttp() {
	    var xmlHttp;
	    try {
	        // Firefox, Opera 8.0+, Safari  
	        xmlHttp = new XMLHttpRequest();
	    } catch (e) {
	        // Internet Explorer  
	        try {
	            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
	        } catch (e) {
	            try {
	                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	            } catch (e) {
	                alert("您的浏览器不支持AJAX！");
	                return false;
	            }
	        }
	    }
	    return xmlHttp;
	}
	/** 
	 * 发送ajax请求 
	 * url--请求到服务器的URL 
	 * methodtype(post/get) 
	 * con (true(异步)|false(同步)) 
	 * functionName(回调方法名，不需要引号,这里只有成功的时候才调用) 
	 * (注意：这方法有二个参数，一个就是xmlhttp,一个就是要处理的对象) 
	 */
	function ajaxrequest(url, methodtype, con, functionName) {
	    //获取XMLHTTPRequest对象
	    var xmlhttp = getajaxHttp();
	    //设置回调函数（响应的时候调用的函数）
	    xmlhttp.onreadystatechange = function() {
	        //这个函数中的代码在什么时候被XMLHTTPRequest对象调用？
	        //当服务器响应时，XMLHTTPRequest对象会自动调用该回调方法
	        if (xmlhttp.readyState == 4) {
	            if (xmlhttp.status == 200) {
	                functionName(xmlhttp.responseText);
	            }
	        }
	    };
	    //创建请求
	    xmlhttp.open(methodtype, url, con);
	    //发送请求
	    xmlhttp.send();
	}
	function checkUsername() {
	    var username=document.getElementById('username').value;
	    //调用ajax请求Servlet
	    ajaxrequest("UserServlet?action=username&&username="+username,"POST",true,ckUsernameResponse);
	}
	function ckUsernameResponse(responseContents){
	    if (responseContents=='yes') {
	        document.getElementById('usernameMsg').innerHTML="<font color='red'>用户名存在</font>";
	        document.getElementById('username').style="background-color: red";
	    }else{
	        document.getElementById('usernameMsg').innerHTML="";
	        document.getElementById('username').style="background-color: white";
	    }
	}
	function ckpwd(){
		var pwd = document.getElementById('pwd');
		if(pwd.value.length<6){
			document.getElementById('pwdMsg').innerHTML="<font color='red'>密码不足6位</font>";
	        document.getElementById('pwd').style="background-color: red";
		}else{
	        document.getElementById('pwdMsg').innerHTML="";
	        document.getElementById('pwd').style="background-color: white";
	    }
	}
	function ck(){
		var pwd = document.getElementById('pwd');
		var un = document.getElementById('username');
		if(pwd.style.backgroundColor=="red"){
			alert("请输入正确格式");
			return false;
		}
		if(un.style.backgroundColor=="red"){
			alert("请输入正确格式");
			return false;
		}
		return true;
	}
</script>
</html>