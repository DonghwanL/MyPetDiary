<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>    
<html>
<head>
   	<title>MyPetDiary | 로그인</title>
   	<meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
  
  <h1>로그인</h1>
  
   <div class="inner_login">
    <div class="login_tistory">

        <form method="post" id="authForm" action="<%=YesForm%>">
            <input type="hidden" name="command" value="mLogin">
            <fieldset>
            <legend class="screen_out">로그인 정보 입력폼</legend>
            <div class="box_login">
                <div class="inp_text">
                <label for="loginId" class="screen_out">아이디</label>
                <input type="text" id="loginId" name="id" placeholder="아이디">
                <span>${errorid}</span>
                </div>
                <div class="inp_text">
                <label for="loginPw" class="screen_out">비밀번호</label>
                <input type="password" id="loginPw" name="password" placeholder="비밀번호">
                <span>${errorpassword}</span>
                </div>
            </div>
            <button type="submit" class="btn_login">로그인</button>
            <button type="button" onclick="location.href="''" class="btn_createId">회원가입</button>
            <span>${errmsg}</span>
            
            <div class="login_append">
                <div class="inp_chk"> <!-- 체크시 checked 추가 -->
                <input type="checkbox" id="keepLogin" class="inp_radio" name="keepLogin">
                <label for="keepLogin" class="lab_g">
        <span class="img_top ico_check"></span>
        
        </label>
        
                </div>
                <span class="txt_find">
	                <a href="<%=NoForm%>mFindId"class="link_find">아이디</a>
	                /
					<a href="<%=NoForm%>mFindPassword" class="link_find">비밀번호 찾기 </a>
                </span>
            </div>
            
            </fieldset>
        </form>
        
    </div>
</div>
 
</body>
</html>