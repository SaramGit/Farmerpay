var urls="http://localhost:5252";
function setHeader(){
	
	  $.ajax({
	    type: "GET",
	    async: true,
	    url: "/wish.htm",
	    headers: {
	    	Authorization: "Basic U2FuZ3JhbToxMjM0NQ==",
	    },
	  }).then(function(response) {
	    console.log(response);
	  });
}
function generateToken(){
	var CompanyId=$('#CompanyId').val();
	var password=$('#password').val();
	var AuthRequest={
		    "companyId":CompanyId,
		    "password":password
		  };
	 $.ajax({
		    type: "POST",
		    contentType : 'application/json; charset=utf-8',
		    async: true,
		    dataType: "text",
		    url: "/authenticate.htm",
		    data: JSON.stringify(AuthRequest), 
		    success :function(result) {
		         alert(result)
		    },
		    error: function(){      
		         alert('Error while request..');
		     }
		    });	
}