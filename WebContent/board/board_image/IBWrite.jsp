<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../../common/common.jsp"%>        

<% String cp = request.getContextPath(); %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPetDiary | 사진 게시판</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/IBWrite.css">
</head>
<body>

	<div class="container">
		<div class="row"> 
		 	<div class="col-md-offset-3 col-md-6 col-md-offset-3 image-upload">		 
		 		<form id="image_upload_form" action="<%=cp%>/Mypet?command=IBWrite" method="POST" enctype="multipart/form-data" 
		 		onsubmit="return writeCheck()">
					<input type="hidden" name="board_type" value="사진">
					
					<table class="image-upload-table">
						<tbody>
							<tr>
								<td width="20%">작성자 : </td>
								<td><input type="text" name="writer" size="20" value="${sessionScope.loginfo.id}" readonly></td>
							</tr>
							
							<tr>
								<td width="20%">제목 :</td>
								<td>
									<input type="text" name="title" size="30" placeholder="제목을 입력하세요">
									<span class="error"></span>
								</td>
							</tr>
							
							<tr>
								<td>첨부 파일 :</td>
								<td width="300">
									<input type="file" name="imageFile" class="imageFile">
								</td>
							</tr>
							
							<tr>
								<td colspan="2" class="content">
									<span class="error1"></span><br />
									<textarea name="content" placeholder="게시물 내용을 입력 해주세요"></textarea>
								</td>
							</tr>		
							
							<tr>
								<td colspan="2">
									<div class="write-button-group" align="center">	
										<button type="submit">글쓰기</button>
										<button type="reset">취소</button>
										<button type="button" onclick="moveList()">목록</button>
									</div>
								</td>
							</tr>	
						</tbody>
			 		</table>	
		 		</form>
			</div>
		</div>
	</div>
	
	<div class="line"></div>
	
	<script>
		function moveList() {
			location.href = "<%=NoForm%>IBList";
		}
		
		function writeCheck() {
			var title = document.forms[0].title.value;     
		    var content = document.forms[0].content.value;
		    var imageFile = document.forms[0].imageFile.value;
		     
		    if (title.length < 5) {    
		        document.querySelector('.error').innerText = '제목은 5글자 이상 입력 해주세요';
		        document.forms[0].title.focus();    
		        
		        return false;                   
		    } 
		     
		    if (content.length < 10) {
		    	document.querySelector('.error1').innerText = '내용은 10글자 이상 입력 해주세요';
		        document.forms[0].content.focus();
		        return false;
		    }
		    
			if (!imageFile) {
				alert("이미지 파일을 선택 해주세요");
				document.forms[0].imageFile.focus();
				return false;
			}
		}
	</script>	
</body>
</html>