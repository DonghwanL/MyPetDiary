<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./../../common/common.jsp"%>

<%
	String cp = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyPetDiary | Shop</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/PRList.css">
</head>

<body>
	<div class="header" align="center">
		<table class="filter-table"> 
			<tr>
				<td align="center" class="p_type">
					<!-- filter() Method를 사용 시 각 페이지를 따로 만들 필요없이 한 화면에서 분류가 가능 -->
					<!-- 필터 인자 값을 한글로 작성 시 페이징 오류가 나므로 반드시 영문으로 입력 ex) all, cat, dog -->
					<label class="a" onclick="filter('all')">전체</label>&nbsp;&nbsp;&nbsp;
					<label class="a" onclick="filter('cat')">|&nbsp;&nbsp;&nbsp;고양이</label>&nbsp;&nbsp;&nbsp;
					<label class="a"onclick="filter('dog')">|&nbsp;&nbsp;&nbsp;강아지</label>
				</td>
			</tr>
		</table>	
	</div>
	
	<div align="center">
		<table class="image-products">
			<tbody>
				<tr>
					<c:set var="a" value="0" />
					<c:forEach var="shop" items="${requestScope.lists}">
						<td align="center">
							<a href="<%=cp%>/Mypet?command=PRDetailView&p_id=${shop.p_id}">
								<img alt="image" src="${pageContext.request.contextPath}/upload/${shop.file_name}" />
							</a><br>
							
							<div class="title">
								<a href="<%=cp%>/Mypet?command=PRDetailView&name="${shop.name}>
									상품명 : ${shop.name}
								</a>
							</div>
							<div class="price">판매가 : ${shop.price}</div></td>

						<c:set var="a" value="${a + 1}" />
						<c:if test="${a == 3}">
							<tr></tr>
						<c:set var="a" value="0" />
						</c:if>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		
		<form class="search_form" name="search_form" action="<%=YesForm%>" method="get">
			<div class="search-group">
				<select name="mode" id="mode">
					<option value="all" selected="selected">선택
					<option value="p_type">동물
					<option value="category">분류
					<option value="name">상품명
				</select> 
				<input type="text" class="search_input" name="keyword" id="keyword">&nbsp;&nbsp;
				<button type="submit">검색</button>&nbsp;
			</div>
			
			<div class="paging-section" align="center">
				<div>${requestScope.pagingHtml}</div>
			</div>
		</form>
	</div>
	
<script> 
function writeForm() {
	location.href = "<%=NoForm%>PRAdd";
}

function filter(type) {
	location.href = "<%=NoForm%>PRList&filterType=" + type;
}
</script>

</body>
</html>