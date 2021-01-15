<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../common/common.jsp"%>

<html>
<head>
	<title>MyPetDiary | 로그인</title>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mFindPasswordForm.css">
</head>
<body>

	<h1>비밀번호 찾기</h1>

	<div class="inner_login">
		<div class="login_tistory">

			<form method="post" id="authForm" action="<%=YesForm%>">
				<input type="hidden" name="command" value="mFindPassword">
				<fieldset>
					<legend class="screen_out">비밀번호 찾기 폼</legend>
					<div class="box_login">
						<div class="inp_text">
							<label for="Name" class="screen_out">이름</label> <input
								type="text" id="Name" tabindex="2" size="29" name="name"
								placeholder="고객님의 이름을 입력해주세요"> 
							<span class="error">${errorname}</span>
						</div>
						
						<div class="inp_text">
							<label for="Id" class="screen_out">아이디</label> <input type="text"
								id="Id" size="29" tabindex="5" name="id"
								placeholder="가입 시 등록하신 아이디를 입력해주세요"> 
								<span class="error">${errorid}</span>
						</div>
						
						<div class="inp_text">
							<label for="Email" class="screen_out">이메일</label> <input
								type="text" id="Email" size="29" tabindex="5" name="email"
								placeholder="가입 시 등록하신 이메일 주소를 입력해주세요"> 
								<span class="error">${erroremail}</span>
						</div>
					</div>
					<button type="submit" class="btn_find">찾&nbsp;기</button>
					<button type="button" class="page-back" onclick="history.back()">돌아가기</button>
				</fieldset>
			</form>
		</div>
	</div>
	<br><br><br><br><br><br>
</body>
</html>