<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="sb-nav-fixed">
	<c:import url="/WEB-INF/include/header.jsp"></c:import>
	<div id="layoutSidenav">
		<c:import url="/WEB-INF/include/sidebar.jsp"></c:import>
		<div id="layoutSidenav_content">
			<main>
			</main>
			<c:import url="/WEB-INF/include/footer.jsp"></c:import>
		</div>
	</div>

</body>
</html>