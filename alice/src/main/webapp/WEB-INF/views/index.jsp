<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<script type="text/javascript">
			window.location.href="<%=basePath%>login/login.do"
		</script>
	</head>
</html>