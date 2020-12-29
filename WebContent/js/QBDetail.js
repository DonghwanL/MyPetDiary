	function moveList() {
		location.href = "<%=NoForm%>QBList";
	}
	
	function deletePost() {
		if (confirm('게시물을 삭제 하시겠습니까?')) {
			location.href = "<%=NoForm%>QBDelete&no=${bean.no}&${requestScope.parameters}";
		}
	}
	
	function modifyPost() {
		if (confirm('게시물을 수정 하시겠습니까?')) {
			location.href = "<%=NoForm%>QBModify&no=${bean.no}&${requestScope.parameters}";
		}
	}
	
	function likeCount(){
		$.ajax({
			url: "LikeUpdateAction",
			type: "POST",
			cache: false,
			dataType: "json",
			data: $('#like_form').serialize(),
			success:
			function(data){ //ajax통신 성공시 넘어오는 데이터 통째 이름 =data
				alert("'좋아요'가 반영되었습니다!") ; // data중 put한 것의 이름 like
				$("#like_result").html(data.like); //id값이 like_result인 html을 찾아서 data.like값으로 바꿔준다.
			},
		
			error:
			function (request, status, error) {
				alert("ajax실패")
			}
		});
	}