<!DOCTYPE html>
<html lang="en-US">
<% response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
 response.setHeader("Pragma","no-cache"); //HTTP 1.0 
 response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
%>
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
								<h1>Tutor Profile</h1>
							</div>
							<div class="fright breadc">
								<a href="index.jsp">Home</a> / Tutor Management
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- TITLE ENDS -->
		
		<div class="wrapcontent container padtop40 padbot50">
		<div class="row">
		<div class="text-center"><span class="smalltitle">TUTOR DETAILS</span></div>
				<% if(request.getAttribute("applicationSuccess") != null) { %>
					<div class="alert alert-success"> ${applicationSuccess}</div> 
				<% } %>
				
				<form id="tutor_application_form" class="edd_form form-horizontal" action="/tutorProfile" method="post" enctype="multipart/form-data">
					<fieldset>	
			            <!-------------------------------------	Profile Description ------------------------------------->
			            <div class="panel panel-default">
			            	<div class="panel-heading"> 
			            		<h4 class="panel-title">
			                  		<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#details">
			                   			 Profile Description <span class="glyphicon chevron-up"></span>
			                    		<i class="fa fa-lg fa-caret-square-o-down pull-right"></i>
			                 		</a>
		                		</h4>
			              	</div>
			              	<div id="details" class="panel-collapse collapse in">
			                	<div class="panel-body">
			                		<div class="form-group">
				                		<div class='col-md-12'>
					                		<label for="tutorpic">Upload tutor picture</label>
	  										<input onchange="readURL(this)" accept="image/*" type="file" id="tutorpic" name="tutorpic"/>
	  										<img id="img_preview" src="${pic}" alt="Your profile picture" />
										</div>
										<div class='col-md-12'>
		                     				<label for="summary">Tutor Summary</label>
		                     				<textarea id="summary" name="summary" rows="2" cols="1">${summary}</textarea>
		                     			</div>
		                     			<div class='col-md-12'>
		                     				<label for="description">Description</label>
											<textarea class="form-control" id="editor" name="editor" style="height: 400px;">${description}</textarea>
										</div>
									</div>
						    	</div>
						    </div>	
      				</div>			            
						
						<p id="application_submit_wrap">
							<input id="application_submit" type="submit" class="edd_submit" name="action" value="Save"/>
						</p>
												
					</fieldset>
				</form>

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
	

<script>
	
$(document).ready(function() {
	
	$('#img_preview')
    .width(328)
    .height(219);
	
	setTimeout(function(){ $("#img_preview").attr("src", $("#img_preview").attr("src")+"?date="+new Date().getTime()); }, 2000);
	
	$('#tutor_application_form')
        .formValidation({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
            	summary: {
                	validators: {
                		notEmpty: {
                            message: 'Please enter a short description of your skills'
                        },
                        stringLength: {
                            min: 30,
                            max:200,
                            message: 'Please enter 30 - 200 characters'
                        }
                    }
                },

                tutorpic:{
                	validators: {
                		file: {
                			maxSize: 5000000,
                			message: 'File too large. Please ensure file does not exceed the 5mb limit'
                		},
                
                	}
                }
            } 
        });
	});
	
	var _URL = window.URL || window.webkitURL;
	$("#img_preview").change(function (e) {
	    var file, img;
	    if ((file = this.files[0])) {
	        img = new Image();
	        img.onload = function () {
	            alert(555 + " " + 370);
	        };
	        img.src = _URL.createObjectURL(file);
	    }
	});
	
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	
	        reader.onload = function (e) {
	            $('#img_preview')
	                .attr('src', e.target.result)
	                .width(328)
	                .height(219);
	        };
	
	        reader.readAsDataURL(input.files[0]);
	    }
	};
	</script>
	<script src="/ckeditor/ckeditor.js"></script>
	<script src="/ckeditor/config.js"></script>
	<!-- /script -->
</body>
</html>