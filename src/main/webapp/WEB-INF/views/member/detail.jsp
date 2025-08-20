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
	<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
	<div id="layoutSidenav">
		<c:import url="/WEB-INF/views/include/sidebar.jsp"></c:import>
		<div id="layoutSidenav_content">
			<main>
			<!-- Contents Start -->
			<div class="alert alert-primary" role="alert">
					<div>
					<img class="img-profile rounded-circle"	src="/files/member/${member.profileVO.saveName }">
					</div>
					<div>${member.memberName }</div>
					<div>${member.memberPhone }</div>
					<div>${member.memberEmail }</div>
			
			</main>
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>

</body>
</html>