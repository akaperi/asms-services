<html>
<head>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="fisheye.js"></script>
<link type="text/css"  href="style.css" rel="stylesheet"> 
</head>
<body>
<div id="fisheye" class="fisheye">
		<div class="fisheyeContainter">
			<a href="#" class="fisheyeItem"><img src="images/home.png" width="50" /></a>&nbsp;&nbsp;
			<a href="#" class="fisheyeItem"><img src="images/email.png" width="50" /></a>&nbsp;&nbsp;
			<a href="#" class="fisheyeItem"><img src="images/portfolio.png" width="50" /></a>&nbsp;&nbsp;
			<a href="#" class="fisheyeItem"><img src="images/rss.png" width="50" /></a>&nbsp;&nbsp;
			<a href="#" class="fisheyeItem"><img src="images/music.png" width="50" /></a>&nbsp;&nbsp;
    		</div>
</div>
<div align=center style="font-size: 10px;color: #dadada;" id="dumdiv"><a href="http://www.hscripts.com" id="dum" style="margin-left:100px;text-decoration:none;color:green;">©h</a></div>
<script type="text/javascript">
$(document).ready(
		function()
		{
			$('#fisheye').Fisheye(
				{
					maxWidth:60,
					items: 'a',
				container: '.fisheyeContainter',
					itemWidth:50,
					proximity: 90,
					halign : 'center'
				}
			)
			
		}
	);

</script>

</body>
</html>