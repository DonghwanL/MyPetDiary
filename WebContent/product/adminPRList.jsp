<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% String cp = request.getContextPath(); %>
<%@ include file="./../../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPetDiary | 관리자 메뉴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminPRList.css">
</head>
<body>
	<div class="manager">
		<table class="manager-table">
			<tbody>
				<tr>
					<td>
						<a class="member-list" href="<%=NoForm%>mList">
							회원 목록
							</a>
					</td>

					<td>
						<a class="product-manager" href="<%=NoForm%>adminProduct">
							상품 관리
						</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<table class="product-list-table">
		<thead>
			<tr class="product-header">
				<th>상품 이미지</th>
				<th>동물</th>
				<th>분류</th>
				<th>상품명</th>
				<th>가격</th>
				<th>재고</th>
				<th>판매량</th>
				<th>포인트</th>	
				<th>등록일</th>	
			</tr>
		</thead>
				
			<tbody>
				<c:forEach var="product" items="${requestScope.lists}">
					<tr class="product-data">
						<td>
							<img alt="product-image" src="<%=cp%>/upload/${bean.file_name}" width="50" height="50" />
						</td>
						<td>${product.p_type}</td>
						<td>${product.category}</td>
						<td>${product.name}</td>
						<td>${product.price}</td>
						<td>${product.stock}</td>
						<td>${product.sell_counts}</td>
						<td>${product.p_point}</td>
						<td>${product.created_at}</td>
					</tr>
				</c:forEach>

			<tr>
				<td colspan="10" align="center">
					<form class="search_form" name="search_form" action="<%=YesForm%>"method="get">
						<input type="hidden" name="command" value="adminPRList">
						<div class="search-group">
							<select name="mode" id="mode">
								<option value="all" selected="selected">선택
								<option value="p_type">동물
								<option value="category">분류
								<option value="name">상품명
							</select> 
							<input type="text" class="search_input" name="keyword" id="keyword">&nbsp;&nbsp;
							<button type="submit">검색</button>&nbsp;
							<button type="submit" onclick="moveList()">목록</button>
						</div>

						<div align="center">
							<div>${requestScope.pagingHtml}</div>
						</div>
					</form>
				</td>
			</tr>
		</tbody>				
	</table>
	<script>
		function moveList() {
			location.href = "<%=NoForm%>adminPRList";
		}
	</script>
</body>
</html>