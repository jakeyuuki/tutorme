<!DOCTYPE html>
<html lang="en-US">
<head>
	<jsp:include page="sharedJSP/head.jsp"/>
</head>
<body>
	<!-- HEADER
	================================================== -->
	<jsp:include page="sharedJSP/header.jsp"/>
	
	
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
									<h1>My Account</h1>
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
					<div class="wraplogin">
						<form id="edd_login_form" class="edd_form form-horizontal" action="login" method="post">
							<fieldset>
								<legend>Log into Your Account</legend>
									
								<% if(request.getAttribute("loginError") != null) { %>
									<div class="alert alert-danger"> ${loginError}</div> 
								<% } %>
									
								<div class="form-group">
									<label class="control-label col-xs-1">Email</label>
									
									<div class="col-xs-5">
										<input name="email" id="edd_user_login" class="required edd-input form-control" type="text" title="Email" value="${loginEmail}"/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-xs-1">Password</label>
									<div class="col-xs-5">
										<input name="password" id="edd_user_pass" class="password required edd-input form-control" type="password"/>
									</div>
								</div>
								<p>
									<input id="edd_login_submit" type="submit" class="edd_submit" name="action" value="signin"/> <!-- matching request.getParameter("action") in LoginController -->
								</p>
								<p class="edd-lost-password has-success">
									<a href="#" title="Lost Password">
									Lost Password? </a>
								</p>
							</fieldset>
						</form>
					</div>
					<span class="or">or</span>
					<div class="wrapregister">
						<form id="edd_register_form" class="edd_form form-horizontal" action="register" method="post">
							<fieldset>
								<legend>Register New Account</legend>
								<div class="form-group">
									<label class="control-label col-xs-1">Given Name</label>
									<div class="col-xs-5">
										<input id="edd-user-login" class="required edd-input form-control" value="${firstname}" type="text" name="firstname" title="Given Name"/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-xs-1">Family Name</label>
									<div class="col-xs-5">
										<input id="edd-user-login" class="required edd-input form-control" value="${lastname}" type="text" name="lastname" title="Family Name"/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-xs-1">Email</label>
									<div class="col-xs-5">
										<input id="edd-user-email" class="required edd-input form-control" value="${email}" type="email" name="email" title="Email Address"/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-xs-1">Password</label>
									<div class="col-xs-5">
										<input id="edd-user-pass" class="password required edd-input form-control" type="password" name="password"/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-xs-1">Confirm Password</label>
									<div class="col-xs-5">
										<input id="edd-user-pass2" class="password required edd-input form-control" type="password" name="confirm_password"/>
									</div>
								</div>
								<p>
									<input class="button" name="edd_register_submit" type="submit" value="Register"/>
								</p>
							</fieldset>
						</form>
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
	<!-- /script -->
	
</body>
</html>