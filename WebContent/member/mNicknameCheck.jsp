<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script>
		function CheckClose() {
			self.close();
		}
	</script>
	
	<style>
		body {
			font-family: 'Noto Sans KR', sans-serif;
			font-size: 13px;
		}
		
		button {
		  font-size: 13px;
		  color: #fff;
		  background-color: #2368B7;
		  border: none;
		  border-radius: 4px;
		  padding: 7px;
		}
		
		.nickcheck-form {
			padding: 20px;	
		}
	</style>
</head>

<body>
	<div class="nickcheck-form">
		<p align="center">${message}</p>
		<div class="nick-check-button" align="center">
			<button type="button" onclick="CheckClose();">닫&nbsp;&nbsp;기</button>
		</div>
	</div>
</body>
</html>