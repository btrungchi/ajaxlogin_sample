$(document).ready(function(){
   $("#login").click(function(){  
        var email=$("#email").val();
        var password=$("#password").val();
        if(email=='' || password==''){
          $("#result").html("Please fill in both your email and password");
        } else {
            $.ajax({
            type: "POST",
            url: "/login",
            data: {
              email: email,
              password: password
            },            
            success: function(jsonData){
              console.log(jsonData);              
              var email = jsonData.email;
              var loginSuccess = jsonData.loginSuccess;

              if (email && loginSuccess == "Yes") {
                $("#result").html(email + " login successed");
              } else {
                $("#result").html("Wrong email/password");
              }
            },
            error: function(jqXHR, exception) {
                $("#result").html(exception);
            }
            });
            return false;
        }  
    });
});
