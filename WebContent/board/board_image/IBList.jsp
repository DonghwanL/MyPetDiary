<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="./../../common/common.jsp"%>     

<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyPetDiary | 사진 게시판</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/IBList.css">
<script>
	function writeForm() {
		location.href = "<%=NoForm%>IBWrite";
	}
</script>
</head>
<body>
	<div align="center">
		<br>
		<h1>사진 게시판</h1>
		<br>
		<div class="line"></div>
		<table class="image-board">
			<tbody>
				<tr>
					<c:set var="a" value="0" />
					<c:forEach var="bean" items="${requestScope.lists}">
						<td align="center" style="padding:20px" >
							<a href="<%=cp%>/Mypet?command=IBDetailView&no=${bean.no}">
								<img alt="image" src="<%=cp%>/upload/${bean.file_name}"
								width="140" height="140" />
							</a>
							<br>
							<div class="title">
								<a href="<%=cp%>/Mypet?command=IBDetailView&no=${bean.no}">
									${bean.title}
								</a>
							</div>
							<div class="wirter">작성자 : ${bean.writer}</div>
						</td>
						
						<c:set var="a" value="${a + 1}" />
						<c:if test="${a == 3}" >
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
					<option value="writer">작성자
					<option value="title">제목
					<option value="content">내용
				</select> 
				<input type="text" class="search_input" name="keyword" id="keyword">&nbsp;&nbsp;
				<button type="submit">검색</button>&nbsp;

				<c:if test="${login_type != 0}">
					<button type="button" onclick="writeForm();">글쓰기</button>
				</c:if>
			</div>

			<div class="paging-section" align="center">
				<div>${requestScope.pagingHtml}</div>
			</div>
			
		</form>
	</div>
</body>
</html>