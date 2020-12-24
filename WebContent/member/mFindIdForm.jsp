<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MyPetDiary | 로그인</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mFindIdForm.css">
</head>

<body>
	<h1>아이디 찾기</h1>

	<div class="inner_login">
		<div class="login_tistory">
			<form method="post" id="authForm" action="<%=YesForm%>">
				<input type="hidden" name="command" value="mFindId">

				<fieldset>
					<legend class="screen_out">아이디 찾기 Form</legend>
					<div class="box_login">
						<div class="inp_text">
							<label for="Name" class="screen_out">이름</label> <input
								type="text" id="Name" tabindex="2" size="29" name="name"
								placeholder="고객님의 이름을 입력해주세요"> <span>${errorname}</span>
						</div>
						<div class="inp_text">
							<label for="Email" class="screen_out">이메일</label> <input
								type="text" id="Email" size="29" tabindex="5" name="email"
								placeholder="가입 시 등록하신 이메일 주소를 입력해주세요"> <span>${erroremail}</span>
						</div>
					</div>
					<button type="submit" class="btn_find">확&nbsp;인</button>
				</fieldset>
			</form>
		</div>
	</div>

</body>
</html>