<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%----- COMMAND -----%>

<%!
	String YesForm = null;
%>

<%
	String contextPath = request.getContextPath();
	String mappingName = "/Mypet";
	
	// Form TAG 변수
	YesForm = contextPath + mappingName;
%>
	    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPetDiary | 댓글 수정</title>
<style>
body {
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 13px;
}

h1 {
	font-size: 20px;
	text-align: center;
}

hr {
	size: 1;
	width: 540px;
}

textarea {
	resize: none;
	width: 530px;
	height: 100px;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

textarea:focus {
	outline: none;
}

button {
	font-size: 13px;
	color: #fff;
	background-color: #2368B7;
	border: none;
	border-radius: 4px;
	padding: 7px;
	margin-right: 5px;
}

input[type=button] {
	font-size: 13px;
	color: #fff;
	background-color: #2368B7;
	border: none;
	border-radius: 4px;
	padding: 7px;
	margin-right: 5px;
}
</style>
</head>
<body>
<div id="wrap">
    <br>
    <h1>댓글 수정</h1>
    <hr>
    <br>
 
    <div id="comment_modify_form">
        <form id="modifyInfo" name="modifyInfo" action="<%=YesForm%>" method="POST" onsubmit="return checkValue()">   
        	<input type="hidden" name="command" value="IBCModify">
			<input type="hidden" name="cno" value="${comment.c_no}"> 
			<input type="hidden" name="no" value="${bean.no}">
			
            <textarea class="comment_content" name="comment_content">${comment.content}</textarea>
            <br><br>
            
            <div align="center">
	          	 <button type="submit">등록</button>
	           	 <input type="button" value="창닫기" onclick="window.close()">
            </div>
        </form>
    </div>
</div> 

<script>
	function checkValue() {
		var form = document.getElementById("modifyInfo");
		var content = document.querySelector('.comment_content').value;
		
		if (content == null || content == "") {
			alert('내용을 입력 해주세요');
			form.comment_content.focus();    
			return false;
		}
		
		window.close();
	}
</script>
</body>
</html>