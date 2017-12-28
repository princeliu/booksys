//提交登录
function submits(loginForm){
	var date = "";
	$.ajax({
	    type: 'POST',
	    async: false,
	    url: basePath+"sysUser/add",
	    data: '',
	    success: function(data){
	       date = data.msg;
	   }
	});
	alert("date="+date+"=date");
	return false;
}