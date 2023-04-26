/**
 *  Board Form 유효성 검사
 */

$("#submitButton").on("click", function(){
	
	if($("#title").val() === ""){
		alert("제목을 입력해주세요")
	} else if($("#writer").val() === ""){
		alert("작성자를 입력해주세요")
	} else {
		$("#contactForm").submit();
	}
})

