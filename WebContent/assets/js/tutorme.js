$( document ).ready(function() {
	// Functionality

});

if(location.pathname=="/" || location.pathname=="/index.jsp") {
	$(".homeimgbtn").click(function() {
		$.scrollTo($("#homepagetemplate"), 800, {offset: function() { return {top:-50}; }});
	});
	$("img.clickable").click(function() {
		$.scrollTo(0, 800);
	});
}
else {
	$(".homeimgbtn").click(function() {
		window.location.href='/'
	});
	$("img.clickable").click(function() {
		window.location.href='/'
	});
}

addGrid = function(imgSource, url, title, summary, price, rate) {
	var rateStar = rate/5*100 + "%";
	if(price != 0) 	price =  "$".concat(price,"/hour");
	else			price = "";
	
	string =  	"<div class='wowitembox col-md-3'>";
	string +=		"<div class='wowitemboxinner'>";
	string += 			"<div class='imagearea' style='height:175px'>";
	//string +=				"<img width='555' height='370' src='".concat(imgSource, "' class='attachment-post-thumbnail wp-post-image' alt='163_1' />");
	string +=				"<img class='attachment-post-thumbnail wp-post-image' ".concat("src='", imgSource, "' />"); //style='width:100%; height:175px;'
	string +=				"<div class='caption'>";
	string +=					"<div class='blur'></div>";
	string +=					"<div class='caption-text'>";
	string +=						"<div class='captionbuttons'>";
	string +=							"<a href='".concat(url, "' class='", summary,"'><i class='fa fa-link'></i> View Details</a>");
	string +=						"</div>";
	string +=					"</div>";
	string +=				"</div>";
	string +=			"</div>";
	string +=			"<div class='notesarea'>";
	string +=				"<h2><a href='".concat(url,"' title='", title,"'>", title, "</a></h2>");
	string +=				"<div class='description'>";
	string +=					"<p>";
	string +=						summary;
	string +=					"</p>";
	string +=				"</div>";
	string +=				"<div class='notesbottom'>";
	string +=					"<div class='price fleft'>".concat(price,"</div>");
	string +=					"<div class='cart fright'>";
	string +=						"<div class='rating-container rating-fa rating-xxs' data-content='&#xf164;&#xf164;&#xf164;&#xf164;&#xf164;' >";
	string +=							"<div class='rating-stars' data-content='&#xf164;&#xf164;&#xf164;&#xf164;&#xf164;' style='width: ".concat(rateStar, ";'></div>");
	string +=						"</div>";
	string +=					"</div>";
	string +=					"<div class='clearfix'></div>";
	string +=				"</div>";
	string +=			"</div>";
	string +=		"</div>";
	string +=		"<!-- .wowitemboxinner-->";
	string +=	"</div>";
	string +=	"<!-- .wowitembox-->";
	
	return string;
}