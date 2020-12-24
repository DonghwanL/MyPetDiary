<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../../common/common.jsp"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPetDiary | 문의 게시판</title>
</head>
<body>
	<div class="container">
		 <div class="row"> 
		 	<div class="col-md-offset-2 col-md-8 col-md-offset-2">
		 		<h1>문의 게시판</h1>
		 		<table>
					<tbody>
						<c:forEach var="bean" items="${requestScope.lists}">
							<tr>
								<td>
									<c:forEach var="cnt" begin="1" end="${bean.depth}">
										<span class="badge-re">re</span>
									</c:forEach>
									<a href="<%=NoForm%>QBDetailView&no=${bean.no}&${requestScope.parameters}">
										${bean.title}
									</a>
								</td>
								<td>${bean.content}</td>
							</tr>
						</c:forEach>
					</tbody>
		 		</table>
			</div>
		</div>
	</div>
</body>
</html>