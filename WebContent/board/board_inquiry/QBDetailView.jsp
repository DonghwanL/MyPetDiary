<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../../common/common.jsp"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPetDiary | 문의 게시판</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/QBDetail.css">
<script>
	function moveList() {
		location.href = "<%=NoForm%>QBList";
	}
	
	function deletePost() {
		if (confirm('게시물을 삭제 하시겠습니까?')) {
			location.href = "<%=NoForm%>QBDelete&no=${bean.no}&${requestScope.parameters}";
		}
	}
	
	function modifyPost() {
		if (confirm('게시물을 수정 하시겠습니까?')) {
			location.href = "<%=NoForm%>QBModify&no=${bean.no}&${requestScope.parameters}";
		}
	}
</script>
</head>

<body>
	<div class="container">
		 <div class="row"> 
		 	<div class="col-md-offset-3 col-md-6 col-md-offset-3 inquiry-detail">		 		
		 		<table class="inquiry-detail-table">
					<tbody>
						<tr>
							<td width="10%">
							작성일 : 
							</td>
							<td>${bean.created_at}</td>
						</tr>
						<tr>
							<td width="10%">이름 :</td>
							<td>${bean.writer}</td>
						</tr>
						<tr>
							<td width="10%">제목 :</td>
							<td>${bean.title}</td>
						</tr>
						<tr>
							<td colspan="2" class="content">${bean.content}</td>
						</tr>		
						
						<tr>
							<td colspan="2">
								<div class="lieks-count" align="right">	
									<form id="like_form">
										<input type="hidden" name="command" value="LikeUpdate">
										<input type="hidden" name="board_no" value="${bean.no}">
										<button type="submit">Like!</button>
										<span class="like_result">${bean.likes_count}</span>
									</form>
								</div>

							</td>
						</tr>
						
						<tr>
							<td>
								<div class="comments">	
									
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="detail-button-group" align="right">
									<c:if test="${sessionScope.loginfo.id == bean.writer || sessionScope.loginfo.id == 'admin'}">
										<button type="submit" onclick="modifyPost()">수정</button>
									</c:if>
									
									<c:if test="${sessionScope.loginfo.id == bean.writer || sessionScope.loginfo.id == 'admin'}">
							 			<button type="submit" onclick="deletePost()">삭제</button>
							 		</c:if>
							 			
							 		<button type="submit" onclick="moveList()">목록</button>
								</div>
							</td>
						</tr>
					</tbody>
		 		</table>

			</div>
		</div>
	</div>
</body>
</html>