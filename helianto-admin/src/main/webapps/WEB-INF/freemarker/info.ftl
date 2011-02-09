<#--
 # Info page
 #
 # @author Mauricio Fernandes de Castro
 #-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Welcome to Helianto</title>
	<link href="../${testRoot!}helianto.css" rel="stylesheet" />
	<link rel="icon" type="image/png" href="../favicon.png" />
</head>
<body>
	<h2>Thank you for your interest in the Helianto Project</h2>
	
	<p>Please, read bellow some useful information to get started with any Helianto-based project.</p>
	
	<h3>Root, trunk and business spaces</h3>
	
	<p>To get started quickly, Helianto provides installation routines to create a user named "manager".
	A manager is automatically assigned full privileges, being able to control both so called "root" and 
	and "trunk" entities.</p>
	
	<p>This concept help to bind entities within isolation levels and create a management hierarchy. One can say, 
	for example, that the organization "A" creates a business space isolated from the business space of 
	organization "B". Trunk entities like accounts or customers from organization "A" are not visible to 
	any organization "B" user. Root entities, however, may be shared across business spaces, what is appropriate
	in the case of provinces or system level services.</p>
	
	<p>One important exception to this rule is the identity. Identities do not belong to any business space, neither
	to a broader name space, created by a special entity called "Operator", that encompasses all its child business spaces
	(technically is possible to have more than one set of name space + operator + business spaces). The identity maps to a
	person in the real world, and being so, must be unique across any possible boundary.</p>
	
	<h3>Your authorization</h3>
	
	<p>To map an identity to a business space, Helianto provides users and user groups, making it possible for the same
	identity to have different access privileges when jumping from one business space to another. The minimum set of privileges
	is granted for members of a group named "USERS", that must exist for any business, as well as other mandatory group named 
	"ADMIN", to hold administrative roles. All of this is applied to an application using the Spring Security API, a very strong
	and flexible library, well fitted to keep authentication and authorization rules in the so called "Security Context".</p>
	
	<h3>Getting started</h3>
	
	<p>Helianto's mission (see <a target="_new" href="http://www.helianto.org">project's web page</a>) does not encourage 
	the development of a working application, including a WEB or GUI user interface. It promotes, instead, the creation of an
	extensible service layer API. Nevertheless, a WEB application packaged in helianto.war is provided as a sample. Please,
	deploy it to any servlet container (like Tomcat or Jetty) and login to 
	<a target="_new" href="http://localhost:8080/helianto">http://localhost:8080/helianto</a> as <b>manager</b> with password 
	<b>"inactive"</b> (without the quotes). The admin application uses a stand alone installer to prepare a minimum 
	environment with a <b>DEFAULT</b> operator (a single namespace), a <b>DEFAULT</b> entity, in top of the first business
	space, and the required "USER" and "ADMIN" groups with the appropriate privileges applied to the manager user.
	You can use the sample application to install some extra business spaces to be managed by you.</p>
	
	<h3>Helianto is open source (Apache v2.0 license).</h3>
	
	<p>Download the source code or binaries from <a href="http://sourceforge.net/projects/helianto/">sourceforge</a>.</p>
	<a target="_new" href="http://www.sourceforge.net">
	<img src="http://sflogo.sourceforge.net/sflogo.php?group_id=161555&type=11" alt="" />
	</a>
	<hr/>
	<h3>Since its first publication (2006), Helianto is built on Spring Framework, Hibernate (JPA) and other Java artifacts.</h3>
	<p>A lot of work has been done to keep Helianto in pace with the latest
	developments in the Java arena. If you are interested to learn more, please, check:</p>
	
	<p><a target="_new" href="http://www.springframework.org/">http://www.springframework.org</a></p>
	<p><a target="_new" href="http://www.hibernate.org/">http://www.hibernate.org</a></p>
	
	<h3>and join us!</h3>

</body>
</html>
