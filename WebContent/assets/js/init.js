// =============================================
// BEGIN THEME SCRIPTS
// =============================================
jQuery(document).ready(function($){

	$('ul.nav li.dropdown, ul.nav li.dropdown-submenu').hover(function() {
		$(this).find(' > .dropdown-menu').stop(true, true).delay(100).fadeIn();
	}, function() {
		$(this).find(' > .dropdown-menu').stop(true, true).delay(100).fadeOut();
	});

	/*====================================
	SIDEMENU 
	======================================*/	
	$(function(){
	  $('#sidemenu a').on('click', function(e){
		e.preventDefault();

		if($(this).hasClass('open')) {
		  // do nothing because the link is already open
		} else {
		  var oldcontent = $('#sidemenu a.open').attr('href');
		  var newcontent = $(this).attr('href');
		  
		  $(oldcontent).fadeOut('fast', function(){
			$(newcontent).fadeIn().removeClass('hidden');
			$(oldcontent).addClass('hidden');
		  });
		  
		 
		  $('#sidemenu a').removeClass('open');
		  $(this).addClass('open');
		}
	  });
	});



    /*====================================
    Fixed Header
    ======================================*/
	var isOn = false;
	var navHeight = 50;
    $(window).bind('scroll', function() {
        if ($(window).scrollTop() > navHeight) {
        	if(!isOn) {
	            $('.navbar-fixed-top').addClass('on');
	            isOn = true;
        	}
        } else if(isOn) {
            $('.navbar-fixed-top').removeClass('on');
            isOn = false;
        }
    });


    /*====================================
    Masonry
    ======================================*/
//	$blocks = $(".wowitemboxlist,.edd_downloads_list");
//	$blocks.imagesLoaded(function(){
//		$blocks.masonry({
//			itemSelector: '.wowitembox,.edd_download'
//		});
//
//		// Fade blocks in after images are ready (prevents jumping and re-rendering)
//		$(".wowitembox,.edd_download").fadeIn();
//	});	
//	$(document).ready( function() { setTimeout( function() { $blocks.masonry(); }, 500); });
//	$(window).resize(function () {
//		$blocks.masonry();
//	});
	
	
// =============================================
// END THEME SCRIPTS
// =============================================
});