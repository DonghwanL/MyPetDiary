<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../../common/common.jsp"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPetDiary | 상품 등록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/PRAdd.css">
</head>
<body>
	<div class="container">
		<div class="row"> 
			<div class="col-md-offset-4 col-md-5 col-md-offset-3 product-add">
				<form name="product_add_form" action="<%=YesForm%>" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="command" value="PRAdd">
					<h1>상품 등록</h1>
					<div class="line"></div>
					
					<table class="product-add-table">
						<tbody>							
							<tr>
								<td width="20%">상품 타입</td>
								<td>
									<select name="p_type" id="p_type">
										<option value="all" selected="selected">선택
										<option value="강아지">강아지
										<option value="고양이">고양이
									</select> 
									<span class="error"></span>
								</td>
							</tr>
								
							<tr>
								<td>상품 분류</td>
								<td>
									<select name="category" id="category">
										<option value="all" selected="selected">선택
										<option value="사료">사료
										<option value="간식">간식
										<option value="기타">기타
									</select> 
									<span class="error"></span>
									</td>
							</tr>
								
							<tr>
								<td>상품 이미지</td>
								<td>
									<input type="file" name="imageFile" size="30"><br> 
									<span class="error"></span>
								</td>
							</tr>
							
							<tr>
								<td>상품명</td>
								<td>
									<input type="text" name="name" size="30"><br> 
									<span class="error"></span>
								</td>
							</tr>
								
							<tr>
								<td>가격</td>
								<td>
									<input type="text" name="price" size="30"><br> 
									<span class="error"></span>
								</td>
							</tr>
								
							<tr>
								<td>재고</td>
								<td>
									<input type="text" name="stock" size="30"><br> 
									<span class="error"></span>
								</td>
							</tr>
								
							<tr>
								<td>포인트</td>
								<td>
									<input type="text" name="p_point" size="30"><br> 
									<span class="error"></span>
								</td>
							</tr>
								
							<tr>
								<td>상품 설명</td>
								<td>
									<textarea name="content" placeholder="상품 설명을 입력해 주세요"></textarea>
									<span class="error"></span>
								</td>
							</tr>
						</tbody>
					</table>
					
							<div class="product-button-group" align="center">
								<button type="submit">상품 등록</button>&nbsp;&nbsp; 
								<a href="#" onclick="moveList()">목&nbsp;록</a>&nbsp;&nbsp;
								<button type="reset">취&nbsp;소</button>
							</div>
				</form>
			</div>
		</div>
	</div>

	
<script>
function moveList() {
	location.href = "<%=NoForm%>adminPRList";
}

function deletePost() {
	if (confirm('게시물을 삭제 하시겠습니까?')) {
		location.href = "<%=NoForm%>IBDelete&no=${bean.no}&${requestScope.parameters}";
	}
}

function deleteComment() {

	if (!confirm('댓글을 삭제 하시겠습니까?')) {
		return false;
	}
}


function modifyComment(cno) {
   	 var url = '<%=NoForm%>IBCModify&cno=' + cno; 
    
     window.open(url, "modifyForm", "width=570, height=350, resizable=no, scrollbars=no");
}

</script>
</body>
</html>