<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../common/common.jsp"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MyPetDiary | 회원 목록</title>
</head>

<body>
	<h1>회원 목록</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>이름</th>
				<th>닉네임</th>
				<th>E-mail</th>
				<th>핸드폰 번호</th>
				<th>주소</th>
				<th>반려동물</th>
				<th>Point</th>
				<th>Level</th>		
			</tr>
			</thead>
				
			<tbody>
				<c:forEach var="bean" items="${requestScope.lists}">
					<tr>
						<td>${bean.id}</td>
						<td>${bean.name}</td>
						<td>${bean.nickname}</td>
						<td>${bean.email}</td>
						<td>${bean.phone}</td>
						<td>${bean.address1}&nbsp; ${bean.address2}</td>
						<td>${bean.animal_type}</td>
						<td>${bean.mpoint}</td>
						<td>${bean.mlevel}</td>
					</tr>
				</c:forEach>
			</tbody>				
		</table>
</body>
</html>