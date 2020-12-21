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
		
		function nicknameCheck() {
			var nickname = document.modify_form.nickname.value;
			
			if (nickname.length == 0) {
				alert('닉네임을 입력해 주세요.');
				document.modify_form.nickname.focus(); 
				return false;
			}
			
			var url = '<%=NoForm%>mNicknameCheck&nickname=' + nickname; 
			window.open(url, 'checkPopUp', 'height=150, width=300');
			}
		}
	</script>
</head>

<body>
		<form name="modify_form" action="<%=YesForm%>" method="POST">
			<input type="hidden" name="command" value="mModify">
			<input type="hidden" name="isCheck" value="false"> 
			
			<table class="modify-table">
				<thead>
					<tr>
						<th><h1>회원정보 수정</h1></th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<th>
							<label for="id">ID</label>
						</th>
						<td>
							<input type="text" name="id" size="25" value="${requestScope.bean.id}" readonly>
	 						<span class="error">${errorid}</span>	
						</td>
					</tr>	
						
					<tr>	
						<th>
							<label for="name">이름</label>
						</th>
						<td>
							<input type="text" name="name" size="25" value="${requestScope.bean.name}" readonly>
							<span class="error">${errorname}</span>
						</td>
					</tr>
					
					<tr>
						<th>
							<label for="nickname">닉네임</label>
						</th>	
						<td>
							<input type="text" name="nickname" size="25" value="${requestScope.bean.nickname}">&nbsp;&nbsp;
							<button onclick="nicknameCheck();">중복 검사</button>
							<span class="error">${errornickname}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="passowrd">패스워드</label>
						</th>	
						<td>
							<input type="password" name="password1" size="25">
							<span class="error">${errorpassword}</span>
						</td>
					</tr>
					
					<tr>
						<th>
							<label for="check-password">패스워드 확인</label>
						</th>	
						<td>
							<input type="password" name="password2" size="25">
							<span class="error">${errorrepassword}</span>
						</td>
					</tr>
					
					<tr>
						<th>
							<label for="email">E-mail</label>
						</th>		
						<td>
							<input type="email" name="email" size="25" value="${requestScope.bean.email}">
							<span class="error">${erroremail}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="hphone">핸드폰 번호</label>
						</th>	
						<td>
							<input type="tel" name="phone" size="25" value="${requestScope.bean.phone}">
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
							<input type="button" class="zipcode" onclick="execPostCode()" value="우편번호 찾기"><br>
							<span class="error">${errorzipcode}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="address1">주소</label>
						</th>	
						<td>
							<input type="text" id="address1" name="address1" size="25" value="${requestScope.bean.address1}" readonly>
							<span class="error">${erroraddress1}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="address2">상세 주소</label>
						</th>	
						<td>
							<input type="text" id="address2" name="address2" size="25" value="${requestScope.bean.address2}">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="error">${erroraddress2}</span>
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
							<button type="reset">취소</button>&nbsp;&nbsp;
						</td>
					</tr>
			</tbody>
		</table>
	</form>
	
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