<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 	String cp = request.getContextPath(); %>

<%@ include file="./../../common/common.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MyPetDiary | 반려동물인과 함께하는 커뮤니티</title>
</head>
<body>
	<main>
    	<section class="section--visual">
        </section>
        
        <section>
        	<div class="info">         		
        		<div class="info__description">
	        		<p>▪︎ 특별하게 엄선된 상품을 <mark>빠르고 정확하게 배송</mark>해 드립니다.</p>
	        		<p>▪ 반려동물을 사랑하는 유저와 <mark>자유롭게 소통</mark>하세요</p>
	        	</div>
        	</div>
        	
       		<div class="shop-direct clearfix">
       			<img src="./../images/main_kogi.png" alt="main_cogi" class="float--left">
       			<div class="shop-direct-desc">
       				<h1>반려동물 용품 <mark>쇼핑</mark></h1>&nbsp;<br>
       				<a href="<%=NoForm%>PRList">쇼핑하기</a>
       			</div>
       		</div>
       		
			<div></div>
			<div></div>
			<div></div>
			<div></div>
        </section>
   	</main>
</body>
</html>