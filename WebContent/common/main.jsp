<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 	String cp = request.getContextPath(); %>

<%@ include file="./../../common/common.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MyPetDiary | 반려동물인과 함께하는 커뮤니티</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
</head>
<body>
	<main>
    	<section class="section--visual">
        </section>
        
        <section>
        	<div class="info">         		
        		<div class="info__description">
	        		<p>▪︎ 특별하게 엄선된 상품을 빠르고 정확하게 배송해 드립니다.</p>
	        		<p>▪ 반려동물을 사랑하는 유저와 자유롭게 소통 하세요!</p>
	        	</div>
        	</div>
        	
       		<div class="shop-direct clearfix">
       			<img src="${pageContext.request.contextPath}/images/main_kogi.png" alt="main_cogi" class="float--left">
       			<div class="shop-direct-desc">
       				<h1>반려동물 용품 <mark>쇼핑</mark></h1>&nbsp;<br>
       				<a href="<%=NoForm%>PRList">쇼핑하기</a>
       			</div>
       		</div>
       		
       		<div class="main-slide-title" style="text-align:center"><h2>특가&nbsp;상품</h2></div>
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<c:forEach var="main" items="${requestScope.lists}">
						<div class="swiper-slide">
							<a href="<%=cp%>/Mypet?command=PRDetailView&p_id=${main.p_id}">
								<img alt="image" src="<%=cp%>/upload/${main.file_name}" />
								<span>${main.name} <button class="small-state-btn-yellow">HIT</button></span>
								<span class="product-price">
									<fmt:formatNumber value="${main.price }" pattern="#,###" />
								</span>
							</a>
						</div>
					</c:forEach>
				</div>

				<!-- 네비게이션 -->
				<div class="swiper-button-next"></div><!-- 다음 버튼 (오른쪽에 있는 버튼) -->
				<div class="swiper-button-prev"></div><!-- 이전 버튼 -->
		
				<!-- 페이징 -->
				<div class="swiper-pagination"></div>
			</div>
        </section>
   	</main>
   	
   	<script>
	   	new Swiper('.swiper-container', {
	
	   		slidesPerView : 3, // 동시에 보여줄 슬라이드 갯수
	   		spaceBetween : 5, // 슬라이드간 간격
	   		slidesPerGroup : 3, // 그룹으로 묶을 수, slidesPerView 와 같은 값을 지정하는게 좋음
	   		roundLengths: true,
	
	   		// 그룹수가 맞지 않을 경우 빈칸으로 메우기
	   		// 3개가 나와야 되는데 1개만 있다면 2개는 빈칸으로 채워서 3개를 만듬
	   		loopFillGroupWithBlank : true,
	
	   		loop : true, // 무한 반복
	
	   		pagination : { // 페이징
	   			el : '.swiper-pagination',
	   			clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
	   		},
	   		navigation : { // 네비게이션
	   			nextEl : '.swiper-button-next', // 다음 버튼 클래스명
	   			prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
	   		},
	   	});
   	</script>
</body>
</html>