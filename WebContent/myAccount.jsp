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
	<div id="boxedcontent">
	
		<div class="templatemyaccount">
		
			<!-- TITLE BEGINS -->
			<div class="headerimage" style="background-image: url(assets/img/back_to_school.jpg);">
				<div class="headercontent">
					<div class="container">
						<div class="row">
							<div class="col-md-12">
								<div class="fleft">
									<h1>My Account</h1>
								</div>
								<div class="fright breadc">
									<a href="index.jsp">Home</a> / My Account
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- TITLE ENDS -->
			<!-- http://jsfiddle.net/LHZXw/1/ validation before submission
			 -->
			<div class="container padtop40 padbot50">
				<div class="row">
				<!-- MAIN CONTENT BEGINS -->
				<div class="col-md-12">
					<div class="wraplogin">
						<form id="edd_login_form" class="edd_form form-horizontal" action="login" method="post">
							<fieldset>
								<legend>Log into Your Account</legend>
									
								<% if(request.getAttribute("loginError") != null) { %>
									<div class="alert alert-danger"> ${loginError}</div> 
								<% } %>
									
								<div class="form-group">
									<label class="control-label col-xs-1">Email</label>
									
									<div class="col-xs-5">
										<input name="email" id="edd_user_login" class="required edd-input form-control" type="text" title="Email" value="${loginEmail}"/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-xs-1">Password</label>
									<div class="col-xs-5">
										<input name="password" id="edd_user_pass" class="password required edd-input form-control" type="password"/>
									</div>
								</div>
								<p>
									<input id="edd_login_submit" type="submit" class="edd_submit" name="action" value="Sign in"/> <!-- matching request.getParameter("action") in LoginController -->
								</p>
								<p class="edd-lost-password has-success">
									<a href="#" title="Lost Password">
									Lost Password? </a>
								</p>
							</fieldset>
						</form>
					</div>
					<span class="or">or</span>
					<div class="wrapregister">
						<form id="edd_register_form" class="edd_form form-horizontal" action="register" method="post">
							<fieldset>
								<legend>Register New Account</legend>
								<div class="form-group">
									<label class="control-label col-xs-1">Given Name</label>
									<div class="col-xs-5">
										<input id="edd-user-login" class="required edd-input form-control" value="${firstname}" type="text" name="firstname" title="Given Name"/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-xs-1">Family Name</label>
									<div class="col-xs-5">
										<input id="edd-user-login" class="required edd-input form-control" value="${lastname}" type="text" name="lastname" title="Family Name"/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-xs-1">Email</label>
									<div class="col-xs-5">
										<input id="edd-user-email" class="required edd-input form-control" value="${email}" type="email" name="email" title="Email Address"/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-xs-1">Password</label>
									<div class="col-xs-5">
										<input id="edd-user-pass" class="password required edd-input form-control" value="${password}" type="password" name="password"/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-xs-1">Confirm Password</label>
									<div class="col-xs-5">
										<input id="edd-user-pass2" class="password required edd-input form-control" value="${confirm_password}" type="password" name="confirm_password"/>
									</div>
								</div>
								<p>
									<input class="button" name="edd_register_submit" type="submit" value="Register"/>
								</p>
							</fieldset>
						</form>
					</div>
				</div>				
				<!-- MAIN CONTENT ENDS -->
					
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
	<!-- /script -->

	
	
	
	
	<script>
	
	$(document).ready(function() {
	    //LOGIN FORM -------
		$('#edd_login_form')
	        .formValidation({
	            framework: 'bootstrap',
	            icon: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
	            fields: {
	                email: {
	                    validators: {
	                        notEmpty: {
	                            message: 'The email address is required'
	                        },
	                        regexp: {
	                            message: 'The input is not a valid email address',
	                            regexp: /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
	                        }
	                    }
	                },
	                password: {
	                    validators: {
	                        notEmpty: {
	                            message: 'The password is required'
	                        },
	                        stringLength: {
	                            min: 6,
	                            message: 'The password must be longer than 6 characters'
	                        }
	                    }
	                }
	            }
	        });
	    //REGISTER FORM -----
	    $('#edd_register_form')
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
            	email: {
                    validators: {
                        notEmpty: {
                            message: 'The email address is required'
                        },
                        regexp: {
                            message: 'The input is not a valid email address',
                            regexp: /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
                        },
                        remote: {
                            message: 'The email is taken!',
                            url: '/rest/validation/email',
                            delay: 500,
                            data: {
                                type: 'email'
                            },
                            type: 'POST'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: 'The password is required'
                        },
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
                    	notEmpty: {
                            message: 'This field is empty'
                        },
                    	identical: {
                            field: 'password',
                            message: 'The password and its confirm are not the same'
                        }
                    }
                }
            }
        });
	    
	    <% if(request.getAttribute("firstNameError") != null) { %>
			$('#edd_register_form').formValidation('updateStatus', 'firstname', 'NOT_VALIDATED').formValidation('validateField', 'firstname');
		<% } if(request.getAttribute("lastNameError") != null) { %>
			$('#edd_register_form').formValidation('updateStatus', 'lastname', 'NOT_VALIDATED').formValidation('validateField', 'lastname');
		<% } if(request.getAttribute("emailError") != null) { %>
			$('#edd_register_form').formValidation('updateStatus', 'email', 'NOT_VALIDATED').formValidation('validateField', 'email');
		<% } if(request.getAttribute("passwordError") != null) { %>
			$('#edd_register_form').formValidation('updateStatus', 'password', 'NOT_VALIDATED').formValidation('validateField', 'password');
			$('#edd_register_form').formValidation('updateStatus', 'confirm_password', 'NOT_VALIDATED').formValidation('validateField', 'confirm_password');
		<% } %>
		
		
	});
	</script>
	
</body>
</html>