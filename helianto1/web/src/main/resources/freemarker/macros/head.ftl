<#--
 # Convenience to create headers.
 #
 # @author Mauricio Fernandes de Castro
-->
<#macro head title="Helianto Project">
	<head>
	  <meta http-equiv="Content-Type" 
	  content="text/html; charset=ISO-8859-1" />
	  <title>${title}</title>
	  <link href="helianto.css" rel="stylesheet" />
	  <#nested />
	</head>
</#macro>