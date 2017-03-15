<!DOCTYPE html>
<html lang="en-US">
<head>
	<jsp:include page="sharedJSP/head.jsp"/>
	<link rel="stylesheet" href="assets/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="boxedlayout">

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
					<a class="navbar-brand" href='index.html'>
					<img alt="Kailo" src="assets/img/logo.png"/>
					</a>
				</div>
				<!-- Menu -->
				<div id="bs-example-navbar-collapse-1" class="collapse navbar-collapse">
					<ul id="menu-top" class="nav navbar-nav navbar-right">
						<li class="active"><a href="index.html">Home</a></li>
						<li><a href="shop.html">Shop</a></li>
						<li><a href="myaccount.html">My Account</a></li>
						<li><a href="checkout.html">Checkout</a></li>
						<li><a href="blog.html">Blog</a></li>
						<li class="dropdown"><a href="#">Pages</a>
							<ul class="dropdown-menu">
								<li><a href="productsingle.html">Product Single</a></li>
								<li><a href="productsingle-nosidebar.html">Product Single No Sidebar</a></li>								
								<li><a href="shop-nosidebar.html">Shop No Sidebar</a></li>
								<li><a href="blogsingle.html">Blog Single</a></li>								
								<li><a href="useraccount.html">User Account</a></li>
								<li><a href="purchaseconfirmation.html">Purchase Confirmation</a></li>
								<li><a href="contact.html">Contact</a></li>
								<li><a href="columns.html">Columns</a></li>
								<li><a href="elements.html">Elements</a></li>
								<li><a href="icons.html">Icons</a></li>
								<li><a href="starter.html">Starter</a></li>
							</ul>
						</li>
						<li><a href="checkout.html"><i class="fa fa-shopping-cart"></i> Cart (3)</a></li>
					</ul>
				</div>
			</div>
		</nav>
		
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
									<h1>Shop</h1>
								</div>
								<div class="fright breadc">
									<a href="index.jsp">Home</a> / Shop
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
							Showing page 1 of 2			
						</span>
						<div class="sortitemsarea fright">    
							<form method="GET" action="#" class="wowsortitems">    
								<select name="data" id="data" onchange='this.form.submit()'>
									<option value="1">Sort by newest</option>
									<option value="2">Sort by price: low to high</option>
									<option value="3">Sort by price: high to low</option>
									<option value="4">Sort by popularity</option>
									<option value="5">Sort by oldest</option>
									<option value="6">Sort by comments</option>
									<option value="7">Sort alphabetically</option>
								</select>
							   <noscript>
									<input type="submit" value="Submit">
							   </noscript>
							 </form>
						</div> 
					</div>
				</div>
				
				<div class="row">
				<!-- MAIN CONTENT BEGINS -->
				<div class="col-md-12">
					<div class="wowitemboxlist">
						<div class="wowitembox col-md-3">
							<div class="wowitemboxinner">
								<div class="imagearea">
									<img width="555" height="370" src="http://www.themepush.com/demo-kailo/wp-content/uploads/sites/6/edd/2015/03/163_1.jpg" class="attachment-post-thumbnail wp-post-image" alt="163_1"/>
									<div class="caption">
										<div class="blur">
										</div>
										<div class="caption-text">
											<div class="captionbuttons">
												<a href="productsingle.html" class="captiondetails"><i class="fa fa-link"></i> View Details</a>
											</div>
										</div>
									</div>
								</div>
								<div class="notesarea">
									<h2><a href="productsingle.html" title="Travel Fashion">
									Travel Fashion
									</a></h2>
									<div class="description">
									<p>
										 Sed ac scelerisque ligula, ac aliquam ligula. Vivamus ut pellentesque nisi, vel com.
									</p>
									</div>
									<div class="notesbottom">
										<div class="price fleft">
											$19.00 
										</div>
										<div class="cart fright">
											<a href="#" class="edd-add-to-cart">
												<span class="edd-add-to-cart-label">Add to Cart</span>
											</a>
										</div>
										<div class="clearfix">
										</div>
									</div>
								</div>
							</div>
							<!-- .wowitemboxinner-->
						</div>
						<!-- .wowitembox-->
						<div class="wowitembox col-md-3">
							<div class="wowitemboxinner">
								<div class="imagearea">
									<img width="555" height="370" src="http://www.themepush.com/demo-kailo/wp-content/uploads/sites/6/edd/2015/03/166_1.jpg" class="attachment-post-thumbnail wp-post-image" alt="166_1"/>
									<div class="caption">
										<div class="blur">
										</div>
										<div class="caption-text">
											<div class="captionbuttons">
												<a href="productsingle.html" class="captiondetails"><i class="fa fa-link"></i> View Details</a>
											</div>
										</div>
									</div>
								</div>
								<div class="notesarea">
									<h2><a href="productsingle.html" title="Winter Feel">
									Winter Feel
									</a></h2>
									<div class="description">
									<p>
										 Sed ac scelerisque ligula, ac aliquam ligula. Vivamus ut pellentesque nisi, vel com.
									</p>
									</div>
									<div class="notesbottom">
										<div class="price fleft">
											$32.00 
										</div>
										<div class="cart fright">
											<a href="#" class="edd-add-to-cart">
												<span class="edd-add-to-cart-label">Add to Cart</span>
											</a>
										</div>
										<div class="clearfix">
										</div>
									</div>
								</div>
							</div>
							<!-- .wowitemboxinner-->
						</div>
						<!-- .wowitembox-->
						<div class="wowitembox col-md-3">
							<div class="wowitemboxinner">
								<div class="imagearea">
									<img width="555" height="370" src="http://www.themepush.com/demo-kailo/wp-content/uploads/sites/6/edd/2015/03/172_1.jpg" class="attachment-post-thumbnail wp-post-image" alt="172_1"/>
									<div class="caption">
										<div class="blur">
										</div>
										<div class="caption-text">
											<div class="captionbuttons">
												<a href="productsingle.html" class="captiondetails"><i class="fa fa-link"></i> View Details</a>
											</div>
										</div>
									</div>
								</div>
								<div class="notesarea">
									<h2><a href="productsingle.html" title="Eyes Wide Shut">
									Eyes Wide Shut
									</a></h2>
									<div class="description">
									<p>
										 Sed ac scelerisque ligula, ac aliquam ligula. Vivamus ut pellentesque nisi, vel com.
									</p>
									</div>
									<div class="notesbottom">
										<div class="price fleft">
											$17.00 
										</div>
										<div class="cart fright">
											<a href="#" class="edd-add-to-cart">
												<span class="edd-add-to-cart-label">Add to Cart</span>
											</a>
										</div>
										<div class="clearfix">
										</div>
									</div>
								</div>
							</div>
							<!-- .wowitemboxinner-->
						</div>
						<!-- .wowitembox-->
						<div class="wowitembox col-md-3">
							<div class="wowitemboxinner">
								<div class="imagearea">
									<img width="555" height="370" src="http://www.themepush.com/demo-kailo/wp-content/uploads/sites/6/edd/2015/03/168_1.jpg" class="attachment-post-thumbnail wp-post-image" alt="168_1"/>
									<div class="caption">
										<div class="blur">
										</div>
										<div class="caption-text">
											<div class="captionbuttons">
												<a href="productsingle.html" class="captiondetails"><i class="fa fa-link"></i> View Details</a>
											</div>
										</div>
									</div>
								</div>
								<div class="notesarea">
									<h2><a href="productsingle.html" title="Bananas">
									Bananas
									</a></h2>
									<div class="description">
									<p>
										 Sed ac scelerisque ligula, ac aliquam ligula. Vivamus ut pellentesque nisi, vel com.
									</p>
									</div>
									<div class="notesbottom">
										<div class="price fleft">
											$6.50 
										</div>
										<div class="cart fright">
											<a href="#" class="edd-add-to-cart">
												<span class="edd-add-to-cart-label">Add to Cart</span>
											</a>
										</div>
										<div class="clearfix">
										</div>
									</div>
								</div>
							</div>
							<!-- .wowitemboxinner-->
						</div>
						<!-- .wowitembox-->
						<div class="wowitembox col-md-3">
							<div class="wowitemboxinner">
								<div class="imagearea">
									<img width="555" height="370" src="http://www.themepush.com/demo-kailo/wp-content/uploads/sites/6/edd/2015/03/169_1.jpg" class="attachment-post-thumbnail wp-post-image" alt="169_1"/>
									<div class="caption">
										<div class="blur">
										</div>
										<div class="caption-text">
											<div class="captionbuttons">
												<a href="productsingle.html" class="captiondetails"><i class="fa fa-link"></i> View Details</a>
											</div>
										</div>
									</div>
								</div>
								<div class="notesarea">
									<h2><a href="productsingle.html" title="Sarabanda">
									Sarabanda
									</a></h2>
									<div class="description">
									<p>
										 Sed ac scelerisque ligula, ac aliquam ligula. Vivamus ut pellentesque nisi, vel com.
									</p>
									</div>
									<div class="notesbottom">
										<div class="price fleft">
											$89.00 
										</div>
										<div class="cart fright">
											<a href="#" class="edd-add-to-cart">
												<span class="edd-add-to-cart-label">Add to Cart</span>
											</a>
										</div>
										<div class="clearfix">
										</div>
									</div>
								</div>
							</div>
							<!-- .wowitemboxinner-->
						</div>
						<!-- .wowitembox-->
						<div class="wowitembox col-md-3">
							<div class="wowitemboxinner">
								<div class="imagearea">
									<img width="555" height="370" src="http://www.themepush.com/demo-kailo/wp-content/uploads/sites/6/edd/2015/03/181_1.jpg" class="attachment-post-thumbnail wp-post-image" alt="181_1"/>
									<div class="caption">
										<div class="blur">
										</div>
										<div class="caption-text">
											<div class="captionbuttons">
												<a href="productsingle.html" class="captiondetails"><i class="fa fa-link"></i> View Details</a>
											</div>
										</div>
									</div>
								</div>
								<div class="notesarea">
									<h2><a href="productsingle.html" title="Old Game">
									Old Game
									</a></h2>
									<div class="description">
									<p>
										 Sed ac scelerisque ligula, ac aliquam ligula. Vivamus ut pellentesque nisi, vel com.
									</p>
									</div>
									<div class="notesbottom">
										<div class="price fleft">
											$33.00 
										</div>
										<div class="cart fright">
											<a href="#" class="edd-add-to-cart">
												<span class="edd-add-to-cart-label">Add to Cart</span>
											</a>
										</div>
										<div class="clearfix">
										</div>
									</div>
								</div>
							</div>
							<!-- .wowitemboxinner-->
						</div>
						<!-- .wowitembox-->
					</div>
					<div class="clear">
					</div>
					<div class='pagination'>
						<span class='current'>1</span><a href='#' class='inactive'>2</a>
					</div>
					<div class="clear">
					</div>
				</div>				
				<!-- MAIN CONTENT ENDS -->
					
				</div>				
			</div>
		</div>		
		
		<!-- CALL TO ACTION
		================================================== -->
		<div class="actionbeforefooter text-center">
			<div class="container">
				<span><i class="fa fa-star fa-spin"></i> Psst! Like what you see? Enter code <b>WOWKAILO10</b> and get 10% discount for Kailo template. This week only! </span>
				<a target="_blank" class="actionbutton" href="#">Buy Now </a>
			</div>
		</div>
		
		<!-- FOOTER
		================================================== -->		
		<footer class="themefooter section">
		<div class="container">
			<div class="section-inner row">
				<!-- /footer-a -->
				<div class="column column-1 col-md-4 rightbd">
					<div class="widgets">
						<div class="widget widget_text">
							<div class="widget-content">
								<h3 class="widget-title">About  "Kailo""</h3>
								<div class="textwidget">
									You are previewing "Kailo", a premium HTML bootstrap template that will help you design your website for selling videos, ebooks, photos, audio, software, podcasts or any other type of file. The Wordpress version provides a fully functional shop ready.
								</div>
							</div>
							<div class="clear">
							</div>
						</div>
					</div>
				</div>
				<!-- /footer-b -->
				<div class="column column-1 col-md-4 rightbd">
					<div class="widgets">
						<div class="widget widget_wysija">
							<div class="widget-content">
								<h3 class="widget-title">Subscribe to our Newsletter</h3>
								<div class="widget_wysija_cont">
									<form class="widget_wysija">
										 Subscribe to our newsletter and join our 1374 subscribers.
										<p class="wysija-paragraph">
											<label>Email <span class="wysija-required">*</span></label>
											<input type="text" name="" class="wysija-input validate[required,custom[email]]" title="Email" value=""/>
										</p>
										<input class="wysija-submit wysija-submit-field" type="submit" value="Subscribe!"/>
									</form>
								</div>
							</div>
							<div class="clear">
							</div>
						</div>
					</div>
				</div>
				<!-- /footer-c -->				
				<div class="column column-1 col-md-4">
					<div class="widgets">
						<div class="widget widget_edd_categories_tags_widget">
							<div class="widget-content">
								<h3 class="widget-title">Shop Categories</h3>
								<ul class="edd-taxonomy-widget">
									<li><a href="#">Banners</a></li>
									<li><a href="#">Graphics</a></li>
									<li><a href="#">Logos</a></li>
									<li><a href="#">Photoes</a></li>
									<li><a href="#">Vintage</a></li>
								</ul>
							</div>
							<div class="clear">
							</div>
						</div>
					</div>
				</div>
				<div class="clear">
				</div>
			</div>
		</div>
		<!-- /copyright -->
		<div class="sectioncredits">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<span class="credits-left fleft">
						2015 &copy; All Rights Reserved, WowThemes.net </span>
						<ul class="footermenu fright">
							<li><a href="index.html">Home</a></li>
							<li><a href="shop.html">Shop</a></li>
							<li><a href="blog.html">Blog</a></li>
							<li><a href="myaccount.html">My Account</a></li>
						</ul>
					</div>
					<div class="clear">
					</div>
				</div>
			</div>
		</div>
		</footer>
		
		<!-- SCRIPTS
		================================================== -->			
		<script type='text/javascript' src='assets/js/jquery.js'></script>
		<script type='text/javascript' src='assets/js/bootstrap.min.js'></script>
		<script type='text/javascript' src='assets/js/masonry.js'></script>
		<script type='text/javascript' src='assets/js/imagesloaded.js'></script>
		<script type='text/javascript' src='assets/js/SmoothScroll.js'></script>
		<script type='text/javascript' src='assets/js/init.js'></script>
		<script type='text/javascript' src='assets/js/anim.js'></script>
	</div>
</body>
</html>