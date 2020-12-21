<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="nickCheck-Container">
		<p align="center">${message}</p>
		<div class="nickCheck--button" align="center">
			<button type="button" onclick="CheckClose('${isCheck}');">
				닫&nbsp;&nbsp;기
			</button>
		</div>
	</div>
	<script>
		function CheckClose(isCheck) {
			opener.document.modify_form.isCheck.value = isCheck;
			self.close();
		}
	</script>
</body>
</html>