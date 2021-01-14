<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../../common/common.jsp"%>  

<%
	String cp = request.getContextPath();
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderDetail.css">
<title>MyPetDiary | 주문 상세 내역 </title>
</head>

<body>
	<div class="order-detail">
		<div class="order-detail-title">
			<h1>주문 상세보기</h1>
		</div>

		<%-- 상품 정보 테이블  --%>
		<div>
			<table class="order-detail-table">
				<thead>
					<tr>
						<th>이미지</th>
						<th>상품명</th>
						<th>가격</th>
						<th>수량</th>
						<th>적립금</th>
						<th>금액</th>
					</tr>
				</thead>

				<tbody class="order-detail-content">
					<c:set var="totalAmount" value="0" />
					<c:forEach var="shopinfo" items="${requestScope.shoplists}">
						<tr>
							<td width="20%">
								<img src="<%=cp%>/upload/${shopinfo.file_name}" alt="product-iamge">
							</td>

							<td>${shopinfo.name}</td>

							<td>
								<fmt:formatNumber value="${shopinfo.price}" pattern="###,###" />원
							</td>

							<td>
								<fmt:formatNumber value="${shopinfo.qty}" pattern="###,###" />개
							</td>
							
							<td>${shopinfo.p_point}</td>
	
							<td>
								<c:set var="amount" value="${shopinfo.price * shopinfo.qty}" /> 
								<c:set var="totalAmount" value="${totalAmount + amount}" /> 
								<fmt:formatNumber value="${amount}" pattern="###,###" />원
							</td>
						</tr>
						
						
					<table class="order-detail-amount-info">
						<tr>
							<th><span>최종 금액</span></th>
						</tr>
			
						<tr>
							<td>
								<c:set var="finalAmount" value="${totalAmount}" />
								<strong><fmt:formatNumber value="${finalAmount}" pattern="###,###" />원</strong>
							</td>
						</tr>
					</table>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<br>

		<%-- 주문자 정보 테이블  --%>
		<div>
			<table class="order-info-table">
				<thead>
					<tr>
						<th width="5%">ID</th>
						<th>이름</th>
						<th>주소</th>
						<th>핸드폰</th>
						<th>주문일자</th>
					</tr>
				</thead>

				<tbody class="order-info-content">
					<c:set var="totalAmount" value="0" />
					<c:forEach var="user" items="${requestScope.orders}">
						<tr>
							<td width="10%">${user.m_id}</td>
							<td width="10%">${user.name}</td>
							<td width="40%">${user.address1}${user.address2}</td>
							<td width="20%">${user.phone}</td>
							<td width="20%">${user.order_date}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<br><br><br>


		<div class="order-button-group">
			<button class="keep-shop-btn" id="keepShopping">쇼핑 계속하기</button>
		</div>
		
	</div>
	<br><br><br><br><br>

<script>
	const keepShopping = document.getElementById("keepShopping");

	keepShopping.addEventListener("click", function(e) {
		location.href = "<%=NoForm%>PRList";
	})
</script>
</body>
</html>