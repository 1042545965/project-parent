<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限分配</title>
<!--图标样式-->

<link rel="stylesheet" type="text/css" href="/ssm/static/jquerytree/css/bootstrap.min.css" />
<!--主要样式-->
<link rel="stylesheet" type="text/css" href="/ssm/static/jquerytree/css/style.css" />
<script type="text/javascript" src="/ssm/static/jquerytree/js/jquery-1.7.2.min.js"></script>
</head>
<body>

	<!-- <div class="checkbox"> <input type="checkbox" onclick="$('input[type =checkbox ]').attr('checked',this.checked);"> 系统设置</div> -->
		<div class="tree well" style="">
		  <ul>
		   <li>
		    <span><i class="icon-folder-open"></i>权限配置</span>
		    <ul>
		      
		    <c:forEach var="FunctionList" items="${authFunctionList }" >
		     <li>
		      <span>
			      <div class="checkbox"> 
			      	<input type="checkbox" class="fun"> ${FunctionList.functionName }
			      </div>
		      </span> 
		      <ul>
			      <c:forEach var="auth" items="${FunctionList.authFunctionList }">
			       <li>
			        <span>
				        <div class="checkbox"> 
				        	<input type="checkbox" class="fun"> ${auth.functionName }
				        </div>
			        </span> 
			       </li>
			       </c:forEach>
		      </ul>
		     </li>
		     </c:forEach>
		    
		    </ul>
		   </li>
		  </ul>
		 </div>
		 
		 <script type="text/javascript">
		 	$(".fun").click(function(){
		 		$(this).parents('span').siblings('ul').find('input[type =checkbox ]').attr('checked',this.checked);
		 		/* alert($(this).parents('span').siblings('ul').find('input[type =checkbox ]').length);  siblings('li') .find('input[type =checkbox ]')*/
		 		   var str = $(this).parent().parent().parent().siblings('li').find('input[type =checkbox ]:checked').length; 
				if (this.checked) {
					$(this).parents("li").children('span').find('input[type =checkbox ]').attr('checked',true);
				}else {
					 if((str)==0){
						$(this).parents("li").children('span').find('input[type =checkbox ]').attr('checked',false);
					} 
				}
		 	});
		 		
		 
		 </script>
		 
	<!-- 	<a href="/ssm/initialize/insert">baocun</a> -->
	
</body>
</html>