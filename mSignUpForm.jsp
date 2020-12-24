<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>    
<%@ include file="./../common/common.jsp"%>
    
<%
	int myoffset = 2;
	int mywidth = 12 - 2 * myoffset;
	int formleft = 3;
	int formright = 12 - formleft;
%>
<html>
<head>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="jquery.validate.js"></script>
<style type="text/css">
	.form-group {
		margin-bottom: 2px;
	}
	.good-result{/* 상태 양호 */
		font-size : 10pt;
		color:pink;
		font-weight: bolder;
	}
</style>
<script type="text/javascript">
		function checkDuplicateId(  ){
			var id = document.myform.id.value ;
			if( id.length == 0 ){
				alert('아이디를 입력해 주세요') ;
				document.myform.id.focus() ; 
				return false ;
			}
			var url='<%=NoForm%>mIDCheck&id=' + id ; 
			window.open(url, 'mywin', 'height=150,width=300') ;
		}
		function checkDuplicatenickname(  ){
			var nickname = document.myform.nickname.value ;
			if( nickname.length == 0 ){
				alert('닉네임을 입력해 주세요') ;
				document.myform.nickname.focus() ; 
				return false ;
			}
			var url='<%=NoForm%>mNicknameCheck&nickname=' + nickname ; 
			window.open(url, 'mywin', 'height=150,width=300') ;
		}
		
		function findmZipcode( ){
			var url='<%=NoForm%>mZipCheck';
			window.open(url, 'mywin',
				'height=600,width=720,status=yes,scrollbars=yes,resizable=no');
		}
		
	function isCheckFalse() {
		document.myform.isCheck.value = false;
	}
	function checkForm() {
		var isCheck = document.myform.isCheck.value;
		//alert( isCheck ) ;
		if (isCheck == 'false') {
			alert('아이디 중복 체크를 우선 해주세요.');
			return false;
		}
	}
</script>
</head>
<body>
	<div id="main_container" align="center"
		class="container col-xs-offset-<%=myoffset%> col-lg-offset-<%=myoffset%> col-xs-<%=mywidth%> col-lg-<%=mywidth%>">
		<div class="panel panel-default ">
			<div class="panel-heading" align="center">
				<font color="black"><b>회원 가입</b></font> 
			</div>
			<div class="panel-body sub_container">
				<form id="myform" name="myform" class="form-horizontal" role="form"
					action="<%=YesForm%>" method="post">
					<input type="hidden" name="command" value="mSignUp"> <input
						type="hidden" name="isCheck" value="false"> 
						
					<div class="form-group">
						<label for="id"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">아이디</label>
						<div class="col-xs-<%=formright - 2%> col-lg-<%=formright - 2%>">
							<input type="text" name="id" id="id" class="form-control"
								placeholder="아이디" value="${requestScope.bean.id}"
								onkeyup="isCheckFalse();"> <span class="err" id="spanid">${errid}</span>
						</div>
						<div class="col-xs-<%=2%> col-lg-<%=2%>" align="left">
							<input type="button" class="btn btn-info" value="중복 검사"
								onclick="checkDuplicateId();">
						</div>
					</div>
					<div class="form-group">
						<label for="nickname"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">닉네임</label>
						<div class="col-xs-<%=formright - 2%> col-lg-<%=formright - 2%>">
							<input type="text" name="nickname" id="nickname" class="form-control"
								placeholder="닉네임" value="${requestScope.bean.nickname}"
								onkeyup="isCheckFalse();"> <span class="err" id="spannickname">${errnickname}</span>
						</div>
						<div class="col-xs-<%=2%> col-lg-<%=2%>" align="left">
							<input type="button" class="btn btn-info" value="중복 검사"
								onclick="checkDuplicatenickname();">
						</div>
					</div>
					<div class="form-group">
						<label for="name"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">이름</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<input type="text" name="name" id="name" class="form-control"
								placeholder="이름" value="${requestScope.bean.name}"> <span
								class="err">${errname}</span>
						</div>
					</div>
					<div class="form-group">
						<label for="password"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">비밀
							번호</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<input type="password" name="password" id="password"
								class="form-control" placeholder="비밀 번호를 넣어 주세요"
								value=""> <span
								class="err">${errpassword}</span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="email"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">이메일 
						</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
                        <input type="text" name="email" class="form-control">@<select name="email">
                            <option>naver.com</option>
                            <option>daum.net</option>
                            <option>gmail.com</option>
                            <option>nate.com</option>                        
                        </select>
                    </div>
                </div>
                <div class="form-group">
						<label for="phone"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">핸드폰 
							번호</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<input type="text" name="phone" id="phone"
								class="form-control" placeholder="핸드폰 번호를 넣어 주세요"
								value=""> <span
								class="err">${errphone}</span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="zipcode"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">우편
							번호</label>
						<div class="col-xs-<%=formright - 2%> col-lg-<%=formright - 2%>">
							<input type="text" name="fakezipcode" id="fakezipcode"
								class="form-control" disabled="disabled" placeholder="우편 번호"
								value=""> <input type="hidden" name="zipcode"
								id="zipcode" value="">
						</div>
						<div class="col-xs-<%=2%> col-lg-<%=2%>" align="left">
							<input type="button" class="btn btn-info" value="우편 번호 찾기"
								onclick="findZipcode();">
						</div>
						<span class="err">${errzipcode}</span>
					</div> 
					<div class="form-group">
						<label for="address1"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">주소</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<input type="text" name="fakeaddress1" id="fakeaddress1"
								class="form-control" disabled="disabled" placeholder="주소"
								value=""> <input type="hidden" name="address1"
								id="address1" value=""> <span class="err">${erraddress1}</span>
						</div>
					</div> 
					<div class="form-group">
						<label for="address2"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">세부
							주소</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<input type="text" name="address2" id="address2"
								class="form-control" placeholder="세부 주소" value="">
							<span class="err">${erraddress2}</span>
						</div>
					</div>
					
						<%-- <div class="form-group">
						<label for="mlevel"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">등급</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<input type="text" name="mlevel" id="mlevel" class="form-control"
								placeholder="등급" value="${requestScope.bean.mlevel}"> <span
								class="err">${errmlevel}</span>
						</div>
						</div>
						<div class="form-group">
						<label for="mpoint"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">포인트</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<input type="text" name="mpoint" id="mpoint" class="form-control"
								placeholder="포인트" value="${requestScope.bean.mpoint}"> <span
								class="err">${errmpoint}</span>
						</div>
						</div> --%>
						<div class="form-group">
						<label for="created_at"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">가입일자</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<input type="text" name="created_at" id="created_at" class="form-control"
								placeholder="가입일자" value="${requestScope.bean.created_at}"> <span
								class="err">${errcreated_at}</span>
						</div>
						</div>
					<div class="form-group">
						<div class="col-xs-<%=12%> col-lg-<%=12%>" align="center">
							<button type="submit" class="btn btn-default"
								onclick="return checkForm();">
								<b>회원 가입</b>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			//alert('dd') ;
			$(".datepicker").datepicker();
			$("#spanid").addClass('good-result');
		});
	</script>
</body>
</html>