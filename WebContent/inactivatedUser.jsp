<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
									<h1>You have not activated your account yet!</h1>
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
					<form name="registerForm" action="register" method="post">
						<input type="hidden" name="email" value="${email}"/>
						<input type="hidden" name="reactivate" value="true"/>
						<div class="wrapcontent">
							<p>You had registered with TutorMe as the email address: <b><i>${email}</i></b> but you haven't activated your account yet.</p>
							<p>We have send the confirmation email to your inbox, please "verify email address" in order to access our full services in TutorMe. If you have not received the confirmation email, please <a onClick="document.forms['registerForm'].submit();" href="#">request a new verification email</a>.</p>
						</div>
					</form>
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