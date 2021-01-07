<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./../../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPetDiary | 관리자 메뉴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mList.css">
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

	<table class="member-list-table">
		<thead>
			<tr class="member-header">
				<th>ID</th>
				<th>이름</th>
				<th>닉네임</th>
				<th>E-mail</th>
				<th>핸드폰</th>
				<th>주소</th>
				<th>반려동물</th>
				<th>Point</th>
				<th>Level</th>		
			</tr>
		</thead>
				
			<tbody>
				<c:forEach var="bean" items="${requestScope.lists}">
					<tr class="member-data">
						<td><strong>${bean.id}</strong></td>
						<td>${bean.name}</td>
						<td>${bean.nickname}</td>
						<td>${bean.email}</td>
						<td>${bean.phone}</td>
						<td>${bean.address1}&nbsp; ${bean.address2}</td>
						<td>${bean.animal_type}</td>
						<td>${bean.mpoint}</td>
						<td>${bean.mlevel}</td>
					</tr>
				</c:forEach>

			<tr>
				<td colspan="10" align="center">
					<form class="search_form" name="search_form" action="<%=YesForm%>"method="get">
						<input type="hidden" name="command" value="mList">
						<div class="search-group">
							<select name="mode" id="mode">
								<option value="all" selected="selected">선택
								<option value="id">ID
								<option value="nickname">닉네임
								<option value="name">이름
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
			location.href = "<%=NoForm%>mList";
		}
	</script>
</body>
</html>