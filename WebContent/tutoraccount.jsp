<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
	<jsp:include page="sharedJSP/head.jsp"/>
	<link rel="stylesheet" href="ckeditor/samples/toolbarconfigurator/lib/codemirror/neo.css">
</head>
<body>
	<!-- HEADER
	================================================== -->
	<jsp:include page="sharedJSP/header.jsp"/>
	<!-- PAGE CONTENT
	================================================== -->
	<div class="templatemyaccount">
	
		<!-- TITLE BEGINS -->
		<div class="headerimage" style="background-image: url(assets/img/back_to_school.jpg);">
			<div class="headercontent">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="fleft">
								<h1>Tutor Details</h1>
							</div>
							<div class="fright breadc">
								<a href="index.jsp">Home</a> / Tutor Details
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- TITLE ENDS -->
		
		<div class="container padtop40 padbot50">
		<h1>Edit Tutor Profile</h1>
		<div class="row">
			<div class="col-md-9">
				<div class="adjoined-bottom">
					<div class="grid-container">
						<div class="grid-width-500">
							<!-- 
							For later reference
							<form id="edd_login_form" class="edd_form form-horizontal" action="login" method="post">
							<input id="tutor_ckeditor_submit" type="submit" class="edd_submit" name="action" value="Sign in"/>
							-->
							<form id="tutor_ckeditor">
								<div id="editor">
									<p>Edit your profile.</p>
								</div>
								<input id="tutor_ckeditor_submit" type="submit" class="edd_submit" />
								
							</form>
						</div>
					</div>
				</div>	
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
	<script src="/ckeditor/ckeditor.js"></script>
	<script src="/ckeditor/config.js"></script>
	<!-- /script -->
</body>
</html>