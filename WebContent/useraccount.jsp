<!DOCTYPE html>
<html lang="en-US">
<head>
	<jsp:include page="sharedJSP/head.jsp"/>
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
								<h1>User Account</h1>
							</div>
							<div class="fright breadc">
								<a href="index.jsp">Home</a> / User Account
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- TITLE ENDS -->
		
		<div class="container padtop40 padbot50">
		<div class="row">
			<div class="col-md-6">
				<span class="smalltitle">Taken courses</span>
				<table id="edd_user_history">
				<thead>
				<tr>
					<th>
						Course
					</th>
					<th>
						Tutor
					</th>
					<th>
						Status
					</th>
					<th>
						Details
					</th>
				</tr>
				</thead>
				<tr>
					<td>
						Node.JS by Pro
					</td>
					<td>
						Darren Bui
					</td>
					<td>
						next class on 3 days 12 hours.
					</td>
					<td>
						<a href="purchaseconfirmation.html">Manage timetable</a>
					</td>
				</tr>
				</table>
				<div id="edd_purchase_history_pagination" class="edd_pagination navigation">
				</div>
				<div style="margin-top:40px;display:block;">
					<span class="smalltitle">Your tutors</span>
					<div class="shoppingcartarea">
						<p class="edd-cart-number-of-items" style="display:none;">
							Number of items in cart: <span class="edd-cart-quantity">0</span>
						</p>
						<ul class="edd-cart">
							<li class="cart_item empty">You haven't enrolled with any tutors yet.</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<span class="smalltitle">Profile Editor</span>

								
				<% if(request.getAttribute("updateSuccess") != null) { %>
					<div class="alert alert-success"> ${updateSuccess}</div> 
				<% } %>
				<% if(request.getAttribute("passwordError") != null || request.getAttribute("updateError") != null) { %>
					<div class="alert alert-danger" role="alert"> <div>${passwordError}</div><div>${updateError}</div></div> 
				<% } %>
								
				<form id="edd_profile_editor_form" class="edd_form form-horizontal" action="myProfile" method="post">
					<fieldset>
					
						<!-------------------------------------	General info ------------------------------------->
						<div class="panel panel-default">
			            	<div class="panel-heading"> 
			            		<h4 class="panel-title">
			                  		<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#generalInfo">
			                   			 Details <span class="glyphicon chevron-up"></span>
			                    		<i class="fa fa-lg fa-caret-square-o-down pull-right"></i>
			                 		</a>
		                		</h4>
			              	</div>
			              	
			              	
								
			              	<div id="generalInfo" class="panel-collapse collapse in">
			                	<div class="panel-body">
			                		<div class='col-md-6 nopadding-left form-group'>
				                		<label for="edd_first_name">First Name</label>
										<input id="edd_first_name" class="required form-control edd-input" type="text" name ="firstname" value="${firstname}"/>
									</div>
			                		<div class='col-md-6 nopadding form-group'>
				                		<label for="edd_first_name">Last Name</label>
										<input id="edd_last_name" class="required form-control edd-input" type="text" name = "lastname" value="${lastname}"/>
									</div>
			                		<div class='col-md-12 nopadding-left form-group'>
										<label for="edd_email">Email Address</label>
										<input id="edd_email" class="edd-input" type="email" name = "email" value="${email}" readonly/>
									</div>
			                		<div class='col-md-12 nopadding-left form-group'>
										<label>New Password</label>
										<input id="edd_new_user_pass1" class="password form-control edd-input" name = "password" type="password"/>
									</div>
			                		<div class='col-md-12 nopadding-left form-group'>
										<label>Re-enter Password</label>
										<input id="edd_new_user_pass2" class="password form-control edd-input" name = "confirm_password" type="password"/>
									</div>
									
									<p class="col-md-12">
										Please note after changing your password, you must log back in. Please leave blank password field if you don't want to change it.
									</p>
								</div>
			              	</div>
			            </div>
			            
						<!-------------------------------------	Address	------------------------------------->
						<div class="panel panel-default">
			            	<div class="panel-heading"> 
			            		<h4 class="panel-title">
			                  		<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
			                   			 Address <span class="glyphicon chevron-up"></span>
			                    		<i class="fa fa-lg fa-caret-square-o-down pull-right"></i>
			                 		</a>
		                		</h4>
			              	</div>
			              	<div id="collapseThree" class="panel-collapse collapse">
			                	<div class="panel-body">
			                	
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
																		
			                     	<div class='col-md-6'>
			                     		<div class="form-group">
			                      			<label for="edd_last_name">Postcode</label>
											<input name="postcode" id="edd_postcode" class="form-control edd-input" type="text" value="${postcode}"/>
			                   			</div>
			                   		</div>
			                   		<div class='col-md-6'>
			                   			<div class="form-group">
			                      			<label for="edd_last_name">State</label>
											<input name="state" id="edd_state" class="form-control edd-input" type="text" value="${state}"/>
			                     		</div>
			                     	</div>
			                     	<div class='col-md-6'>
			                     		<div class="form-group">
			                      			<label for="edd_last_name">Country</label>
											<input name="country" id="edd_country" class="form-control edd-input" type="text" value="${country}"/>
				                		</div>
				                	</div>
			                	</div>
			              	</div>
			            </div>
			            
						
						
						<p id="edd_profile_submit_wrap">
							<input id="edd_profile_editor_submit" type="submit" class="edd_submit" name="action" value="Update Details"/>
						</p>
					</fieldset>
				</form>
				<!-- #edd_profile_editor_form -->
				<form name="logoutform" method="post">
					<a class="button btn btn-danger logout" href="#" title="Logout" onClick="document.forms['logoutform'].submit();"><i class="fa fa-sign-out"></i> Log Out</a>
				</form>
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
	fillFields();
	formValidation();
});


	function fillFields(){
		
		
	}
	
	function formValidation(){
    	//Update User Form
	    $('#edd_profile_editor_form')
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
                        stringLength: {
                            max: 50,
                            message: 'Address is too long'
                        }
                    }
                },
                suburb: {
                	validators: {
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
                		regexp: {
                            message: 'Letters only',
                            regexp: /^[A-Za-z_\-\ ]*$/
                        },
                        stringLength: {
                            max: 3,
                            message: 'State is too long, please enter the aberration of the state'
                        }
                    }
                },
                postcode: {
                	validators: {
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
                password: {
                    validators: {
                        regexp: {
                        	regexp: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/,
                        	message: 'Minimum 6 characters and contains at least 1 letter and number'
                        },
                        identical: {
                            field: 'confirm_password',
                            message: 'The password and its confirm are not the same'
                        }
                    }
                },
                confirm_password: {
                    validators: {
                    	identical: {
                            field: 'password',
                            message: 'The password and its confirm are not the same'
                        }
                    }
                }
                
            }
        });
    	
	    <% if(request.getAttribute("firstNameRegexError") != null) { %>
			$('#edd_profile_editor_form').formValidation('updateStatus', 'firstname', 'NOT_VALIDATED').formValidation('validateField', 'firstname');
		<% } if(request.getAttribute("lastNameRegexError") != null) { %>
			$('#edd_profile_editor_form').formValidation('updateStatus', 'lastname', 'NOT_VALIDATED').formValidation('validateField', 'lastname');
		<% } if(request.getAttribute("addressLengthError") != null) { %>
			$('#edd_profile_editor_form').formValidation('updateStatus', 'address', 'NOT_VALIDATED').formValidation('validateField', 'address');
		<% } if(request.getAttribute("suburbLengthError") != null) { %>
			$('#edd_profile_editor_form').formValidation('updateStatus', 'suburb', 'NOT_VALIDATED').formValidation('validateField', 'suburb');
		<% } if(request.getAttribute("stateLengthError") != null) { %>
			$('#edd_profile_editor_form').formValidation('updateStatus', 'state', 'NOT_VALIDATED').formValidation('validateField', 'state');
		<% } if(request.getAttribute("postcodeRegexError") != null) { %>
			$('#edd_profile_editor_form').formValidation('updateStatus', 'postcode', 'NOT_VALIDATED').formValidation('validateField', 'postcode');
		<% } if(request.getAttribute("countryLengthError") != null) { %>
			$('#edd_profile_editor_form').formValidation('updateStatus', 'country', 'NOT_VALIDATED').formValidation('validateField', 'country');
		<% } %>
    }
				
	</script>
	<!-- /script -->
</body>
</html>