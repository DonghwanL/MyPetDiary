<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../../common/common.jsp"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPetDiary | 문의 게시판</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/QBDetail.css">
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
							 		<c:choose>
										<c:when test="${sessionScope.loginfo.id != bean.writer || sessionScope.loginfo.id == 'admin'}">
											<form id="like_form">
												<input type="hidden" name="command" value="LikeUpdate">
												<input type="hidden" name="board_no" value="${bean.no}">
												<button type="submit" onclick="return likeCount()">Like</button>
												<span id="like_result">${bean.likes_count}</span>
											</form>
										</c:when>
										
										<c:otherwise>
											<span class="like_result">추천 : ${bean.likes_count}</span>
										</c:otherwise>
									</c:choose>
									
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
							 		
							 		<c:if test="${sessionScope.loginfo.id != null}">
							 			<button type="submit" onclick="deletePost()">답글</button>
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
		location.href = "<%=NoForm%>QBModify&no=${bean.no}&${requestScope.parameters}";
	}
	
	function likeCount(){
		$.ajax({
			url: "LikeUpdateAction.java",
			type: "POST",
			cache: false,
			dataType: "json",
			data: $('#like_form').serialize(),
			success:
			function(data){ //ajax통신 성공시 넘어오는 데이터 통째 이름 =data
				alert("'좋아요'가 반영되었습니다!") ; // data중 put한 것의 이름 like
				$("#like_result").html(data.like); //id값이 like_result인 html을 찾아서 data.like값으로 바꿔준다.
			},
		
			error:
			function (request, status, error) {
				alert("ajax실패")
			}
		});
	}
</script>
</body>
</html>