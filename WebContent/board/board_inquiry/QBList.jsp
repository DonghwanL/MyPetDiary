<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../../common/common.jsp"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPetDiary | 문의 게시판</title>
<script>
	function writeForm() {
		location.href = "<%=NoForm%>QBWrite";
	}
</script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/QBList.css">
</head>
<body>
	<div class="container">
		 <div class="row"> 
		 	<div class="col-md-offset-3 col-md-7 col-md-offset-2 inquiry">
		 		<h1>문의 게시판</h1>
		 		<table class="inquiry-table">
					<tbody>
						<c:forEach var="bean" items="${requestScope.lists}">
							<tr>
								<td>
									<c:if test="${bean.depth > 0}">
										<c:forEach begin="1" end="${bean.depth}">
											&nbsp;&nbsp;
										</c:forEach>
										<span class="reply-icon">↳</span>
									</c:if>	
									
									<a href="<%=NoForm%>QBDetailView&no=${bean.no}&${requestScope.parameters}">
											${bean.title} 
									</a>
								</td>
								
								<td>${bean.writer}</td>
								<td>${bean.created_at}</td>
								<td>${bean.reads_count}</td>
								
							</tr>
						</c:forEach>
						
						<tr>
							<td colspan="10" align="center">
								<form class="search_form" name="search_form" action="<%=YesForm%>" method="get">
									<input type="hidden" name="command" value="QBList">
									<div class="search-group">
										<select name="mode" id="mode">
											<option value="all" selected="selected">선택
											<option value="writer">작성자
											<option value="title">제목									
											<option value="content">내용									
										</select>
										<input type="text" class="search_input" name="keyword" id="keyword">&nbsp;&nbsp;
											<button type="submit">검색</button>&nbsp;
											
											<c:if test ="${login_type != 0}"> 
												<button type="button" onclick="writeForm();">글쓰기</button>
											</c:if>
									</div>

									<div align="center">
										<div>${requestScope.pagingHtml}</div>			
									</div>	
								</form>
							</td>
						</tr>
					</tbody>
		 		</table>
			</div>
		</div>
	</div>
</body>
</html>