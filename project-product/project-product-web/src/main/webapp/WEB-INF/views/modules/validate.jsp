<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>this is validate page</title>
</head>
<body>
		<form action="/ssm/user/validateUser">
			邮箱 ：&nbsp <input name="userEmail"> <br>
			<form:errors path="userEmail"></form:errors>
			时间：&nbsp<input name="userDob"> <br>
			<input type="submit" value="提交"> <br>
		</form>
</body>
</html>