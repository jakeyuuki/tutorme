<!DOCTYPE html>
<html lang="en-US">
<head>
<jsp:include page="/sharedJSP/head.jsp" />
</head>
<body>
	<!-- HEADER
	=================================================== -->
	<jsp:include page="/sharedJSP/header.jsp" />

	<!-- PAGE CONTENT
	================================================== -->
	<div id="boxedcontent">

		<!-- TITLE BEGINS -->
		<div class="headerimage"
			style="background-image: url(/assets/img/back_to_school.jpg);">
			<div class="headercontent">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="fleft">
								<h1>${fullName}</h1>
							</div>
							<div class="fright breadc">
								<a href="/"> Home </a> / Tutor
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- TITLE ENDS -->
		<div class="container padtop50 padbot50">
			<div class="row">
				<!-- MAIN CONTENT BEGINS -->
				<div class="col-md-12">
					<div id="primary" class="content-area">
						<div id="main" class="site-main" role="main">
							<article class="contentdownloadphp">
								<div class="row">
									<div class="col-md-3">
										<img class="tutorcountry" src="${tutorpic}" /> <span
											class="tutorname"><b>Name: </b>${tutorname}<br /></span> <span
											class="tutorname"><b>Contact Email: </b>${tutoremail}<br /></span>
										<span class="tutorcountry"><b>Country: </b>${tutorcountry}<br /></span>
									</div>
									<div class="container col-md-9">${tutordesc}</div>
								</div>

							</article>
						</div>
					</div>
				</div>
				<!-- MAIN CONTENT ENDS -->

			</div>
		</div>
	</div>

	<!-- FOOTER
	================================================== -->
	<jsp:include page="/sharedJSP/footer.jsp" />
	<!-- /footer -->

	<!-- SCRIPTS
	================================================== -->
	<jsp:include page="/sharedJSP/javascript.jsp" />
</body>
</html>