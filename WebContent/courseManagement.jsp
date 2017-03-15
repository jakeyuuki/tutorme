<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<jsp:include page="sharedJSP/head.jsp" />
</head>

<body>
	<!-- HEADER
	================================================== -->
	<jsp:include page="sharedJSP/header.jsp" />

	<!-- PAGE CONTENT
	================================================== -->
	<div id="boxedcontent">

		<div class="templatemyaccount">

			<!-- TITLE BEGINS -->
			<div class="headerimage"
				style="background-image: url(assets/img/back_to_school.jpg);">
				<div class="headercontent"></div>
			</div>
			<!-- TITLE ENDS -->
			<!-- http://jsfiddle.net/LHZXw/1/ validation before submission
			 -->
			<div class="container padtop40 padbot50">
				<div class="row">
					<!-- MAIN CONTENT BEGINS -->
					<div class="col-md-9">
						<div class="wowitembox col-md-3 notesarea">
							<a href="javaScript:void(0);">
							<div class="wowitemboxinner" id="addCourse">
								<br>
								<div class="imagearea">
									<div class="caption">
										<div class="blur">
										</div>
										
									</div>
								</div>
								<div class="notesarea cmanagementbox">
									<br>
									<div class="cmanagementplus">+</div>
									<br>
									<span class="cmanagementtext">Add a course</span>
								</div>
							</div>
							<!-- .wowitemboxinner-->
							</a>
						</div>
						<div id="tutorsList" class="wowitemboxlist">
							
							
							<!-----------------------List of courses here ------------------------>
							
							
							
						</div>
						<div class="clear"></div>
						<div class="divider1"></div>
						<div id="loadMore" class='separatr text-center clickable'>
							<span class="faa-parent animated-hover"><i
								class="fa fa-angle-double-down faa-float"></i>&nbsp; Load more
								&nbsp; <i class="fa fa-angle-double-down faa-float"></i></span>
						</div>
						<div class="clear"></div>
					</div>


					<!-- MAIN CONTENT ENDS -->

				</div>
			</div>
		</div>
	</div>

	<!-- FOOTER
	================================================== -->
	<jsp:include page="sharedJSP/footer.jsp" />
	<!-- /footer -->

	<!-- SCRIPTS
	================================================== -->
	<jsp:include page="sharedJSP/javascript.jsp" />
	<!-- /script -->
	<script>
		
	</script>

	<script>
	startIndex = 0;
	orderBy = "rate";
	numberOfItemPerLoad = 6;
	result = [];
	that = this;
	$.getJSON('rest/courses', 
		{ start: startIndex, count: 6, orderBy: orderBy }, 
		function(data) {
			result = data;
			loadTutors();
		}
	);
	
	$('#loadMore').click(function() {
		loadTutors();
	});
	
	$('#addCourse').click(function() {
		loadTutors();
	});
	// Fuctions:
	loadTutors = function() {
		imgSource = "http://www.themepush.com/demo-kailo/wp-content/uploads/sites/6/edd/2015/03/163_1.jpg";
		summary = "Sed ac scelerisque ligula, ac aliquam ligula. Vivamus ut pellentesque nisi, vel com.";
		price = "80.00";
		rate = 4;
		
		$.each(result.slice(startIndex, startIndex + numberOfItemPerLoad), function(i, item) {
			url = "/course?id=".concat(item['ce_pk']);
			var reader = new window.FileReader();
			$('#tutorsList').append(addGrid(imgSource, url, item['ce_name'], item['summary'].replace(/<(?:.|\n)*?>/gm, ''),  item['ce_cost'], item['rate']));
			startIndex++;
		});
	}
	
	function bin2String(array) {
		var result = "";
		for (var i = 0; i < array.length; i++) {
			result += String.fromCharCode(parseInt(array[i], 2));
		}
		return result;
	}
	</script>
</body>
</html>