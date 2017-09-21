$("ul.thumb li").hover(function() {	$(this).css({'z-index' : '10'});	$(this).find('img').addClass("hover").stop()
		.animate({marginTop: '-110px',	marginLeft: '-140px',	top: '50%',	left: '50%',width: '160px',		height: '160px', 
			padding: '15px'
		},100); 
	$(this).next().css({'z-index' : '30'}); $(this).next().find('img').addClass("hover").stop().animate({	marginTop: '-130px', 
			marginLeft: '-120px',
			top: '50%',
			left: '50%',
			width: '120px', 
			height: '120px', 
			padding: '15px'
		}, 200); 
  $(this).prev().css({'z-index' : '30'});
  $(this).prev().find('img').addClass("hover").stop()
		.animate({
			marginTop: '-130px', 
			marginLeft: '-120px',
			top: '50%',
			left: '50%',
			width: '120px', 
			height: '120px', 
			padding: '15px'
		},100); 
  
  
  } , function() {  
	$(this).css({'z-index' : '0'});
	$(this).find('img').removeClass("hover").stop() 
		.animate({
			marginTop: '0', 
			marginLeft: '0',
			top: '0',
			left: '0',
			width: '100px',
			height: '100px',
			padding: '10px'
		}, 100);

$(this).next().css({'z-index' : '0'});
$(this).next().find('img').removeClass("hover").stop() 
	.animate({
			marginTop: '0', 
			marginLeft: '0',
			top: '0',
			left: '0',
			width: '100px',
			height: '100px',
			padding: '10px'
		}, 100);
$(this).prev().css({'z-index' : '0'});
$(this).prev().find('img').removeClass("hover").stop() 
	.animate({
			marginTop: '0', 
			marginLeft: '0',
			top: '0',
			left: '0',
			width: '100px',
			height: '100px',
			padding: '5px'
		}, 100);

});


