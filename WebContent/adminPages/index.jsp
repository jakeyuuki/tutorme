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
	<jsp:include page="/sharedJSP/header.jsp" />

	<!-- PAGE CONTENT
	================================================== -->
	<div id="boxedcontent">
		
		

		<div class="templatemyaccount">

			<!-- TITLE BEGINS -->
			<div class="headerimage"
				style="background-image: url(/assets/img/back_to_school.jpg);">
				<div class="headercontent"></div>
			</div>
			 
			 
			<!-- basic scripts -->
			<div class="container padtop40 padbot50">
				<div class="row">
					
					<table id="user-table"></table>
					<div id="user-pager"></div>
					
					<br/>
					
					<table id="app-table"></table>
					<div id="app-pager"></div>
					
					<table id="comment-table"></table>
					<div id="comment-pager"></div>






					<!-- View Certification Form -->
					<div class="modal fade" id="viewCertificationModal" tabindex="-2"
						role="dialog" aria-labelledby="viewCertificationModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="viewCertificationModalLabel">Certification</h4>
								</div>
								<div class="modal-body">
									<form id = "viewCertificationForm">
										
										<div class="form-group" style="display: none;">
											<input type="text" class="form-control" id="certPKViewCertification" name = "pk">
										</div>
										
										<div class="form-group" style="display: none;">
											<input type="text" class="form-control" id="userPKViewCertification" name = "ur_pk">
										</div>

										<div id="alert_message2" class="alert">
										</div>
										
										<div class="form-group">
											<label for="certname" class="control-label">Certification Name:</label>
											<input type="text" class="form-control" id="certname" name = "certname" value="${cn_name}" readonly>
										</div>
										<div class="form-group">
											<label for="certdescription" class="control-label">Certification Description:</label>
											<input type="text" class="form-control" id="certdescription" name = "certdescription" value="${cn_description}" readonly>
										</div>
										<div class="form-group">
											<label for="btnScan" class="control-label">Click to view certification scan</label>
											<button type="button" id="btnScan" class="btn btn-primary" onClick="viewCertificationScan('${cn_scan}')">View</button>
										</div>
										
										
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>

											<button id="btnApprove" type="submit" class="btn btn-primary" name="action" value="approveApplication">Approve</button>
											<button id="btnDeny" type="submit" class="btn btn-primary" name="action" value="denyApplication">Deny</button>
											
										</div>
									</form>
								</div>
								
							</div>
						</div>
					</div>
					
					
					
					
					
					
					
					<!-- View Certification Scan -->
					<div class="modal fade" id="viewScanModal" tabindex="-2"
						role="dialog" aria-labelledby="viewScanModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
							 <div class="vertical-alignment-helper">
       						 <div class="modal-dialog vertical-align-center">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="viewScanModalLabel">Certification Scan</h4>
								</div>
								<div class="modal-body text-center">
									<form id = "viewScanForm">
	
										
										<div id="certificatePicture">
											
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>

											
										</div>
									</form>
								</div>
								</div>
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
									<form id = "changePasswordForm">
									<div class="form-group" style="display: none;">
											<input type="text" class="form-control" id="userPkChangePassword" name = "pk">
										</div>
										<div id="alert_message" class="alert" style="display: none">
										</div>
										<div class="form-group">
											<label for="password" class="control-label">New Password:</label>
											<input type="password" class="form-control" id="password" name = "password">
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
											<button id="btnSubmit" type="submit" class="btn btn-primary" name="action" value="updatePassword">Confirm</button>
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
	
	<!-- basic scripts -->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>

		<!-- page specific plugin scripts -->
		<script src="/adminPages/assets/js/date-time/bootstrap-datepicker.js"></script>
		<script src="/adminPages/assets/js/jqGrid/jquery.jqGrid.src.js"></script>
		<script src="/adminPages/assets/js/jqGrid/i18n/grid.locale-en.js"></script>

		<!-- ace scripts -->
		<script src="/adminPages/assets/js/ace/ace.js"></script>
		<script src="/adminPages/assets/js/ace/ace.sidebar.js"></script>
		<script src="/adminPages/assets/js/ace/ace.sidebar-scroll-1.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
						
			jQuery(function($) {
				var grid_selector = "#user-table";
				var pager_selector = "#user-pager";
				
				var grid_selector_app = "#app-table";
				var pager_selector_app = "#app-pager";
				
				//resize to fit page size
				$(window).on('resize.jqGrid', function () {
					$(grid_selector).jqGrid( 'setGridWidth', $(".row").width() );
			    })
				//resize on sidebar collapse/expand
				var parent_column = $(grid_selector).closest('[class*="col-"]');
				$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
					if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
						//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
						setTimeout(function() {
							$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
						}, 0);
					}
			    })
	
				jQuery(grid_selector).jqGrid({
					mtype: "POST",
					datatype: 'json',
					url: '/controller/admin/userValues',
					editurl: '/controller/admin/userEdit',
					height: 'auto',
					jsonReader: { root: 'values', id: 'ur_pk', repeatitems: false },
					colNames: ['  ', 'id', 'First Name', 'Last Name', 'Email', 'Actived', 'Is Admin', 'Is Tutor', 'Is Active','Phone Number', 'Address', 'Suburb', 'State' ,'Post Code' ,'Country', '  '],
					colModel:[
						{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
							formatter:'actions', 
							formatoptions:{ 
								keys:true,
								delbutton: false,//disable delete button
								
								//delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
								//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
							}
						},
						{name:'ur_pk',index:'ur_pk', hidden: true, align: 'center'},
						{name:'ur_firstname',index:'ur_firstname',width:90, editable:true, searchoptions: { sopt: ['cn', 'bw', 'ew', 'eq']} },
						{name:'ur_lastname',index:'ur_lastname', width:90,editable: true, searchoptions: { sopt: ['cn', 'bw', 'ew', 'eq']} },
						{name:'ur_email',index:'ur_email', width:90,editable: false, searchoptions: { sopt: ['cn', 'bw', 'ew', 'eq']}, edittype:"checkbox",editoptions: {value:"true:false"} },
						{name:'ur_activated',index:'ur_activated', width:90,editable: true, searchoptions: { sopt: ['cn', 'bw', 'ew', 'eq']}, edittype:"checkbox",editoptions: {value:"true:false"}, unformat: aceSwitch },
						{name:'ur_isadmin',index:'ur_isadmin', width:90,editable: true, searchoptions: { sopt: ['cn', 'bw', 'ew', 'eq']}, edittype:"checkbox",editoptions: {value:"true:false"}, unformat: aceSwitch },
						{name:'ur_istutor',index:'ur_istutor', width:90,editable: true, searchoptions: { sopt: ['cn', 'bw', 'ew', 'eq']}, edittype:"checkbox",editoptions: {value:"true:false"}, unformat: aceSwitch },
						{name:'ur_isactive',index:'ur_isactive', width:90,editable: true, searchoptions: { sopt: ['cn', 'bw', 'ew', 'eq']}, edittype:"checkbox",editoptions: {value:"true:false"}, unformat: aceSwitch},
						{name:'ur_phonenumber',index:'ur_phonenumber', hidden: true, editable:true, editrules: { edithidden: true } },
						{name:'ur_address',index:'ur_address', hidden: true, editable:true, editrules: { edithidden: true } },
						{name:'ur_suburb',index:'ur_suburb', hidden: true, editable:true, editrules: { edithidden: true } },
						{name:'ur_state',index:'ur_state', hidden: true, editable:true, editrules: { edithidden: true } },
						{name:'ur_postcode',index:'ur_postcode', hidden: true, editable:true, editrules: { edithidden: true } },
						{name:'ur_country',index:'ur_country', hidden: true, editable:true, editrules: { edithidden: true } },
						{name:'changepassword',index:'',width:150,fixed:true,sortable:false,resize:false,viewable:false, formatter: function(cellvalue, options, rowObject) {
					        return '<button class="btn btn-primary" onclick="changePasswordAction(\''+options.rowId+'\');">Change Password</button>'; } }
					], 
			
					viewrecords : true,
					rowNum:10,
					rowList:[10,20,30],
					pager : pager_selector,
					altRows: true,
					//toppager: true,
					
					multiselect: true,
					//multikey: "ctrlKey",
			        multiboxonly: true,
			        ondblClickRow: myOndblClickRow,
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							styleCheckbox(table);
							
							updateActionIcons(table);
							updatePagerIcons(table);
							enableTooltips(table);
						}, 0);
					},
			

					caption: "Users",
					autowidth: true
			
				});
				
				jQuery(grid_selector_app).jqGrid({
					mtype: "POST",
					datatype: 'json',
					url: '/controller/admin/pendingApplications',
					height: 'auto',
					jsonReader: { root: 'values', id: 'cn_tr_pk', repeatitems: false },
					colNames: ['  ', 'id', 'Cn_Name', 'Cn_description', 'Cn_scan', 'First Name', 'Last Name', 'Email', '  '],
					colModel:[
						{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
							formatter:'actions', 
							formatoptions:{ 
								keys:true,
								delbutton: false,//disable delete button
								
								//delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
								//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
							}
						},
						{name:'ur_pk',index:'ur_pk', hidden: true, align: 'center'},
						{name:'cn_name',index:'cn_name', hidden: true, align: 'center'},
						{name:'cn_description',index:'cn_description', hidden: true, align: 'center'},
						{name:'cn_scan',index:'cn_scan', hidden: true, align: 'center'},
						{name:'ur_firstname',index:'ur_firstname',width:90, editable:true, searchoptions: { sopt: ['cn', 'bw', 'ew', 'eq']} },
						{name:'ur_lastname',index:'ur_lastname', width:90,editable: true, searchoptions: { sopt: ['cn', 'bw', 'ew', 'eq']} },
						{name:'ur_email',index:'ur_email', width:90,editable: false, searchoptions: { sopt: ['cn', 'bw', 'ew', 'eq']}, edittype:"checkbox",editoptions: {value:"true:false"} },
						{name:'viewcertification',index:'',width:150,fixed:true,sortable:false,resize:false,viewable:false, formatter: function(cellvalue, options, rowObject) {
							console.log(cellvalue, options, rowObject);
					        return '<button class="btn btn-primary" onclick="viewCertificationAction(\''+options.rowId+'\', \''+rowObject.cn_name+'\', \''+rowObject.cn_description+'\', \''+rowObject.ur_pk+'\', \''+rowObject.cn_scan+'\');">View Certification</button>'; } }
					], 
			
					viewrecords : true,
					rowNum:10,
					rowList:[10,20,30],
					pager : pager_selector_app,
					altRows: true,
					//toppager: true,
					
					multiselect: true,
					//multikey: "ctrlKey",
			        multiboxonly: true,
			
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							styleCheckbox(table);
							
							updateActionIcons(table);
							updatePagerIcons(table);
							enableTooltips(table);
						}, 0);
					},
					caption: "Tutor Applications",
					autowidth: true
				});
				
				$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
				$(grid_selector).parents('div.ui-jqgrid-bdiv').css("max-height","600px");
				$(grid_selector_app).parents('div.ui-jqgrid-bdiv').css("max-height","600px");

				//switch element when editing inline
				function aceSwitch( cellvalue, options, cell ) {
					setTimeout(function(){
						$(cell) .find('input[type=checkbox]')
							.attr({
								"data-toggle": "toggle",
								"data-style": "ios",
								"data-size": "mini",
								"data-on": "True",
								"data-off": "False"
							})
							.after('<span class="lbl"></span>').bootstrapToggle();
					}, 0);
				}
				//enable datepicker
				function pickDate( cellvalue, options, cell ) {
					setTimeout(function(){
						$(cell) .find('input[type=text]')
								.datepicker({format:'yyyy-mm-dd' , autoclose:true}); 
					}, 0);
				}
				
				function myOndblClickRow(id, ri, ci) {
					var editButton = $('#jEditButton_' + id);
					if (editButton.is(":visible"))
						editButton.click();
				}
			
				//navButtons
				jQuery(grid_selector).jqGrid('navGrid',pager_selector,
					{ 	//navbar options
						edit: true,
						editicon : 'ace-icon fa fa-pencil blue',
						add: false,
						addicon : 'ace-icon fa fa-plus-circle purple',
						del: false,
						delicon : 'ace-icon fa fa-trash-o red',
						search: false,
						searchicon : 'ace-icon fa fa-search orange',
						refresh: true,
						refreshicon : 'ace-icon fa fa-refresh green',
						view: true,
						viewicon : 'ace-icon fa fa-search-plus grey',
					},
					{
						//edit record form
						closeAfterEdit: true,
						closeOnEscape: true,
						recreateForm: true,
						viewPagerButtons: true,
						resize: true,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
							style_edit_form(form);
						},
						afterSubmit: function (response, postdata) {
							var data = eval('(' + response.responseText + ')');
							console.log(data);
							return [data.success, data.message, data.id];
						}
					},
					{
						//new record form
						//width: 700,
						closeAfterAdd: true,
						recreateForm: true,
						viewPagerButtons: true,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
							.wrapInner('<div class="widget-header" />')
							style_edit_form(form);
						}
					},
					{
						//delete record form
						recreateForm: true,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							if(form.data('styled')) return false;
							
							form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
							style_delete_form(form);
							
							form.data('styled', true);
						},
						onClick : function(e) {
							//alert(1);
						}
					},
					{
						//search form
						recreateForm: true,
						afterShowSearch: function(e){
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
							style_search_form(form);
						},
						afterRedraw: function(){
							style_search_filters($(this));
						}
						,
						multipleSearch: true,
						/**
						multipleGroup:true,
						showQuery: true
						*/
					},
					{
						//view record form
						recreateForm: true,
						beforeShowForm: function(e){
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
						}
					}
				)
			
			
				
				function style_edit_form(form) {
					//enable datepicker on "sdate" field and switches for "stock" field
					form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
					
					form.find('input[name=stock]').addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
							   //don't wrap inside a label element, the checkbox value won't be submitted (POST'ed)
							  //.addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');
			
							
					//update buttons classes
					var buttons = form.next().find('.EditButton .fm-button');
					buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();//ui-icon, s-icon
					buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
					buttons.eq(1).addClass('btn-danger').prepend('<i class="ace-icon fa fa-times"></i>')
					
					buttons = form.next().find('.navButton a');
					buttons.find('.ui-icon').hide();
					buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
					buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');		
				}
			
				function style_delete_form(form) {
					var buttons = form.next().find('.EditButton .fm-button');
					buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();//ui-icon, s-icon
					buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
					buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>')
				}
				
				function style_search_filters(form) {
					form.find('.delete-rule').val('X');
					form.find('.add-rule').addClass('btn btn-xs btn-primary');
					form.find('.add-group').addClass('btn btn-xs btn-success');
					form.find('.delete-group').addClass('btn btn-xs btn-danger');
				}
				function style_search_form(form) {
					var dialog = form.closest('.ui-jqdialog');
					var buttons = dialog.find('.EditTable')
					buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
					buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'ace-icon fa fa-comment-o');
					buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'ace-icon fa fa-search');
				}
				
				function beforeDeleteCallback(e) {
					var form = $(e[0]);
					if(form.data('styled')) return false;
					
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
					style_delete_form(form);
					
					form.data('styled', true);
				}
				
				function beforeEditCallback(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
					style_edit_form(form);
				}
			
			
			
				//it causes some flicker when reloading or navigating grid
				//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
				//or go back to default browser checkbox styles for the grid
				function styleCheckbox(table) {
				/**
					$(table).find('input:checkbox').addClass('ace')
					.wrap('<label />')
					.after('<span class="lbl align-top" />')
			
			
					$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
					.find('input.cbox[type=checkbox]').addClass('ace')
					.wrap('<label />').after('<span class="lbl align-top" />');
				*/
				}
				
			
				//unlike navButtons icons, action icons in rows seem to be hard-coded
				//you can change them like this in here if you want
				function updateActionIcons(table) {
					/**
					var replacement = 
					{
						'ui-ace-icon fa fa-pencil' : 'ace-icon fa fa-pencil blue',
						'ui-ace-icon fa fa-trash-o' : 'ace-icon fa fa-trash-o red',
						'ui-icon-disk' : 'ace-icon fa fa-check green',
						'ui-icon-cancel' : 'ace-icon fa fa-times red'
					};
					$(table).find('.ui-pg-div span.ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
					*/
				}
				
				//replace icons with FontAwesome icons like above
				function updatePagerIcons(table) {
					var replacement = 
					{
						'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
						'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
						'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
						'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
					};
					$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
				}
			
				function enableTooltips(table) {
					$('.navtable .ui-pg-button').tooltip({container:'body'});
					$(table).find('.ui-pg-div').tooltip({container:'body'});
				}
			
				//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
			
				$(document).one('ajaxloadstart.page', function(e) {
					$(grid_selector).jqGrid('GridUnload');
					$('.ui-jqdialog').remove();
				});
			});
		</script>
        <script>
            var users;
            $(document).ready(function() {
            	formValidation();                
            });
            
            function formValidation(){
            	//Update User Form
        	    $('#FrmGrid_user-table')
                .formValidation({
                    framework: 'bootstrap',
                    icon: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                    	ur_firstname: {
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
                        ur_lastname: {
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
                        ur_phonenumber: {
                        	validators: {
                                stringLength: {
                                    max: 15,
                                    message: 'Phone Number is too long'
                                }
                            }
                        },
                        ur_address: {
                        	validators: {
                                stringLength: {
                                    max: 50,
                                    message: 'Address is too long'
                                }
                            }
                        },
                        ur_suburb: {
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
                        ur_state: {
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
                        ur_postcode: {
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
                        ur_country: {
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
            
            certificationPath = "";
            
            function changePasswordAction(id) {
				$("#changePasswordModal").modal('show'); 
				$('#userPkChangePassword').val(id);
			}
            
            function viewCertificationAction(id, cn_name, cn_description,  ur_id, cn_scan) {
				$("#viewCertificationModal").modal('show'); 
				$('#certPKViewCertification').val(id);
				$('#userPKViewCertification').val(ur_id);
				$('#certname').val(cn_name);
				$('#certdescription').val(cn_description);
				certificationPath = cn_scan;
			}
            
            
            function viewCertificationScan() {
            	
				$("#viewCertificationModal").modal('hide'); 
				$("#viewScanModal").modal('show'); 
				$('#certUrl').val(certificationPath);
				if (certificationPath != "" && isExtensionPDFPath(certificationPath)){
					$('#certificatePicture').html('<embed id="embed_pdf" src="'+certificationPath+'" width="500" height="375" type="application/pdf">');
				}else{
					$('#certificatePicture').html('<img src="'+certificationPath+'" id="image_src" width="500" height="375"/>');
								
				} 
		

			}
            
            function isExtensionPDFPath(certPath) {
        		var tokens = certPath.split(".");
        		var extensionName = tokens[tokens.length - 1];
        		return extensionName == "pdf";
        	}

            $('#viewCertificationForm').unbind('submit');
            
            $('#btnApprove').click(function() {
			//$.post('/controller/admin/approveApplication', { id: $('#certPKViewCertification').val(), ur_pk:'fd849bc3-0386-4baf-a558-2804e782d070'}, function (data) {

            	$.post('/controller/admin/approveApplication', { id: $('#certPKViewCertification').val(), ur_pk:$('#userPKViewCertification').val()}, function (data) {
            		$('#alert_message2').removeClass('alert-success alert-danger');
            		$('#alert_message2').addClass(data.success ? 'alert-success' : 'alert-danger');
            		$('#alert_message2').text(data.message).show();
            		//$('#btnSubmit').prop('disabled', false).removeClass('disabled');
            		setInterval(function() {
            			$("#alert_message2").hide(5000);
            			if(data.success) {
    						$('#viewCertificationModal').modal('hide');
    						window.location.reload();
                		}
					}, 1000);
            		
				});
                return false; // avoid to execute the actual submit of the form.
            });
			
    			
            $('#btnDeny').click(function() {
    			//$.post('/controller/admin/approveApplication', { id: $('#certPKViewCertification').val(), ur_pk:'fd849bc3-0386-4baf-a558-2804e782d070'}, function (data) {

                	$.post('/controller/admin/denyApplication', { id: $('#certPKViewCertification').val(), ur_pk:$('#userPKViewCertification').val()}, function (data) {
                		$('#alert_message2').removeClass('alert-success alert-danger');
                		$('#alert_message2').addClass(data.success ? 'alert-success' : 'alert-danger');
                		$('#alert_message2').text(data.message).show();
                		//$('#btnSubmit').prop('disabled', false).removeClass('disabled');
                		setInterval(function() {
                			$("#alert_message2").hide(5000);
                			if(data.success) {
        						$('#viewCertificationModal').modal('hide');
        						window.location.reload();
                    		}
    					}, 1000);
                		
    				});
                    return false; // avoid to execute the actual submit of the form.
                });


            $('#changePasswordForm').unbind('submit');
            
            $('#btnSubmit').click(function() {
            	$.post('/controller/admin/changePassword', { id: $('#userPkChangePassword').val(), password: $('#password').val() }, function (data) {
            		$('#alert_message').removeClass('alert-success alert-danger');
            		$('#alert_message').addClass(data.success ? 'alert-success' : 'alert-danger');
            		$('#alert_message').text(data.message).show();
            		//$('#btnSubmit').prop('disabled', false).removeClass('disabled');
            		setInterval(function() {
            			$("#alert_message").hide(5000);
					}, 1000);
            		if(data.success) {
	            		$('#changePasswordForm').data('formValidation').resetForm();
	            		$('#password').val('');
            		}
				});
                return false; // avoid to execute the actual submit of the form.
            });
            
        </script>
</body>
</html>