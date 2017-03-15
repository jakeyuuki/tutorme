<!DOCTYPE html>
<html lang="en-US">
<head>
	<jsp:include page="sharedJSP/head.jsp"/>
</head>
<body>
	<!-- HEADER
	=================================================== -->
	<jsp:include page="sharedJSP/header.jsp"/>
	
	<!-- PAGE CONTENT
	=================================================== -->
	
	<div id="boxedcontent">
		
		<div class="templatemyaccount">
		
			<!-- TITLE BEGINS -->
			<div class="headerimage" style="background-image: url(assets/img/back_to_school.jpg);">
				<div class="headercontent">
					<div class="container">
						<div class="row">
							<div class="col-md-12">
								<div class="fleft">
									<h1>404 Not Found</h1>
								</div>
								<div class="fright breadc">
									<a href="index.jsp">Home</a> / Error
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
						<h1 class="text-center">Oops, 404 Not Found</h1>
						<p class="text-center">
							Sorry, an error has occured. Requested page not found! <br/>
							<a href="/">Click here to return MainPage.</a>
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
</body>
</html>