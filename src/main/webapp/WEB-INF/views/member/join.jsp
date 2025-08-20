<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
			<div id="content-wrapper" class="d-flex flex-column">
				<form:form method="post" modelAttribute="memberVO" enctype="multipart/form-data">
						<h2>회원가입</h2>
				
					  <div class="mb-4">
					    <label for="id" class="form-label">아이디</label>
					    <form:input path="memberId" cssClass="form-control"/>
					    <form:errors path="memberId"></form:errors>
					  </div>
					   <div class="mb-4">
					    <label for="password" class="form-label">비밀번호</label>
					    <form:input path="memberPassword" cssClass="form-control"/>
					    <form:errors path="memberPassword"></form:errors>
					  </div>
					  <div class="mb-4">
				  		<label for="passwordCheck" class="form-label">비밀번호 확인</label> 
						<input type="password" class="form-control" name="passwordCheck"
								id="passwordCheck" aria-describedby="passwordCheckHelp">
					    <form:errors path="passwordCheck"></form:errors>
					  </div>
					  <div class="mb-4">
					    <label for="name" class="form-label">이름</label>
					    <form:input path="memberName" cssClass="form-control"/>
					    <form:errors path="memberName"></form:errors>
					  </div>
					   <div class="mb-4">
					    <label for="phone" class="form-label">전화번호</label>
					    <form:input path="memberPhone" cssClass="form-control"/>
					    <form:errors path="memberPhone"></form:errors>
					  </div>
					   <div class="mb-4">
					    <label for="email" class="form-label">이메일</label>
					    <form:input path="memberEmail" cssClass="form-control"/>
					    <form:errors path="memberEmail"></form:errors>
					  </div>
					  <div class="mb-4 form-check">
					    <input type="checkbox" class="form-check-input" id="exampleCheck1">
					    <label class="form-check-label" for="exampleCheck1">Check me out</label>
					  </div>
					  <button type="submit" class="btn btn-primary">회원가입</button>
				</form:form>
			</div>
			</main>
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>

</body>
</html>