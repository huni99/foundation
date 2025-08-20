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
			<h2 style="text-align:center; margin-top:60px; font-size:50px">마이페이지</h2>
			<div style="text-align:center;">
				<img class="img-profile rounded-circle"	src="/files/member/${member.profileVO.saveName }" style="margin-top:50px; border-style:solid; border-width:0.5px; width:250px;">
			</div>
			<div style="text-align:center; margin-top:30px; font-size:30px">${member.memberName }</div>
			<div style="text-align:center; margin-top:30px; font-size:20px">${member.memberPhone }</div>
			<div style="text-align:center; margin-top:30px; font-size:20px">${member.memberEmail }</div>
			
			<div class="mb-4" style="text-align:center; margin-top:50px;">
				<input type="hidden" name="memberNo" value="${memberNo }">
		  		<a type="submit" class="btn btn-primary" href="update?memberNo=${memberNo}">수정</a>
		    </div>
			
			</main>
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>

</body>
</html>