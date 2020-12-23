<%----- IMPORT, TAGLIB -----%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
 
 
<%----- LOGIN STATE -----%>

<%-- Not Login --%>
<c:if test="${empty sessionScope.loginfo}">
	<c:set var="login_type" value="0" />
</c:if>

<c:if test="${not empty sessionScope.loginfo}">
	<%-- Admin Login --%>
	<c:if test="${sessionScope.loginfo.id == 'admin'}">
		<c:set var="login_type" value="1" />
	</c:if>
	
	<%-- Member Login --%>
	<c:if test="${sessionScope.loginfo.id != 'admin'}">
		<c:set var="login_type" value="2" />
	</c:if>
</c:if>
    
    
<%----- COMMAND -----%>

<%!
	String YesForm = null;
	String NoForm = null;
%>

<%
	String contextPath = request.getContextPath();
	String mappingName = "/Mypet";
	
	// Form TAG 변수
	YesForm = contextPath + mappingName;
	
	// Not Form TAG 변수
	NoForm = contextPath + mappingName + "?command=";	
%>
	
	    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans&family=Noto+Sans+KR:wght@300;400;500&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	  <div class="body--container">
        <!-- HEADER -->
        <header>
            <div class="inner clearfix">
                <div class="menu-group float--left">
                    <div class="menu-group__logo">
                        <a href="#">MyPetDiary</a>
                    </div>
                    
                    <ul class="main-menu">
                            <li class="dropdown-board"><a href="#" class="hover-color">Community</a>
                            	<ul class="dropdown-submenu">
                            		<li><a href="<%=NoForm%>FBList">자유 게시판</a></li>
                            		<li><a href="<%=NoForm%>IBList">사진 게시판</a></li>
                            		<li><a href="<%=NoForm%>QBList">문의 게시판</a></li>
                            	</ul>
                            </li>
                            
                            <li class="dropdown-mypage"><a href="#" class="hover-color">My Page</a>
                                <ul class="dropdown-submenu">
                                	<li><a href="#">My Diary</a></li>
                            		<li><a href="<%=NoForm%>mModify&id=${sessionScope.loginfo.id}">정보 수정</a></li>
                            		<li><a href="#">주문 조회</a></li>
                            		
                            		<c:if test="${login_type == 1}">
										<li><a href="<%=NoForm%>mList">관리자 메뉴</a></li>
									</c:if>
                            	</ul>
                            </li>
                            
                            <li class="shop"><a href="#" class="hover-color">Shop</a></li>
                    </ul>
                </div>

                <div class="sign-group float--right">
                    <div class="btn-group">
                    	<div>
                   			<c:if test="${empty sessionScope.loginfo}">
								<span></span>
							</c:if>
							<c:if test="${login_type != 0}">
								<span>${sessionScope.loginfo.nickname}(${sessionScope.loginfo.id})님 환영합니다!</span> 
							</c:if>
						</div>
						
						<div>
	                    	<c:if test ="${login_type == 0}"> 
	                        	<a href="<%=NoForm%>mLogin" class="btn--home">로그인</a>
	                        </c:if>
	                        
	                        <c:if test ="${login_type != 0}"> 
	                        	<a href="<%=NoForm%>mLogout" class="btn--home">로그아웃</a>
	                        </c:if>
                		</div>
                  
                        <a href="#" class="sign-up btn--home">회원가입</a>
                        <a href="#" class="shop-cart">Cart</a>
                    </div>
                </div>
            </div>
        </header>

        <footer>
             <div class="footer-inner">
                <div class="copyright">
                    <p>© 2020 TEAM2 All rights reserved</p>
                </div>
    
                <div class="contact">
                    <address>서울 마포구 백범로 23</address><br>
                    <a href="mailto:mypetdiaryex@gmail.com">메일 보내기</a>
                    <span>/</span>
                    <a href="tel:019-1234-5678">고객센터 연결</a>
                </div>
            </div>
        </footer>
    </div>
</body>
</html>