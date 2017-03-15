<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en-US">

<% if (!Boolean.TRUE.equals(session.getAttribute("isAdmin"))) { %>
<jsp:forward page = "accessDenied.jsp" />
<% } %>

<head>
	<jsp:include page="sharedJSP/head.jsp"/>
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
					<div id="tableDiv"></div>
					
					<!-- Modify User Form -->
					<div class="modal fade" id="modifyUserModal" tabindex="-1"
						role="dialog" aria-labelledby="modifyUserModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="modifyUserModalLabel">Modify User</h4>
								</div>
								<div class="modal-body">
									<form id = "modifyUserForm" action = "user" method = "post">
										<div class="form-group" style="display: none;">
											<input type="text" class="form-control" id="pk" name = "pk">
										</div>
										<div class="form-group">
											<label for="firstname" class="control-label">First Name:</label>
											<input type="text" class="form-control" id="firstname" name = "firstname">
										</div>
										<div class="form-group">
											<label for="lastname" class="control-label">Last Name:</label>
											<input type="text" class="form-control" id="lastname" name = "lastname">
										</div>
										<div class="form-group">
											<label for="email" class="control-label">Email:</label>
											<input readonly type="text" class="form-control" id="email" name = "email">
										</div>
										<div class="form-group">
											<label for="email" class="control-label">State:</label>
											<select class="form-control" id="active" name = "active">
												<option value = "0">Inactive</option>
												<option value = "1">Active</option>
											</select>
										</div>
										<div class="form-group">
											<label for="phonenumber" class="control-label">Phone Number:</label>
											<input type="text" class="form-control" id="phonenumber" name = "phonenumber">
										</div>
										<div class="form-group">
											<label for="address" class="control-label">Address:</label>
											<input type="text" class="form-control" id="address" name = "address">
										</div>
										<div class="form-group">
											<label for="suburb" class="control-label">Suburb:</label>
											<input type="text" class="form-control" id="suburb" name = "suburb">
										</div>
										<div class="form-group">
											<label for="state" class="control-label">State:</label>
											<input type="text" class="form-control" id="state" name = "state">
										</div>
										<div class="form-group">
											<label for="postcode" class="control-label">Post Code:</label>
											<input type="text" class="form-control" id="postcode" name = "postcode">
										</div>
										<div class="form-group">
											<label for="country" class="control-label">Country:</label>
											<input type="text" class="form-control" id="country" name = "country">
										</div>
										<div class="form-group">
											<input type="button" class="btn btn-primary" id="changepassword" value = "Change Password" data-toggle="modal" href="#changePasswordModal" value = "Change Password">
										
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
											<button type="submit" class="btn btn-primary" name="action" value="updateUser">Update</button>
										</div>
									</form>
								</div>
								
							</div>
						</div>
					</div>
					
					<!-- Change Password Form -->
					<div class="modal fade" id="changePasswordModal" tabindex="-2"
						role="dialog" aria-labelledby="changePasswordModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="changePasswordModalLabel">Change Password</h4>
								</div>
								<div class="modal-body">
									<form id = "changePasswordForm" action = "user" method = "post">
									<div class="form-group" style="display: none;">
											<input type="text" class="form-control" id="userPk" name = "pk">
										</div>
										<div class="form-group">
											<label for="password" class="control-label">New Password:</label>
											<input type="password" class="form-control" id="password" name = "password">
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
											<button type="submit" class="btn btn-primary" name="action" value="updatePassword">Confirm</button>
										</div>
									</form>
								</div>
								
							</div>
						</div>
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
		var id
		$('#modifyUserModal').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget) // Button that triggered the modal
			id = button.data('key') // Extract info from data-* attributes
			$.getJSON("/rest/users/"+id, function(data){
				$('.modal-body #pk').val(id);
        		$('.modal-body #firstname').val(data[0].ur_firstname);
        		$('.modal-body #lastname').val(data[0].ur_lastname);
        		$('.modal-body #email').val(data[0].ur_email);
        		if (data[0].ur_isactive === 0){
        			$('.modal-body #active').val("0");
				}
        		else{
        			$('.modal-body #active').val("1");
        		}
        		$('.modal-body #phonenumber').val(data[0].ur_phonenumber);
        		$('.modal-body #address').val(data[0].ur_address);
        		$('.modal-body #suburb').val(data[0].ur_suburb);
        		$('.modal-body #state').val(data[0].ur_state);
        		$('.modal-body #postcode').val(data[0].ur_postcode);
        		$('.modal-body #country').val(data[0].ur_country);
        	});
		})
		$('#changePasswordModal').on('show.bs.modal', function(event) {
			$('.modal-body #userPk').val(id);
		})
	</script>
	
	<script>
        var users;
        $(document).ready(function() {
        	formValidation();
        	$.getJSON("/rest/users", function(data){
        		users = data;
        		createTable();
        	});
            
        });
        function createTable(){ //Create User Table
        	var table = $('<table id = "userTable" class="table table-striped table-bordered" cellspacing="0" width="100%" align="center"/>').appendTo($('#tableDiv'));
        	var thead = $('<thead/>').appendTo($('#userTable'));
		$('<tr/>').appendTo(thead).append($('<th/>').text('First Name')).append($('<th/>').text('Last Name')).append($('<th/>').text(' '));
            $(users).each(function(i, user) {
                $('<tr/>').appendTo(table)
                    .append($('<td/>').text(user.ur_firstname))
                    .append($('<td/>').text(user.ur_lastname))
                    .append($('<td/>').append($('<input type="button" class="btn btn-primary" data-toggle="modal" data-target="#modifyUserModal" data-key='+user.ur_pk+' value = "Edit"/>').text(user.ur_lastname)));
            });
            
        }
        
        function formValidation(){
        	//Update User Form
    	    $('#modifyUserForm')
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
                    phonenumber: {
                    	validators: {
                            stringLength: {
                                max: 15,
                                message: 'Phone Number is too long'
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
                    }
                    
                }
            });
    	    //Change Password Form -------
    		$('#changePasswordForm')
    	        .formValidation({
    	            framework: 'bootstrap',
    	            icon: {
    	                valid: 'glyphicon glyphicon-ok',
    	                invalid: 'glyphicon glyphicon-remove',
    	                validating: 'glyphicon glyphicon-refresh'
    	            },
    	            fields: {
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
        }
        
        
    </script>
</body>
</html>