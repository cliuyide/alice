<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://localhost:8080/alice/resources/alice/base/jquery-1.8.3.js"></script>
</head>
<body>
	<script type="text/javascript">
		function preview() {
			$("body").append(
					"<div id=\"explorer\" style=\"display:none;background-color:red\">"+
					"<div><span>标题</span><div><img src=\"<%=basePath%>\/resources\/image\/exit.png\"> <img src=\"<%=basePath%>\/resources\/image\/min.png\"></div></div>"+
					"<iframe name=\"selectorFrame\" id=\"selectorFrame\" width=\"100%\" height=\"90%\" frameborder=\"0\" scrolling=\"auto\" src=\"\" ></iframe></div>");
			var dialog=document.getElementById("explorer");
			dialog.style.display="block";
			document.getElementById("explorer").onmousedown=function(){console.log("11111111");}		
			
		}
	</script>
</body>
</html>