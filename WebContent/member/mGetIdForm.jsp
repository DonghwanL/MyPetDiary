<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MyPetDiary | ID 찾기</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mGetIdForm.css">
</head>
<body>

	<div class="wrapper">
		<h1>아이디 찾기</h1>
		<div class="write_form">
			<p class="desc">
				고객님의 아이디 찾기가 완료 되었습니다!
			</p>
			<p class="info-id">ID : ${requestScope.id}</p>
			<button type="button" onclick="<%=NoForm%>mLogin" class="btn_createId">로그인</button>
		</div>
	</div>
	
<script>
const login = document.querySelector('.btn_createId');

login.addEventListener("click", function(e) {
	
	location.href = "<%=NoForm%>mLogin";
})

</script>
</body>
</html>