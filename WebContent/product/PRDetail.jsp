<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./../../common/common.jsp"%>

<%
	String cp = request.getContextPath();

	// Content 글 줄바꿈 (개행문자 치환)
	pageContext.setAttribute("br", "<br/>");
	pageContext.setAttribute("cn", "\n");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPetDiary | 상품 상세 보기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/PRDetail.css">
</head>
<body>
<body>
	<div align="center" class="product-detail clearfix">
		<form id="product-list-form" action="<%=YesForm%>" method="POST" onsubmit="return qtyCheck()">
			<!-- 넘어갈 Parameter -->
			<input type="hidden" name="command" value="CartAdd">
			<input type="hidden" name="p_id" value="${bean.p_id}">
			<input type="hidden" name="name" value="${bean.name}">
			<input type="hidden" name="price" value="${bean.price}">
			<input type="hidden" name="p_point" value="${bean.p_point}">
			<input type="hidden" name="stock" value="${bean.stock}">
			
			<div class="product-image float--left">
				<img src="<%=cp%>/upload/${bean.file_name}" alt="img-thumbnail">
			</div>
			
			<div class="product-menu">
				<div class="product-name">
					상품명&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${bean.name}
				</div>
					
				<div class="product-price">
					판매가&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${bean.price}" pattern="#,###" />
				</div>
				
				<div class="product-point">
					포인트&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${bean.p_point}
				</div>
				
				<div class="product-stock">
					재고&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${bean.stock}
				</div>
				
				<div class="product-qty">
					수량&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="number" name="qty" class="qty">
				</div>
							
				<div class="btn-group-detail" align="left">
					<button class="btn-cart">장바구니 담기</button>&nbsp;&nbsp;&nbsp;
					<button type="button" id="btn-pay" class="btn-pay">바로 구매</button>
				</div>
			</div>
		</form>
	</div>
	
	<br>
	<hr width="750">
	
	<div class="product-detail-content" >
		${fn:replace(bean.content, cn, br)}
	</div>
   
<script>
	function moveList() {
		location.href = "<%=NoForm%>PRList";
	}
	
	const orderPay = document.getElementById("btn-pay");
	
	orderPay.addEventListener("click", function(e) {
		const qty = document.querySelector('.qty').value;
		
		if (!qty) {
			alert('수량을 선택 해주세요');
			return false;
		}
		
		location.href = "<%=NoForm%>CartInstantBuy&p_id=${bean.p_id}&qty=" + qty + "&stock=${bean.stock}";
	})
	
	function qtyCheck() {
		const qty = document.querySelector('.qty').value;
		
		if (!qty) {
			alert('수량을 선택 해주세요');
			return false;
		}
		
	}
</script>
</body>
</html>