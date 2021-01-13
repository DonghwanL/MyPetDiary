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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cartList.css">
<title>MyPetDiary | 장바구니</title>
</head>

<body>
	<div class="cart-list"> 
			<div class="cart-list-title">
				<h1>장바구니</h1>
			</div>
			<br /><br />
			
			<%-- 장바구니 상품 정보 테이블  --%>
			<div>
				<table class="cart-product-list"> 
					<thead>
						<tr>
							<th width="80px"><input type="checkbox" name="checkbox" id="checkall" /></th>
							<th width="250px">이미지</th>
							<th width="450px">상품명</th>
							<th width="150px">판매가</th>
							<th width="100px">수량</th>
							<th width="100px">적립금</th>
	 						<th width="100px">배송비</th>
							<th width="150px">합계</th>
							<th width="150px">옵션</th>
						</tr>
					</thead>
					
					<tbody class="cart-content">
						<c:forEach var="shopinfo" items="${sessionScope.shoplists}" > 
							<tr>
								<td>
									<input type="checkbox" id="cbtr1" name="checkbox" />
								</td>
								
								<td> 				
									<img src="<%=cp%>/upload/${shopinfo.file_name}">
								</td>
								
								<td>
									<strong>${shopinfo.name}</strong>
								</td> 
								
								<td>
									<fmt:formatNumber value="${shopinfo.price}" pattern="###,###"/>
								</td>
								
								<td style="width: 80px; width:43px;">
									${shopinfo.qty}
								</td>
								
								<td>
									${shopinfo.p_point * shopinfo.qty}
								</td>
								
		 						<td>무료<br/></td>
		 						
		 						
								<td>
									<fmt:formatNumber value="${shopinfo.qty * shopinfo.price}" pattern="###,###"/>
								</td>
								
								
								<td class="delete-td">
									<button class="delete-btn" onclick="deleteCart()">삭제</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<br/><br/>
	 
	 
			<%-- 결제예정금액 테이블  --%>
			<table class="cart-orderpay-list">
				<tr>
					<th width="200px">총 상품금액 </th>
					<th width="200px">총 배송비 </th>
					<th width="750px"><span>결제예정금액</span></th>
				</tr>
				
				<tr>
					<td style="padding: 22px 0;">
						<span class="price"><fmt:formatNumber value="${sessionScope.totalAmount}" pattern="###,###"/></span>
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
			
				
			<div class="cart-info-section">
				<h2>장바구니 이용안내</h2>
				
				<ol>
					<li class="info-item">결제를 할 경우 '절대' 환불이 불가능 합니다.</li>
					<li class="info-item">[쇼핑계속하기] 버튼을 누르시면 쇼핑을 계속 하실 수 있습니다.</li>
				</ol>
			</div>
			
			<div class="cart-button-group">
				<button class="pay-btn" id="allProduct" onclick="cartPay()">결제하기</button>
				<button class="keep-btn" id="productClear" onclick="keepShop()">쇼핑 계속하기</button>
			</div>
			
			<br/><br/><br/><br/><br/>
	</div>
	
	
<script>
		$(document).ready(function(){
		    //최상단 체크박스 클릭
		    $("#checkall").click(function(){
		        //클릭되었으면
		        if($("#checkall").prop("checked")){
		            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
		            $("input[name=checkbox]").prop("checked",true);
		            //클릭이 안되있으면
		        }else{
		            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
		            $("input[name=checkbox]").prop("checked",false);
		        }
		    })
		});

	function cartPay() {
		location.href="<%=NoForm%>CartCalculate";
	}
	
	function keepShop() {
		location.href="<%=NoForm%>PRList";
	}
	
	function deleteCart() {
		location.href="<%=NoForm%>CartDelete&p_id=${shopinfo.p_id}";
	}
</script>
</body>
</html>