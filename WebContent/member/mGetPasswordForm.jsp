<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPetDiary | 비밀번호 찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mGetPasswordForm.css">

</head>
<body>

	<div class="wrapper">
		<h1>비밀번호 찾기</h1>
		<div class="write_form">
			<p class="desc">
				고객님의 비밀번호 찾기가 완료되었습니다!
			</p>
			<p class="info-password">비밀번호 : ${requestScope.password}</p>
			<button type="button" onclick="<%=NoForm%>mLogin" class="btn_createId">로그인</button>
		</div>
	</div>
</body>
</html>