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
				<h1 style="margin-top:100px;text-align: center;">유기견 목록</h1>
				<div class="d-flex justify-content-center">
					<table class="mt-4 table table-striped w-75 ">
						<thead>
							
							<tr>
								<th scope="col">이름</th>
								<th scope="col">생일</th>
								<th scope="col">성별</th>
								<th scope="col">종류</th>
							</tr>
							
						</thead>
						<tbody>
							<c:forEach var="l" items="${dog}">
							<c:if test="${not empty member}">
								<tr onclick="location.href='./detail?dogNo=${l.dogNo}'" style="cursor:pointer;">
							</c:if>
							<c:if test="${empty member}">
							<tr>
							</c:if>
								<th scope="row">${l.dogName}</th>
								<td>${l.dogBirth}</td>
								<td>
								<c:if test="${l.dogGender eq 0}">남자</c:if>
								<c:if test="${l.dogGender ne 0}">여자</c:if>
								</td>
								<td>${l.dogType}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</main>
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>

</body>
</html>