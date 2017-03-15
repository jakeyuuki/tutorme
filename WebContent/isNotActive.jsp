<!DOCTYPE html>
<html lang="en-US">
<head>
<jsp:include page="sharedJSP/head.jsp" />
<link rel="stylesheet" href="assets/css/font-awesome-animation.css"
	media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="assets/css/enableGPU.css" media="all"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<!-- HEADER
	================================================== -->
	<jsp:include page="sharedJSP/header.jsp" />

	<!-- PAGE CONTENT
	================================================== -->
	<div class="shoppage">

		<!-- TITLE BEGINS -->
		<div class="headerimage"
			style="background-image: url(assets/img/back_to_school.jpg);">
			<div class="headercontent">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="fleft">
								<h1>Courses</h1>
							</div>
							<div class="fright breadc">
								<a href="index.jsp">Home</a> / Courses
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- TITLE ENDS -->
		<div class="container padtop40 padbot50">
			<div class="row">
				<!-- MAIN CONTENT BEGINS -->
				<div class="col-md-12">
					<div class="wrapcontent">
						<h3 class="text-center">You have been blocked from being able
							to use TutorMe's services.</h3>
							<p class="text-center">
								To know the reason you've been blocked, you must e-mail the
								admin at tutorme@darrenleohartbui.com with the <b>e-mail subject</b><br>
								starting with <b>"[TUTORME REQUEST]"</b>.
							</p>
					</div>
				</div>
				<!-- MAIN CONTENT ENDS -->

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

	<!-- FUNCTIONS
	================================================== -->
	<script type='text/javascript'>
		startIndex = 0;
		orderBy = "rate";
		numberOfItemPerLoad = 6;
		result = [];
		that = this;
		$.getJSON('rest/courses', {
			start : startIndex,
			count : 6,
			orderBy : orderBy
		}, function(data) {
			result = data;
			loadTutors();
		});

		$('#loadMore').click(function() {
			loadTutors();
		});

		// Fuctions:
		loadTutors = function() {
			imgSource = "http://www.themepush.com/demo-kailo/wp-content/uploads/sites/6/edd/2015/03/163_1.jpg";
			summary = "Sed ac scelerisque ligula, ac aliquam ligula. Vivamus ut pellentesque nisi, vel com.";
			price = "80.00";
			rate = 4;

			$.each(result.slice(startIndex, startIndex + numberOfItemPerLoad),
					function(i, item) {
						url = "/course?id=".concat(item['ce_pk']);
						var reader = new window.FileReader();
						$('#tutorsList').append(
								addGrid(imgSource, url, item['ce_name'],
										item['summary'].replace(
												/<(?:.|\n)*?>/gm, ''),
										item['ce_cost'], item['rate']));
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