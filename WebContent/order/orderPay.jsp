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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderPay.css">
<title>MyPetDiary | 결제</title>
</head>
<body>

	<div class="order-pay"> 
			
		<div class="order-pay-title">
			<h1>결제 하기</h1>
		</div>
		<br>
			
			<%-- 주문할 상품 정보 테이블  --%>
			<div>
				<table class="order-pay-table"> 
					<thead>
						<tr>
							<th width="250px">이미지</th>
							<th width="450px">상품명</th>
							<th width="150px">판매가</th>
							<th width="100px">수량</th>
							<th width="100px">적립금</th>
	 						<th width="100px">배송비</th>
							<th width="150px">합계</th>
						</tr>
					</thead>
					
					<tbody class="order-pay-content">
						<c:forEach var="shopinfo" items="${sessionScope.shoplists}" > 
							<tr>
								<td> 				
									<img src="<%=cp%>/upload/${shopinfo.file_name}">
								</td>
								
								<td>
									<strong>${shopinfo.name}</strong>
								</td> 
								
								<td>
									<fmt:formatNumber value="${shopinfo.price}" pattern="###,###"/>원
								</td>
								
								<td style="width: 80px; width:43px;">
									${shopinfo.qty}개
								</td>
								
								<td>
									${shopinfo.p_point * shopinfo.qty}
								</td>
								
		 						<td>무료<br/></td>
		 						
		 						
								<td>
									<fmt:formatNumber value="${shopinfo.qty * shopinfo.price}" pattern="###,###"/>원
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<br><br>
			 
			 
			 <div class="deliver-info-title">
				<h1>주문 배송 정보</h1>
			</div>
			<br>
			
			<table class="deliver-info-table">
				<thead>
					<tr>
						<th><label for="name">이름</label></th>
						<th><label for="email">E-mail</label></th>
						<th><label for="hphone">휴대폰</label></th>
						<th><label for="zipcode">우편번호</label></th>
						<th><label for="address">주소</label></th>
						<th><label for="address-detail">상세 주소</label></th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<td>
							${loginfo.name}<br> 
						</td>
						
						<td>
							${loginfo.email}<br> 
						</td>
						
						<td>
							${loginfo.phone}<br> 
						</td>
						
						<td>
							${loginfo.zipcode}&nbsp;
						</td>
						
						<td>
							${loginfo.address1}<br>
						</td>
						
						<td>
							${loginfo.address2}<br>
						</td>
					</tr>
				</tbody>
			</table>
			<br><br>

			<%-- 결제예정금액 테이블  --%>
			<table class="order-price-table">
				<tr>
					<th width="200px">총 상품금액 </th>
					<th width="200px">총 배송비 </th>
					<th width="750px"><span>결제예정금액</span></th>
				</tr>
				
				<tr>
					<td style="padding: 22px 0;">
						<span class="price"><fmt:formatNumber value="${sessionScope.totalAmount}" pattern="###,###"/>원</span>
					</td>
					
					<td>
						<span> + 무료</span>
					</td>
					
					<td>
						<span class="total-price"> = <fmt:formatNumber value="${sessionScope.totalAmount}" pattern="###,###"/>원</span>
					</td>
				</tr>
			</table>
			
			<br/><br/>
			
			
			<div class="pay-info-section">
				<h2>결제 안내</h2>
				
				<ol>
					<li class="info-item">배송은 약 120일 ~ 365일 소요됩니다.</li>
					<li class="info-item">비회원 주문의 경우, 주문확인이 불가능 합니다</li>
					<li class="info-item">주문의 취소 및 환불, 교환 불가능 합니다.</li>
					<li class="info-item">[쇼핑계속하기] 버튼을 누르시면 쇼핑을 계속 하실 수 있습니다.</li>
				</ol>
			</div>
			
			<div class="cart-button-group">
				<button class="pay-btn" id="allProduct" onclick="cartPay()">결제하기</button>
				<button class="keep-btn" id="keepShopping" onclick="keepShop()">쇼핑 계속하기</button>
			</div>
	</div>
			<br/><br/><br/><br/><br/>
			
			
			
<script>
	const keepShopping = document.getElementById("keepShopping");

		keepShopping.addEventListener("click", function(e) {
			location.href = "<%=NoForm%>PRList";
		})
</script>
</body>
</html>