<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../../common/common.jsp"%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderList.css">
<title>MyPetDiary | 주문 내역</title>
</head>

<body>
	<div class="order-list"> 
			<div class="order-list-title">
				<h1>주문 내역</h1>
			</div>
			
			<br>
			
			<div class="order-member-info">
				<h2>${sessionScope.loginfo.name}(${sessionScope.loginfo.id})님의 이전 주문 내역</h2>
			</div>
			
			<%-- 상품 정보 테이블  --%>
			<div>
				<table class="order-product-list"> 
					<thead>
							<tr>
								<th>주문 번호</th>
								<th>주문 일자</th>
								<th>배송 정보</th>
								<th>상세 보기</th>
							</tr>
					</thead>
				
					<tbody class="order-product-list__content">
						<c:forEach var="shopinfo" items="${sessionScope.listsForMem}">
							<tr>
								<td>${shopinfo.o_id}</td>
								<td>${shopinfo.order_date}</td>
								<td>${shopinfo.address1} ${shopinfo.address2}</td>
								<td>
									<a href="<%=NoForm%>ORDetailView&o_id=${shopinfo.o_id}">상세 보기</a>
								</td>
							</tr>
					</c:forEach>
				</tbody>
				</table>
				<br><br>
				
				<div class="error"><span >${errmsg}</span></div>
				
				<br><br>
			
			<div class="order-button-group">
 				<button class="keep-shop-btn" id="keepShopping">쇼핑 계속하기</button>
			</div>

		</div>
	</div>
	
	<br><br><br>
	
<script>
	const keepShopping = document.getElementById("keepShopping");
	
	keepShopping.addEventListener("click", function(e) {
		location.href = "<%=NoForm%>PRList";
	})
</script>
</body>
</html>