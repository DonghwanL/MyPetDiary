<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../../common/common.jsp"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPetDiary | 상품 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/PRAdd.css">
</head>
<body>
	<div class="container">
		<div class="row"> 
			<div class="col-md-offset-4 col-md-5 col-md-offset-3 product-add">
				<form class="product_add_form" name="product_add_form" action="<%=YesForm%>" method="POST" enctype="multipart/form-data" onsubmit="return formCheck()">
					<input type="hidden" name="command" value="PRModify">
					<input type="hidden" name="pageNumber" value="${param.pageNumber}">
					<input type="hidden" name="pageSize" value="${param.pageSize}">
					<input type="hidden" name="mode" value="${param.mode}">
					<input type="hidden" name="keyword" value="${param.keyword}">
					<input type="hidden" name="p_id" value="${product.p_id}">
					<input type="hidden" name="image" value="${product.file_name}">
					
					<h1>상품 수정</h1>
					<div class="line"></div>
					
					<table class="product-add-table">
						<tbody>							
							<tr>
								<td width="20%">상품 타입</td>
								<td>
									<select name="p_type" id="p_type" onmouseup="formCheck()">
										<option value="all" selected="selected">선택
										
										<c:if test="${product.p_type == 'DOG'}">
											<option value="DOG" selected="selected">강아지
										</c:if>
										<c:if test="${product.p_type != 'DOG'}">
											<option value="DOG">강아지
										</c:if>
										
										<c:if test="${product.p_type == 'CAT'}">
											<option value="CAT" selected="selected">고양이
										</c:if>
										<c:if test="${product.p_type != 'CAT'}">
											<option value="CAT">고양이
										</c:if>
										
									</select> 
									<span class="error p_type_error"></span>
								</td>
							</tr>
								
							<tr>
								<td>상품 분류</td>
								<td>
									<select name="category" id="category" onmouseup="formCheck()">
										<option value="all" selected="selected">선택
										
										<c:if test="${product.category == '신상품'}">
											<option value="신상품" selected="selected">신상품
										</c:if>	
										<c:if test="${product.category != '신상품'}">
											<option value="신상품">신상품
										</c:if>	
										
										<c:if test="${product.category == '특가'}">
											<option value="특가" selected="selected">특가
										</c:if>		
										<c:if test="${product.category != '특가'}">
											<option value="특가">특가
										</c:if>	
										
										<c:if test="${product.category == 'BEST'}">
											<option value="BEST" selected="selected">BEST
										</c:if>
										<c:if test="${product.category != '기타'}">
											<option value="BEST">BEST
										</c:if>
									</select> 
									<span class="error category_error"></span>
									</td>
							</tr>
								
							<tr>
								<td>상품 이미지</td>
								<td>
									<input type="file" class="imageFile" name="imageFile" size="30"><br> 
								</td>
							</tr>
							
							<tr>
								<td>상품명</td>
								<td>
									<input type="text" class="name" name="name" size="30" value="${product.name}" onmouseover="formCheck()">
									<span class="error name_error"></span>
								</td>
							</tr>
								
							<tr>
								<td>가격</td>
								<td>
									<input type="text" class="price" name="price" size="30" value="${product.price}" onmouseover="formCheck()">
									<span class="error"></span>
								</td>
							</tr>
								
							<tr>
								<td>재고</td>
								<td>
									<input type="number" class="stock" name="stock" size="30" value="${product.stock}" onmouseover="formCheck()">
									<span class="error"></span>
								</td>
							</tr>
								
							<tr>
								<td>포인트</td>
								<td>
									<input type="number" class="point" name="p_point" size="30" value="${product.p_point}" onmouseover="formCheck()"> 
									<span class="error"></span>
								</td>
							</tr>
								
							<tr>
								<td>상품 설명</td>
								<td>
									<textarea class="content" name="content" placeholder="상품 설명을 입력해 주세요" onclick="formCheck()">&nbsp;${product.content}
									</textarea>
									<span class="error content_error"></span>
								</td>
							</tr>
						</tbody>
					</table>
					
							<div class="product-button-group" align="center">
								<button type="submit">수&nbsp;정</button>&nbsp;&nbsp; 
								<button type="reset">취&nbsp;소</button>&nbsp;&nbsp;
								<a href="#" onclick="moveList()">목&nbsp;록</a>
							</div>
				</form>
			</div>
		</div>
	</div>

	
<script>
function moveList() {
	location.href = "<%=NoForm%>adminPRList";
}

function formCheck() { 
	var form = document.querySelector('.product_add_form');
	var p_type = document.querySelector('#p_type');
	var category = document.querySelector('#category');
	var p_type_error = document.querySelector('.p_type_error');
	var category_error = document.querySelector('.category_error');
	var imageFile = document.querySelector('.imageFile').value;
 	var name = document.querySelector('.name');
 	var name_error = document.querySelector('.name_error');
 	var price = document.querySelector('.price');
 	var price_error = document.querySelector('.price_error');
 	var stock = document.querySelector('.stock');
 	var stock_error	= document.querySelector('.stock_error');
 	var point = document.querySelector('.point');
 	var point_error = document.querySelector('.point_error');
	var content = document.querySelector('.content');
	var content_error = document.querySelector('.content_error');
	
	if (p_type.value == 'all') {
		p_type_error.innerText = '상품 타입을 선택 해주세요';
		return false;
	} 
	
	if (category.value == 'all') {
		category_error.innerText = '상품 분류를 선택 해주세요';
		return false;
	} 
	
	if (!imageFile) {
		alert('이미지 파일을 선택 해주세요');
		return false;
	}
	
	if (name.value == null || name.value == '') {
		name_error.innerText = '상품명을 입력 해주세요';
		form.name.focus();  
		return false;
	}
	
	if (price.value == null || price.value == '') {
		price_error.innerText = '가격을 입력 해주세요';
		form.price.focus();  
		return false;
	}
	
	if (stock.value == null || stock.value == '') {
		stock_error.innerText = '재고를 입력 해주세요';
		form.stock.focus();  
		return false;
	}
	
	if (point.value == null || point.value == '') {
		point_error.innerText = '포인트를 입력 해주세요';
		form.point.focus();  
		return false;
	}
	
	if (content.value == null || content.value == "") {
		content_error.innerText = '상품 상세 설명을 입력 해주세요';
		form.content.focus();    
		return false;
	}
}
</script>
</body>
</html>