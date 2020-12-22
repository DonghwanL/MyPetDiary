<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../common/common.jsp"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MyPetDiary | 정보 수정</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modify_form.css">
	<script>
		function dropCheck() {
				if (!confirm('회원 탈퇴 하시겠습니까?')) {
					return false;
				}
			}
				
		function modifyCheck() {
			if (!confirm('회원정보를 수정 하시겠습니까?')) {
				return false;
			}
		}
		
		function passwordReCheck() {
			
			if(document.modify_form.password.value == document.modify_form.password_re.value) {
				document.getElementById("msg").innerText="";
			}
			
			if(document.modify_form.password.value != document.modify_form.password_re.value) {
				document.getElementById("msg").innerText="비밀번호가 일치하지 않습니다";
			}
		}
				
		function nicknameCheck() {
			var nickname = document.modify_form.nickname.value;
					
			var url = '<%=NoForm%>mNicknameCheck&nickname=' + nickname; 
			window.open(url, 'checkPopUp', 'height=150, width=300');
		}
</script>
</head>

<body>
	<div class=modify>
		<form name="modify_form" action="<%=YesForm%>" method="POST">
		<input type="hidden" name="command" value="mModify">
		<h1>회원정보 수정</h1>
			
			<table class="modify__table">
				<tbody>
					<tr>
						<th>
							<label for="id">ID</label>
						</th>
						<td>
							<input type="text" name="id" size="25" value="${requestScope.bean.id}" readonly><br>
	 						<span class="error">${errorid}</span>	
						</td>
					</tr>	
						
					<tr>	
						<th>
							<label for="name">이름</label>
						</th>
						<td>
							<input type="text" name="name" size="25" value="${requestScope.bean.name}" readonly><br>
							<span class="error">${errorname}</span>
						</td>
					</tr>
					
					<tr>
						<th>
							<label for="nickname">닉네임</label>
						</th>	
						<td>
							<input type="text" name="nickname" size="25" value="${requestScope.bean.nickname}">&nbsp;&nbsp;
							<button onclick="nicknameCheck();">중복 검사</button><br>
							<span class="error">${errornickname}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="password">비밀번호<strong class="require">*</strong></label>
						</th>	
						<td>
							<input type="password" name="password" size="25" placeholder="비밀번호를 입력 해주세요"><br>
							<span class="error">${errorpassword}</span>
						</td>
					</tr>
					
					<tr>
						<th>
							<label for="check-password">비밀번호 확인<strong class="require">*</strong></label>
						</th>	
						<td>
							<input type="password" name="password_re" size="25" placeholder="비밀번호를 한번 더 입력 해주세요" onkeyup="return passwordReCheck()"><br>
							<span id="msg" class="error">${errorpassword2}</span>
						</td>
					</tr>
					
					<tr>
						<th>
							<label for="email">E-mail</label>
						</th>		
						<td>
							<input type="email" name="email" size="25" value="${requestScope.bean.email}"><br>
							<span class="error">${erroremail}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="hphone">휴대폰</label>
						</th>	
						<td>
							<input type="tel" name="phone" size="25" value="${requestScope.bean.phone}"><br>
							<span class="error">${errorphone}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="zipcode">우편번호</label>
						</th>	
						<td>
							<input type="text" id="zipcode" name="zipcode" size="25" value="${requestScope.bean.zipcode}" readonly>
							&nbsp;
							<input type="button" onclick="execPostCode()" value="우편번호 찾기"><br>
							<span class="error">${errorzipcode}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="address">주소</label>
						</th>	
						<td>
							<input type="text" id="address1" name="address1" size="25" value="${requestScope.bean.address1}" readonly><br>
							<span class="error">${erroraddress1}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="address-detail">상세 주소</label>
						</th>	
						<td>
							<input type="text" id="address2" name="address2" size="25" value="${requestScope.bean.address2}"><br>
							<span class="error">${erroraddress2}</span>
						</td>
					</tr>
					
					<tr>
						<th>
							<label for="animal-type">반려동물 종류</label>
						</th>
						<td class="radio-group">
							고양이<input type="radio" name="animal_type" value="고양이" checked="checked">
							강아지<input type="radio" name="animal_type" value="강아지">
							기타<input type="radio" name="animal_type" value="기타">
						</td>	
					</tr>
					
					<tr>	
						<td>
							<p>Point : ${requestScope.bean.mpoint} / 레벨 : ${requestScope.bean.mlevel}&nbsp;</p>
						</td>
					</tr>
					
					<tr>
						<td colspan="2" align="center" class="button-group">
							<button type="submit" value="<%=NoForm%>mModify&id=${sessionScope.loginfo.id}" onclick="return modifyCheck();">
								정보 수정</button>&nbsp;&nbsp;
							<a href="<%=NoForm%>mDelete&id=${sessionScope.loginfo.id}" onclick="return dropCheck();">
								회원 탈퇴</a>&nbsp;&nbsp;
							<button type="reset">취&nbsp;&nbsp;소</button>
						</td>
					</tr>
			</tbody>
		</table>
	</form>
</div>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function execPostCode() {
	    	new daum.Postcode({
		       	 oncomplete: function(data) {
	
		           var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
		           var extraRoadAddr = ''; // 도로명 조합형 주소 변수
		           
		           // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		           // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		           if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		               extraRoadAddr += data.bname;
		           }
		           
		           // 건물명이 있고, 공동주택일 경우 추가한다.
		           if(data.buildingName !== '' && data.apartment === 'Y'){
		              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		           }
		           
		           // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		           if(extraRoadAddr !== ''){
		               extraRoadAddr = ' (' + extraRoadAddr + ')';
		           }
		           
		           // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
		           if(fullRoadAddr !== ''){
		               fullRoadAddr += extraRoadAddr;
		           }
		           
		           // 우편번호와 주소 정보를 해당 필드에 넣는다.
	               document.getElementById("zipcode").value = data.zonecode;
	               document.getElementById("address1").value = fullRoadAddr;
	               
	               // 커서를 상세주소 필드로 이동한다.
	               document.getElementById("address2").focus();
		       }
	    	}).open();
		}
	</script>
</body>
</html>