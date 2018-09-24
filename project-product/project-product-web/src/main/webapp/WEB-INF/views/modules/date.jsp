<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/ssm/static/js/jquery-1.12.4.min.js"></script>
</head>
	<form >
	shijan <input type="text" name="date" id="date">
	<button type="submit">tijiao</button>
	</form>

<body>
	<script type="text/javascript">
	$(function(){
		alert("laile ");
		 var form = document.forms[0];//获取网页form里面的数据
		    console.log(form);
		    var searchDate = $(form).serialize();//进行url化
				$.ajax({       
					data : searchDate , 
					url:"/ssm/user/text",
					success:function(result){
				    $("body").html(result);
				}});
		});
   

</script>
</body>



</html>