$(document).ready(function(){
    var sds = document.getElementById("dum");
    if(sds == null){
        alert("You are using a free package.\n You are not allowed to remove the tag.\n");
	$("#frm").hide();
    }
    var sdss = document.getElementById("dumdiv");
    if(sdss == null)
    {
        alert("You are using a free package.\n You are not allowed to remove the tag.\n");
    }
    if(sdss!=null)
    {
             $('#incorrect').click(function(){  
			   var nme = $("#name").val(); 
               var password = $("#pass").val(); 

               if (nme == "test" && password == "test") 
               {
                   $("#message").html("<font style='color:green;'>Username and Password is correct!!!</font>");
               }   
               else
               {
                var duration = 30;                   

                $('#login-box').animate({marginLeft:('+=40')}, duration,function(){
                $(this).animate({marginRight:('+=40')}, duration, function(){
                $(this).animate({marginTop:('+=40')}, duration, function(){	
		$(this).animate({marginBottom:('+=40')}, duration, function(){
		$(this).animate({marginLeft:('-=40')}, duration,function(){
		$(this).animate({marginRight:('-=40')}, duration, function(){
                $(this).animate({marginTop:('-=40')}, duration, function(){					 
                $(this).animate({marginBottom:('-=40')}, duration);
                      });
                    });
                  });
			     });
		        }); 
	           });
              }); 
	                          
                $("#message").html('Invalid username or password');
                $("#name").val("");
                $("#pass").val("");
	           }  		  
            });
           } 
         });
      
