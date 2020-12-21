<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<form name="modify-form" action="" method="POST">
			<input type="hidden" name="command" value="mModify">
			
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
							<input type="text" name="disabled-id" disabled="disabled" size="23" value="${requestScope.bean.id}">
							<input type="hidden" name="id" value="${requestScope.bean.id}">
	 						<span class="error">${error_id}</span>	
						</td>
					</tr>	
						
					<tr>	
						<th>
							<label for="name">이름</label>
						</th>
						<td>
							<input type="text" name="disabled-name" disabled="disabled" size="23" value="${requestScope.bean.name}">
							<input type="hidden" name="Name" value="${requestScope.bean.name}">
							<span class="error">${error_name}</span>
						</td>
					</tr>
					
					<tr>
						<th>
							<label for="nickname">닉네임</label>
						</th>	
						<td>
							<input type="text" name="nickname" size="23" value="${requestScope.bean.nickname}">&nbsp;&nbsp;
							<button onclick="checkDuplicateID();">중복 검사</button>
							<span class="error">${error_nickname}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="passowrd">패스워드</label>
						</th>	
						<td>
							<input type="password" name="password1" size="23">
							<span class="error">${error_password}</span>
						</td>
					</tr>
					
					<tr>
						<th>
							<label for="check-password">패스워드 확인</label>
						</th>	
						<td>
							<input type="password" name="password2" size="23">
							<span class="error">${error_repassword}</span>
						</td>
					</tr>
					
					<tr>
						<th>
							<label for="email">E-mail</label>
						</th>		
						<td>
							<input type="email" name="email" size="23">
							<span class="error">${error_email}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="hphone">핸드폰 번호</label>
						</th>	
						<td>
							<input type="tel" name="hphone" size="23">
							<span class="error">${error_hphone}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="postcode">우편번호</label>
						</th>	
						<td>
							<input type="text" id="postcode" name="postcode" readonly size="23" value="${requestScope.bean.zipcode}">
							&nbsp;
							<button onclick="execDaumPostcode();">우편번호 찾기</button>
							<span class="error">${error_zipcode}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="adress1">주소</label>
						</th>	
						<td>
							<input type="text" id="address1" name="address1" readonly size="23" value="${requestScope.bean.address1}">
							<span class="error">${error_address}</span>
						</td>
					</tr>
					
					<tr>	
						<th>
							<label for="address2">상세 주소</label>
						</th>	
						<td>
							<input type="text" id="address2" name="address2" size="23" value="${requestScope.bean.address2}">
							<input type="text" id="extraAddress">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="error">${error_address2}</span>
						</td>
					</tr>
					
					<tr>	
						<td>
							<p>Point : ${requestScope.bean.mpoint} / 레벨 : ${requestScope.bean.mlevel}&nbsp;</p>
						</td>
					</tr>
					
					<tr>
						<td colspan="2" align="center" class="button-group">
							<button type="submit">정보 수정</button>&nbsp;&nbsp;
							<a href="#" onclick="return dropCheck();">
								회원 탈퇴</a>&nbsp;&nbsp;
							<button type="reset">취소</button>&nbsp;&nbsp;
						</td>
					</tr>
			</tbody>
		</table>
	</form>
	

	<script>
		function dropCheck() {
			if (!confirm('회원 탈퇴 하시겠습니까?')) {
				return false;
			}
		}
		
		function execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	           		console.log(data);
	           		
	    			 // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }

	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                    document.getElementById("extraAddress").value = extraAddr;
	                
	                } else {
	                    document.getElementById("extraAddress").value = '';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('postcode').value = data.zonecode;
	                document.getElementById('address1').value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById('address2').focus();
	            }
	        }).open();
		}
	</script>
</body>
</html>