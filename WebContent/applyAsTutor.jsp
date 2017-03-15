<!DOCTYPE html>
<html lang="en-US">

<% if (Boolean.TRUE.equals(session.getAttribute("applicationSent"))) { %>
<jsp:forward page = "applicationSent.jsp" />
<% } %>

<head>
	<jsp:include page="sharedJSP/head.jsp"/>
	<!-- <link rel="stylesheet" href="ckeditor/samples/toolbarconfigurator/lib/codemirror/neo.css"> -->
	
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
									<h1>Got something to share, ${firstname}?</h1>
							</div>
							<div class="fright breadc">
								<a href="index.html">Home</a> / User Account
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- TITLE ENDS -->
		
		<div class="container padtop40 padbot50">
		<div class="row">
		<div class="text-center"><span class="smalltitle">TUTOR DETAILS</span></div>
								
				<% if(request.getAttribute("applicationSuccess") != null) { %>
					<div class="alert alert-success"> ${applicationSuccess}</div> 
				<% } %>
				<% if(request.getAttribute("certnameRegexError") != null) { %>
					<div class="alert alert-error"> ${certnameRegexError}</div> 
				<% } %>
				<% if(request.getAttribute("certdesRegexError") != null) { %>
					<div class="alert alert-error"> ${certdesRegexError}</div> 
				<% } %>
								
				<form id="tutor_application_form" class="edd_form form-horizontal" action="/tutorApplication" method="post" enctype="multipart/form-data">
					<fieldset>
					
					<div class="panel panel-default">
			            	<div class="panel-heading"> 
			            		<h4 class="panel-title">
			                  		<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#tutorInfo">
			                   			 Information <span class="glyphicon chevron-up"></span>
			                    		<i class="fa fa-lg fa-caret-square-o-down pull-right"></i>
			                 		</a>
		                		</h4>
			              	</div>
			              	<div id="tutorInfo" class="panel-collapse collapse in">
			                	<div class="panel-body">
			                		<div class='col-md-6 nopadding form-group'>
				                		<label for="edd_first_name">First Name</label>
										<input id="edd_first_name" class="required form-control edd-input" type="text" name ="firstname" value="${firstname}"/>
									</div> 
			                		<div class='col-md-6 nopadding form-group'>
				                		<label for="edd_last_name">Last Name</label>
										<input id="edd_last_name" class="required form-control edd-input" type="text" name = "lastname" value="${lastname}"/>
									</div>
			                		<div class='col-md-12 nopadding form-group'>
										<label for="edd_email">Email Address</label>
										<input readonly id="edd_email" class="edd-input" type="email" name = "email" value="${email}"/>
									</div>	
									<div class='col-md-12'>
				                		<div class="form-group">
				                			<label for="edd_first_name">Street Address</label>
											<input name="address" id="edd_address" class="form-control edd-input" type="text" value="${address}"/>
										</div>
									</div>
									
									<div class='col-md-6'>
										<div class="form-group">
			                      			<label for="edd_last_name">Suburb</label>
											<input name="suburb" id="edd_suburb" class="form-control edd-input" type="text" value="${suburb}"/>
										</div>
									</div>
									<br/>	
			                   		
			                   		<div class='col-md-6'>
			                   			<div class="form-group">
			                      			<label for="edd_last_name">State</label>
											<input name="state" id="edd_state" class="form-control edd-input" type="text" value="${state}"/>
			                     		</div>
			                     	</div>
			                     	
			                     	<div class='col-md-6'>
			                     		<div class="form-group">
			                      			<label for="edd_last_name">Postcode</label>
											<input name="postcode" id="edd_postcode" class="form-control edd-input" type="text" value="${postcode}"/>
			                   			</div>
			                   		</div>
			                   		
			                     	<div class='col-md-12'>
			                     		<div class="form-group">
			                      			<label for="edd_last_name">Country</label>
											<input name="country" id="edd_country" class="form-control edd-input" type="text" value="${country}"/>
				                		</div>
				                		
				                		
				                	</div>								
								</div>
			              	</div>
			              	</div>		
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
			                		<div class='col-md-12 nopadding form-group'>
			                		
			                					                		
			                     		<div class="form-group">
					                		<div class='col-md-12'>
						                		<label for="tutorpic">Upload tutor picture</label>
		  										<input  onchange="readURL(this)" accept="image/*" type="file" id="tutorpic" name="tutorpic"/>
		  										<img id="img_preview"  src="/assets/img/profile_default.png" alt="Your profile picture" />
											</div>
											<div class='col-md-12'>
			                     				<label for="summary">Tutor Summary</label>
			                     				<textarea id="summary" name="summary" rows="2" cols="1"></textarea>
			                     			</div>
			                     			<div class='col-md-12'>
			                     				<label for="description">Description</label>
												<textarea class="form-control" id="editor" name="editor" style="height: 400px;"></textarea>
											</div>
										</div>   
																    		
									</div>
						    	</div>
						    </div>	
      				</div>	
			              
						<!-------------------------------------	Certification info ------------------------------------->
						<div class="panel panel-default">
			            	<div class="panel-heading"> 
			            		<h4 class="panel-title">
			                  		<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#certInfo">
			                   			 Certification <span class="glyphicon chevron-up"></span>
			                    		<i class="fa fa-lg fa-caret-square-o-down pull-right"></i>
			                 		</a>
		                		</h4>
			              	</div>
			              	<div id="certInfo" class="panel-collapse collapse in">
			                	<div class="panel-body">
			                		<div class='col-md-12 nopadding form-group'>
				                		<label for="cert_name">Certification Name</label>
										<input id="cert_name" class="required form-control edd-input" type="text" name ="certname"/>
									</div>
			                		<div class='col-md-12 nopadding form-group'>
				                		<label for="cert_description">Please enter a description of this certification, skills you learned while completing it, where you obtained it, etc</label>
										<textarea class="required form-control edd-input" id="cert_description" rows=5 name="certdescription"/></textarea>
									</div>
									<div class='col-md-12 nopadding-left form-group'>
				                		<label for="cert_scan">Upload scan of certification in PDF or PNG</label>
										<input id="cert_scan" class="required form-control edd-input" type="file" accept="application/pdf, image/*" name="certscan"/>
								</div>
									
								
															
								
			                		<div class='col-md-12 nopadding form-group'>
							            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#termsModal">Terms and conditions</button>
							            <input type="hidden" name="agree" value="no" />
						    		</div>
						    	</div>
						    </div>	
      				</div>			            
						
						<p id="application_submit_wrap">
							<input id="application_submit" type="submit" class="edd_submit" name="action" value="Apply as Tutor"/>
						</p>
												
					</fieldset>
				</form>

		</div>			
		</div>
				
				<div class="modal fade" id="termsModal" tabindex="-1" role="dialog" aria-labelledby="Terms and conditions" aria-hidden="true">
				    <div class="modal-dialog">
				        <div class="modal-content">
				            <div class="modal-header">
				                <h3 class="modal-title">Terms and conditions</h3>
				            </div>
				
				            <div class="modal-body">
				                <p>Lorem ipsum dolor sit amet, veniam numquam has te. No suas nonumes recusabo mea, est ut graeci definitiones. His ne melius vituperata scriptorem, cum paulo copiosae conclusionemque at. Facer inermis ius in, ad brute nominati referrentur vis. Dicat erant sit ex. Phaedrum imperdiet scribentur vix no, ad latine similique forensibus vel.</p>
				                <p>Dolore populo vivendum vis eu, mei quaestio liberavisse ex. Electram necessitatibus ut vel, quo at probatus oportere, molestie conclusionemque pri cu. Brute augue tincidunt vim id, ne munere fierent rationibus mei. Ut pro volutpat praesent qualisque, an iisque scripta intellegebat eam.</p>
				            </div>
				
				            <div class="modal-footer">
				                <button type="button" class="btn btn-primary" id="agreeButton" data-dismiss="modal">Agree</button>
				                <button type="button" class="btn btn-default" id="disagreeButton" data-dismiss="modal">Disagree</button>
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
	

<script>
	

	
$(document).ready(function() {
	    $('#tutor_application_form')
        .formValidation({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
            	firstname: {
                    validators: {
                    	notEmpty: {
                            message: 'The first name is required'
                        },
                        regexp: {
                            message: 'First letter must be a capital and name can only contain letters or spaces',
                            regexp: /^[A-Z][A-Za-z_\-\ ]+$/
                        },
                        stringLength: {
                            max: 30,
                            message: 'Given name is too long'
                        }
                    }
                },
                lastname: {
                	validators: {
                		notEmpty: {
                            message: 'This last name is required'
                        },
                        regexp: {
                            message: 'First letter must be a capital and name can only contain letters or spaces',
                            regexp: /^[A-Z][A-Za-z_\-\ ]+$/
                        },
                        stringLength: {
                            max: 30,
                            message: 'Given name is too long'
                        }
                    }
                },
                address: {
                	validators: {
                		notEmpty: {
                            message: 'Please enter your address'
                        },
                        stringLength: {
                            max: 50,
                            message: 'Address is too long'
                        }
                    }
                },
                suburb: {
                	validators: {
                		notEmpty: {
                            message: 'Please enter your suburb'
                        },
                		regexp: {
                            message: 'Letters only',
                            regexp: /^[A-Za-z_\-\ ]*$/
                        },
                        stringLength: {
                            max: 49,
                            message: 'Suburb name too long'
                        }
                    }
                },
                state: {
                	validators: {
                		notEmpty: {
                            message: 'Please enter the three digit abbreviation of your state'
                        },
                		regexp: {
                            message: 'Letters only',
                            regexp: /^[A-Za-z_\-\ ]*$/
                        },
                        stringLength: {
                            max: 3,
                            message: 'State is too long, please enter the abbreviation of the state'
                        }
                    }
                },
                postcode: {
                	validators: {
                		notEmpty: {
                            message: 'Please enter your postcode'
                        },
                		regexp: {
                            message: 'Digits only',
                            regexp: /^[0-9]*$/
                        },
                        stringLength: {
                            max: 5,
                            message: 'Post code is invalid'
                        }
                    }
                },
                country: {
                	validators: {
                		notEmpty: {
                            message: 'Please enter your country'
                        },
                		regexp: {
                            message: 'Letters only',
                            regexp: /^[A-Za-z_\-\ ]*$/
                        },
                        stringLength: {
                            max: 49,
                            message: 'Country name too long'
                        }
                    }
                },
            	tutorteach: {
                    validators: {
                    	notEmpty: {
                            message: 'Please give us an idea of what you intend to teach, and why you\'re qualified',
                        },
                        stringLength: {
                            min: 100,
                            message: 'Please enter 100 characters minimum'
                        }
                    }
                },
                certname: {
                	validators: {
                		notEmpty: {
                            message: 'Please enter the name of your certification'
                        },
                    }
                },
                certdescription: {
                	validators: {
                		notEmpty: {
                            message: 'Please enter a description of this certification, skills you learned while completing it, where you obtained it, etc'
                        },
                        stringLength: {
                            min: 30,
                            message: 'Please enter 30 characters minimum'
                        }
                    }
                },
                certscan:{
                	validators: {
                		file: {
                			maxSize: 3000000,
                			message: 'File too large. Please ensure file does not exceed the 3mb limit'
                		},
	                notEmpty: {
	                    message: 'Please upload your certification'
	                }
                
                	}
                },
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
	                notEmpty: {
	                    message: 'Please upload a profile picture'
	                }
                
                	}
                },
                agree: {
                    excluded: false,
                    validators: {
                        callback: {
                            message: 'You must agree with the terms and conditions',
                            callback: function(value, validator, $field) {
                                return value === 'yes';
                            }
                        }
                    }
               
                }
            } 
        });
 
	    $('#agreeButton, #disagreeButton').on('click', function() {
	        var whichButton = $(this).attr('id');

	        $('#tutor_application_form')
	            .find('[name="agree"]')
	                .val(whichButton === 'agreeButton' ? 'yes' : 'no')
	                .end()
	            // Revalidate the field manually
	            .formValidation('revalidateField', 'agree');
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