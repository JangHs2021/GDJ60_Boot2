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
 
$("#passWord").blur(pwCheck);
	
function pwCheck(){
	if($("#passWord").val() === "") {
		console.log("필수 사항 입니다")
	}
}

$("#passWordCheck").blur(pwCheck2);

function pwCheck2(){
	if($("#passWord").val() == $("#passWordCheck").val()) {
		console.log("패스워드가 맞습니다")
	} else {
		console.log("패스워드가 틀렸습니다")
	}
}
	 
$("#name").blur(nameCheck);
	
function nameCheck(){
	if($("#name").val() === "") {
		console.log("필수 사항 입니다")
	}
}

$("#email").blur(emailCheck);
	
function emailCheck(){
	if($("#email").val() === "") {
		console.log("필수 사항 입니다")
	}
}

$("#birth").blur(birthCheck);
	
function birthCheck(){
	if($("#birth").val() === "") {
		console.log("필수 사항 입니다")
	}
}
	

 
 