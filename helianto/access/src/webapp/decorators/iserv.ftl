<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title>Helianto.org - ${title}</title>
		<meta content="text/html; charset=iso-8859-1" 
			http-equiv="Content-Type"/>
		<link href="${base}/iservport.css" rel="stylesheet" type="text/css">
	    ${head}
	</head>

	<body style="background-color:#dddddd; text-align: center; margin-top: 0px; margin-left: 0px;">
		<div align="center">

<table id="menutable"
	   style="background-color: #ffffff; margin-top: 0px; margin-left: 0px; border-spacing: 0px;">
	<tbody>
		<tr style="background-image: url(img/vert-grad.png);">
			<td align="left" height="80">
         		<#if org?exists>
    				<img border="0" src="logo.htm?orgAlias=${org.uniqueAlias}"/>
				<#else>
		    		<img border="0" src="img/helianto-t.png"/>
				</#if>
			</td>
			<td style="text-align: right;">Sobre <a href="about.html">Helianto.org</a></td>
		</tr>
	</tbody>
</table>

			<table id="frametable" 
				   style="background-color: #f0f0f0;">
				${body}
			</table>

	<table style="background-color: #f0f0f0;">
	<tbody>
		<tr>
			<td style="height: 200px;">
				&#160;
			</td>
		</tr>
	</tbody>
	</table>
		</div>
	</body>
	
</html>