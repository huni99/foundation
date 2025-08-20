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
			<div id="content" style="margin:100px auto;" class="w-75">
				<div id="content-wrapper" class="d-flex flex-column">
					<form:form method="post" modelAttribute="memberVO" enctype="multipart/form-data">
     						<h2>정보 수정</h2>
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

						  <div>
							  <label for="file" class="form-label">File</label>
							  <input type="file" class="" name="profile">
							  <form:errors path="profileVO"></form:errors>
						  </div>
							  
						  <div class="mb-4">
						  	<button type="submit" class="btn btn-primary">수정완료</button>
						  </div>
					</form:form>
				</div>
			</div>
			</main>
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>

</body>
</html>