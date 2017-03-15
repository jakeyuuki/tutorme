<!DOCTYPE html>
<html lang="en-US">
<head>
	<jsp:include page="sharedJSP/head.jsp"/>
	<link rel="stylesheet" href="assets/css/font-awesome-animation.css" media="all" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="assets/css/enableGPU.css" media="all" rel="stylesheet" type="text/css"/>
</head>
<body>
	<!-- HEADER
	================================================== -->
	<jsp:include page="sharedJSP/header.jsp"/>
	
	<!-- PAGE CONTENT
	================================================== -->
	<div class="shoppage">
	
		<!-- TITLE BEGINS -->
		<div class="headerimage" style="background-image: url(assets/img/back_to_school.jpg);">
			<div class="headercontent">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="fleft">
								<h1>Tutors</h1>
							</div>
							<div class="fright breadc">
								<a href="index.jsp">Home</a> / Tutors
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- TITLE ENDS -->
		
		<div class="container padbot30">
			<div class="row padtop30 padbot20">
				<div class="col-md-12">
					<span class="pagesit fleft">
						Filter: by newest			
					</span>
					<div class="sortitemsarea fright">
						<form method="GET" action="#" class="wowsortitems">
							<select name="data" id="data" onchange='this.form.submit()'>
								<option value="1">Sort by newest</option>
								<!-- <option value="2">Sort by price: low to high</option>
								<option value="3">Sort by price: high to low</option>
								<option value="4">Sort by popularity</option>
								<option value="5">Sort by oldest</option>
								<option value="6">Sort by comments</option>-->
								<!-- <option value="7">Sort alphabetically</option> -->
							</select>
						   <noscript>
								<input type="submit" value="Submit">
						   </noscript>
						 </form>

					</div> 
				</div>
			</div>
			
			<div id="boxedcontent" class="row">
			<!-- MAIN CONTENT BEGINS -->
			<div class="col-md-9">
				<div id="tutorsList" class="wowitemboxlist">
				<!-----------------------List of tutors here ------------------------>
				</div>
				<div class="clear">
				</div>
				<div class="divider1">
				</div>
				<div id="loadMore" class='separatr text-center clickable'>
					<span class="faa-parent animated-hover"><i class="fa fa-angle-double-down faa-float"></i>&nbsp; Load more &nbsp; <i class="fa fa-angle-double-down faa-float"></i></span>
				</div>
				<div class="clear">
				</div>
			</div>				
			<!-- MAIN CONTENT ENDS -->
			
			<!-- SIDEBAR BEGINS -->
			<div class="col-md-3">
				<div id="secondary" class="widget-area" role="complementary">
					<aside id="text-4" class="widget widget_text">
					<h1 class="widget-title section-title"><span>Search for Tutors</span></h1>
					<div class="textwidget text-center">
						<form role="search" method="get" class="search-form" action="/tutors.jsp">
							<input type="search" class="search-field" placeholder="Type and hit enter..." value="<%=request.getParameter("name") != null ? request.getParameter("name") : ""%>" name="name" title=""/>
							<input id="edd_profile_editor_submit" type="submit" class="edd_submit" value="Search">
						</form>
					</div>
					<!-- 
					</aside><aside id="edd_categories_tags_widget-3" class="widget widget_edd_categories_tags_widget">
					<h1 class="widget-title section-title"><span>Categories</span></h1>
					<ul class="edd-taxonomy-widget">
						<li class="cat-item"><a href="#">Banners</a>
						</li>
						<li class="cat-item"><a href="#">Graphics</a>
						</li>
						<li class="cat-item"><a href="#">Logos</a>
						</li>
						<li class="cat-item"><a href="#">Photoes</a>
						</li>
						<li class="cat-item"><a href="#">Vintage</a>
						</li>
					</ul>
					</aside>
					<aside class="widget widget_edd_cart_widget">
					<h1 class="widget-title section-title"><span>My Cart</span></h1>
					<p class="edd-cart-number-of-items">
						Number of items in cart: <span class="edd-cart-quantity">2</span>
					</p>
					<ul class="edd-cart">
						<li class="edd-cart-item">
							<span class="edd-cart-item-title">Sandy Banner</span>
							<span class="edd-cart-item-separator">-</span><span class="edd-cart-item-price">&nbsp;$8.00&nbsp;</span><span class="edd-cart-item-separator">-</span>
							<a href="#" class="edd-remove-from-cart">remove</a>
						</li>
						<li class="edd-cart-item">
							<span class="edd-cart-item-title">Sarabanda</span>
							<span class="edd-cart-item-separator">-</span><span class="edd-cart-item-price">&nbsp;$89.00&nbsp;</span><span class="edd-cart-item-separator">-</span>
							<a href="#" class="edd-remove-from-cart">remove</a>
						</li>
						<li class="cart_item edd_subtotal">Subtotal: <span class='subtotal'>$97.00</span></li>
						<li class="cart_item edd_checkout"><a href="checkout.html">Checkout</a></li>
					</ul>
					-->
					</aside>
				</div>
			</div>
			<!-- SIDEBAR ENDS -->
				
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
	
	<!-- FUNCTIONS
	================================================== -->	
	<script type='text/javascript'>
		startIndex = 0;
		orderBy = "rate";
		numberOfItemPerLoad = 6;
		result = [];
		that = this;
		$.post('rest/tutors', 
			{ start: startIndex, count: 6, orderBy: orderBy, name: "<%=request.getParameter("name")%>" }, 
			function(data) {
				result = data;
				loadTutors();
			}
		);
		
		$('#loadMore').click(function() {
			loadTutors();
		});
		
		// Fuctions:
		loadTutors = function() {
			imgSource = "http://www.themepush.com/demo-kailo/wp-content/uploads/sites/6/edd/2015/03/163_1.jpg";
			summary = "Sed ac scelerisque ligula, ac aliquam ligula. Vivamus ut pellentesque nisi, vel com.";
			price = "80.00";
			rate = 4;
			
			$.each(result.slice(startIndex, startIndex + numberOfItemPerLoad), function(i, item) {
				url = "/tutor?id=".concat(item['tr_pk']);
				var reader = new window.FileReader();
				var img = item['tr_pic'] != null ? item['tr_pic'] : imgSource;
				$('#tutorsList').append(addGrid(img, url, item['name'], item['summary'].replace(/<(?:.|\n)*?>/gm, ''), item['cost'], item['rate']));
				startIndex++;
			});
		}
	</script>
	<!-- SCRIPTS Actions
	================================================== -->	
	<script type='text/javascript'>
		loadTutors();		
	</script>
	<!-- /script -->
</body>
</html>