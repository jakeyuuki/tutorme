<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="tutorme.controllers.LoginController"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
	<jsp:include page="sharedJSP/head.jsp"/>
	<link rel="stylesheet" href="assets/css/font-awesome-animation.css" media="all" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div id="boxedlayout">

		<!-- HEADER
		================================================== -->
		<nav id="wow-menu" class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand">
						<img class="clickable" src="../assets/img/logo.png"/>
					</a>
				</div>
				<!-- Menu -->
				<div id="bs-example-navbar-collapse-1" class="collapse navbar-collapse">
					<ul id="menu-top" class="nav navbar-nav navbar-right">
						<li><a href="./">Home</a></li>
						<li><a href="shop.html">Tutors</a></li>
						<li><a href="checkout.html">Courses</a></li>
						<li class="active"><a href="myAccount.jsp">My Account</a></li>
					</ul>
				</div>
			</div>
		</nav>
		
		<!-- PAGE CONTENT
		================================================== -->
		<div id="boxedcontent">
		
			<div class="templatemyaccount">
			
				<!-- TITLE BEGINS -->
				<div class="headerimage" style="background-image: url(assets/img/back_to_school.jpg);">
					<div class="headercontent">
						<div class="container">
							<div class="row">
								<div class="col-md-12">
									<div class="fleft">
										<h1>Welcome back, <%=request.getAttribute("firstName")%>!</h1>
									</div>
									<div class="fright breadc">
										<a href="index.jsp">Home</a> / My Account
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- TITLE ENDS -->
				<!-- http://jsfiddle.net/LHZXw/1/ validation before submission
				 -->
				<div class="container padtop40 padbot50">
					<div class="row">
					<!-- MAIN CONTENT BEGINS -->
					<div class="col-md-12">
						<div class="wrapcontent">
							<p class="text-center">
								<i class="fa fa-spinner faa-spin animated fa-4x"></i><br/>
								Log in success. Please wait a few minute to let us redirect you to your account's page<br/>
								<a href="/index.jsp">Click here if the page does not redirect after 5 seconds.</a>
							</p>
						</div>
					</div>				
					<!-- MAIN CONTENT ENDS -->
						
					</div>				
				</div>
			</div>		
			
		</div>
		
		<!-- FOOTER
		================================================== -->		
		<jsp:include page="sharedJSP/footer.jsp"/>
		<!-- /footer -->
	
		<!-- SCRIPTS
		================================================== -->			
		<jsp:include page="sharedJSP/javascript.jsp"/>
				
		<script type='text/javascript'>
		$( document ).ready(function() {
			<% if(LoginController.isSessionReady(session)) { %>
				window.setTimeout(function() {
					location.href = "/";
				}, 2000);
			<% } %>
		});
		</script>
	</div>
</body>
</html>