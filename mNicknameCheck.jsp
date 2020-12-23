<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	function meClose( isCheck ){
		//alert( isCheck ) ;
		opener.document.myform.isCheck.value = isCheck ;
		self.close();
	}
</script>	
</head>
<body>
	<h1></h1>
	<h1></h1>
	<h1></h1>
	<div class="container">
		<p align="center">${message}</p>
		<div class="row" align="center">
			<button class="btn btn-primary" type="button" 
				onclick="meClose('${isCheck}');">
				닫&nbsp;&nbsp;기
			</button>
		</div>
	</div>
</body>
</html>
