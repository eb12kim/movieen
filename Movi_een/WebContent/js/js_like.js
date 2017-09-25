

var limit = 3;
	   $(document).ready(function() {
	   $('input.single-checkbox').on('change', function() {
	      if($("input[name='MEMBER_MOVIE_LIKE']:checked").length > limit || $("input[name='MEMBER_TRIP_LIKE']:checked").length > limit) {
	    	 	alert("최대 3개까지만 선택할수 있습니다.");
	          this.checked = false;
	      }
	   });
	   });
	   
	   
	   
		$(document).ready(function() {
			$("#btn_update").click(function() {
				var x = confirm("수정내용이 사라집니다. 회원정보로 돌아가시겠습니까?");
				if (x == true) {
					window.location = "memberView.me?id=${member.getMEMBER_ID()}"
				} else {
					return false;
				}

			});
		});