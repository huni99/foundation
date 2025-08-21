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
				<h1 style="margin-top:100px;text-align: center;">유기견 </h1>
				<div class="d-flex justify-content-center">
					<table class="mt-4 table table-striped w-75 ">
						<thead>
							
							<tr >
								<th scope="col">이름</th>
								<th scope="col">생일</th>
								<th scope="col">성별</th>
								<th scope="col">종류</th>
								<th scope="col">예방접종 여부</th>
								<th scope="col">입양가격</th>
								<th scope="col">사진</th>
								<th scope="col">총 후원 금액</th>
								
							</tr>
							
						</thead>
						<tbody style=" vertical-align: middle;">
						
							<tr >
								<th scope="row">${dogVO.dogName}</th>
								<td>${dogVO.dogBirth}</td>
								<td>
								<c:if test="${dogVO.dogGender eq 0}">남자</c:if>
								<c:if test="${dogVO.dogGender ne 0}">여자</c:if>
								</td>
								<td>${dogVO.dogType}</td>
								<td>
								<c:if test="${dogVO.dogInoculation eq false}">접종 x</c:if>
								<c:if test="${dogVO.dogInoculation ne false}">접종 o</c:if>
								</td>
								<td>${dogVO.dogPrice}</td>
								<td><img src="/files/dog/${dogVO.dogFileVO.saveName}" style="width:100px;height:100px;"></td>
								<td></td>
							</tr>
							<tr>
								<td>비고</td>
								<td colspan="4">${dogVO.dogInfo}</td>
							</tr>
					
						</tbody>
					</table>
					<div>
						<form id="frm">
							<input id="value" type="hidden" value="${dogVO.dogNo}">
							
						</form>
						<button id="btn-cart">즐겨찾기</button>
						<button id="btn-adopt">입양하기</button>
						<button id="btn-sponsor" data-dogno="${dogVO.dogNo}">후원하기</button>
						<c:if test="${sessionScope.donationSuccess eq true}">
						    <script>alert("후원되었습니다 🎉");</script>
						    <c:remove var="donationSuccess" scope="session"/>
						</c:if>
						
							
					</div>
				</div>
			</main>
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>
	<script type="text/javascript" src="/js/dog/dog_detail.js"></script>
	<script type="text/javascript" src="/js/donation/donation.js"></script>
</body>
</html>