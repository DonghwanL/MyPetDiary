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
						<a class="product-manager" href="<%=NoForm%>adminPRList">
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
				<th>관리</th>		
			</tr>
		</thead>
				
			<tbody>
				<c:forEach var="product" items="${requestScope.lists}">
					<tr class="product-data">
						<td>
							<img src="<%=cp%>/upload/${product.file_name}"  alt="product-image" width="80" height="60" />
						</td>
						<td>${product.p_type}</td>
						<td>${product.category}</td>
						<td>${product.name}</td>
						<td><fmt:formatNumber value="${product.price}" pattern="#,###" /></td>
						<td>${product.stock}</td>
						<td>${product.sell_counts}</td>
						<td>${product.p_point}</td>
						<td>${product.created_at}</td>
						<td colspan="2">
							<div class="modify-delete-group">
								<a href="<%=NoForm%>PRModify&p_id=${product.p_id}&${requestScope.parameters}" class="modify-button">[수정]</a>&nbsp; 
								<a href="<%=NoForm%>PRDelete&p_id=${product.p_id}&${requestScope.parameters}" onclick="return deleteCheck()">[삭제]</a> 
							</div>
						</td>
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
							<input type="text" class="search_input" name="keyword" id="keyword">&nbsp;
							<button type="submit">검색</button>&nbsp;
							<button type="button" onclick="writeForm();">상품 등록</button>&nbsp;
							<button type="submit" onclick="moveList()">목록</button>
						</div>

						<div align="center" class="paging-section">
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
		
		function writeForm() {
			location.href = "<%=NoForm%>PRAdd";
		}
		
		function deleteCheck() {
			if (!confirm('상품을 삭제 하시겠습니까?')) {
				return false;
			}
		}
</script>
</body>
</html>