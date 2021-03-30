$(function(){
//回车事件
$('body').keydown(function(event){  
  if(event.keyCode==13){  
    validateLogin();
  }  
});           
             
});   

//验证登录
function validateLogin(){

              //验证表单
              var result =  validForm(['userName','password']);
              if(!result){
                return ;
              }
              
		      var userName = $("#userName").val(); //获取用户名
			  var password = $("#password").val(); //获取密码
			  
			  //去空格
		      userName = userName.replace(/\s+/g,"");
		      password = password.replace(/\s+/g,"");
			  
		      jsutil.defaultSerializeReq(
		    	  mUrl.login,
				  {
				    "userName":userName,
		            "password":password
				  },
				  function(data){
				    if(data.resultType=="success"){
		                window.location.href=mUrl.preIndexPage;
		             }else{
		                parent.alertBox.showAlert(data.resultMsg,'error');
		             }
				  }
			   );
}     

//重置用户名和密码 
function resetLogin(){
	        $("#userName").val("");
	        $("#password").val("");
	        return false;
}   