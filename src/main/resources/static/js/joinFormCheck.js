/**
 * Joing Form 에서 검증
 */

 $("#userName").blur(idDuplicateCheck);
 
 function idDuplicateCheck(){
	 
	 $.ajax({
		 type: "GET",
		 url: "./idDuplicateCheck",
		 data: {
			 userName: $("#userName").val()
		 },
		 success: function(result){
			 if(result) {
				console.log("사용 가능한 아이디")
			 } else {
				console.log("중복된 아이디")
			 }
		 },
		 error: function(){
			 console.log("error")
		 }
	 })
	 
 }