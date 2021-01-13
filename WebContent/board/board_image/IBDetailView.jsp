<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../../common/common.jsp"%>    

<%
	// 게시판 글 줄바꿈 (개행문자 치환)
	pageContext.setAttribute("br", "<br/>");
	pageContext.setAttribute("cn", "\n");

	String cp = request.getContextPath();
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPetDiary | 사진 게시판</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/IBDetail.css">
</head>
<body>
	<div class="container">
		<div class="row"> 
			<div class="col-md-offset-2 col-md-8 col-md-offset-2 image-detail">
					<table class="image-detail-table">
						<tbody>							
							<tr>
								<td width="10%">작성일 :</td>
								<td>${bean.created_at}</td>
							</tr>
						
							<tr>
								<td width="10%">작성자 :</td>
								<td>${bean.writer}</td>
							</tr>
							
							<tr>
								<td width="10%">제목 :</td>
								<td>${bean.title}</td>
							</tr>
							
							<tr>
								<td colspan="2" class="content">
									<img align="middle" width="300" height="300"
									src="<%=cp%>/upload/${bean.file_name}"
									alt="img-thumbnail"><br><br>
									${fn:replace(bean.content, cn, br)}
								</td>
							</tr>		
							
							<tr>
								<td colspan="2">
									<div class="lieks-count" align="right">	
								 		<c:choose>
											<c:when test="${sessionScope.loginfo.id != bean.writer}">
												<form id="like_form">
													<input type="hidden" name="command" value="LikeUpdate">
													<input type="hidden" name="no" value="${bean.no}">
													<input type="hidden" name="image" value="${bean.file_name}">
													
													<img src="${pageContext.request.contextPath}/images/thumbs_up.svg" class="like-image" onclick="return likeCount()">
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
								<td colspan="2">
									<div class="detail-button-group" align="right">
										<c:if test="${sessionScope.loginfo.id == bean.writer}">
											<button type="submit" onclick="modifyPost()">수정</button>
										</c:if>
										
										<c:if test="${sessionScope.loginfo.id == bean.writer || sessionScope.loginfo.id == 'admin'}">
								 			<button type="submit" onclick="deletePost()">삭제</button>
								 		</c:if>
								 			
								 		<button type="submit" onclick="moveList()">목록</button>
									</div>
								</td>
							</tr>
							
							
							<tr>
								<td>
									<div class="comments-write">	
										<form id="comments_form" action="<%=YesForm%>" method="POST" onsubmit="return commentCheck()">
											<input type="hidden" name="command" value="IBCWrite">
											<input type="hidden" name="no" value="${bean.no}">
											<input type="hidden" name="comment_writer" value="${sessionScope.loginfo.id}">
											
											<c:if test="${sessionScope.loginfo.id != null}">
												<p class="comments-title">Comments</p>
												<div class="comments-input detail-button-group">
													<textarea name="comments_content" class="comments_content" placeholder="댓글을 입력 해주세요"></textarea>
													<button type="submit">등록</button>
												</div>
											</c:if>
										</form>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					
					
					
					<table class="image-comment-table">
		 				<tbody>
		 					<c:if test="${requestScope.commentList != null}">
							<c:forEach var="comment" items="${requestScope.commentList}">		
									<tr class="comment-list">
	
										<td class="comment-writer" width="20%">
											<span class="comment-writer">${comment.writer}</span> 
											<span class="comment-date">&nbsp;(${comment.created_at})</span>
										</td>
										<td class="comment-content" width="60%">${comment.content}</td>
										<td class="comment-button" align="right" width="20%">

					
											<form id="comments_delete_form" action="<%=YesForm%>" method="GET" onsubmit="return deleteComment()">
												<input type="hidden" name="command" value="IBCDelete">
												<input type="hidden" name="cno" value="${comment.c_no}">
												<input type="hidden" name="no" value="${bean.no}">
												
												<a href="#" class="modify-button" onclick="modifyComment(${comment.c_no})">[수정]</a> 
												<button type="submit" class="comment-delete">[삭제]</button>
											</form>
										</td>
									</tr>
							</c:forEach>
						</c:if>
		 			</tbody>
		 		</table>
		 		<div class="footer"></div>
			</div>
		</div>
	</div>
			

	
<script type="text/javascript">
function moveList() {
	location.href = "<%=NoForm%>IBList";
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

function modifyPost() {
	location.href = "<%=NoForm%>IBModify&no=${bean.no}&${requestScope.parameters}";
}

function modifyComment(cno) {
   	 var url = '<%=NoForm%>IBCModify&cno=' + cno; 
    
     window.open(url, "modifyForm", "width=570, height=350, resizable=no, scrollbars=no");
}

function commentCheck() {
	var form = document.getElementById("comments_form");
	var content = document.querySelector('.comments_content').value;
	
	if (content == null || content == "") {
		alert('내용을 입력 해주세요');
		form.comments_content.focus();    
		return false;
	}
}

// COMMENT

function likeCount(){
	var url = "/Mypet/Mypet?command=LikeUpdate"
			
	$.ajax({
		url: url,
		type: "POST",
		cache: false,
		dataType: "json",
		data: $('#like_form').serialize(),
		success: function(data) { // Ajax 통신 성공시 넘어오는 데이터의 이름 : data
			$("#like_result").html(data.like); //ID 값이 like_result인 html을 찾아서 data.like값으로 변경
		},
	
		error: function (request, status, error) {
			alert("Ajax 실패")
		}
	});
}

</script>
</body>
</html>