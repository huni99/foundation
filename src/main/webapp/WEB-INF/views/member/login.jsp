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
			<div id="content">
				<form method="post" enctype="multipart/form-data">
					<div class="mb-3">
						 <label for="id" class="form-label">아이디</label>
						 <input type="text" class="form-control" name="memberId">
					</div>
					<div class="mb-3">
						 <label for="password" class="form-label">비밀번호</label>
						 <input type="text" class="form-control" name="memberPassword">
					</div>
					
					<button type="submit" class="btn btn-primary">로그인</button>
				</form>
			</div>
			</main>
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>

</body>
</html>