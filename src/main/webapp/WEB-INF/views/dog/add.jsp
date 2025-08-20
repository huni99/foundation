<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<main class="justify-content-center">
				<div class="w-75" style="margin:80px auto;">
					<form:form method="post" enctype="multipart/form-data"
						modelAttribute="dogVO">
						<div class="mb-3">
							<label for="dogName" class="form-label">유기견 이름</label>
							<form:input path="dogName" cssClass="form-control" />
							<form:errors path="dogName"></form:errors>
						</div>
						<div class="mb-3">
							<label for="dogBirth" class="form-label">유기견 생일</label> <input
								type="date" name="dogBirth" id="dogBirth">
							<form:errors path="dogBirth"></form:errors>
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">유기견 성별</label>
							<input type='radio' name='dogGender' value='0' checked />남&nbsp;
							<input type='radio' name='dogGender' value='1' />여&nbsp;
							<form:errors path="dogGender"></form:errors>
						</div>
						<div class="mb-3">
							<label for="dogType" class="form-label">유기견 종류</label>
							<form:input path="dogType" cssClass="form-control" />
							<form:errors path="dogType"></form:errors>
						</div>
						<div class="mb-3">
							<label for="dogInoculation" class="form-label">유기견 예방 접종
								유무</label>
							<input type='radio' name='dogInoculation' value='1' checked />접종 O&nbsp;
							<input type='radio' name='dogInoculation' value='0' />접종 X&nbsp;
							<form:errors path="dogInoculation"></form:errors>
						</div>
						<div class="mb-3">
							<label for="dogPrice" class="form-label">입양 가격</label>
							<form:input path="dogPrice" cssClass="form-control" />
							<form:errors path="dogPrice"></form:errors>
						</div>
						<div class="mb-3">
							<label for="dogInfo" class="form-label">비고</label>
							<form:textarea path="dogInfo" cssClass="form-control" />
							<form:errors path="dogInfo"></form:errors>
						</div>
						<div class="mb-3">
							<label for="dogFile" class="form-label">파일 첨부</label>
							<input type="file" name="dogFile" Class="form-control" />
								<form:errors path="dogFileVO"></form:errors>
						</div>

						<button type="submit" class="btn btn-primary">Submit</button>
					</form:form>
				</div>
			</main>
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>

</body>
</html>