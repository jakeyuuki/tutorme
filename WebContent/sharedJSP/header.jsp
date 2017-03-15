<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>	
<!--   taglib uri="http://ckeditor.com" prefix="ckeditor"  -->
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
				<a class="navbar-brand">
					<img class="clickable" src="/assets/img/logo.png"/>
				</a>
			</div>
			<!-- Menu -->
			<div id="bs-example-navbar-collapse-1" class="collapse navbar-collapse">
			<form name="logoutform" action="/login" method="post">
				<input type="hidden" name="action" value="signout"/>
				<ul id="menu-top" class="nav navbar-nav navbar-right">
					<li class="${pageContext.request.requestURI == '/' || pageContext.request.requestURI == '/index.jsp' ? ' active' : ''}">
						<a href="/">Home</a>
					</li>
					<li class="${pageContext.request.requestURI == '/tutors.jsp' || pageContext.request.requestURI == '/indirectAccessiblePages/tutor.jsp' ? ' active' : ''}">
						<a href="/tutors.jsp">Tutors</a>
					</li>
					<li class="${pageContext.request.requestURI == '/courses.jsp' ? ' active' : ''}">
						<a href="/courses.jsp">Courses</a>
					</li>
					<% if(session.getAttribute("pk") == null) { %>
					<li class="${pageContext.request.requestURI == '/myAccount.jsp' || pageContext.request.requestURI == '/registerAction.jsp' || pageContext.request.requestURI == '/activationActivated.jsp' || pageContext.request.requestURI == '/activationSuccess.jsp' ? ' active' : ''}">
						<a href="/myAccount.jsp">My Account</a>
					</li>
					<% } else {	%>
					<!--<li class="${pageContext.request.requestURI == '/polos.jsp' ? ' active' : ''}">
						<a href="/bookedCourses.jsp"><i class="fa fa-book"></i> On Progress(3)</a>
					</li>-->
					<li class="dropdown${pageContext.request.requestURI == '/useraccount.jsp' || pageContext.request.requestURI == '/adminPages/' || pageContext.request.requestURI == '/adminPages/index.jsp' || pageContext.request.requestURI == '/inactivatedUser.jsp' || pageContext.request.requestURI == '/tutorManagement.jsp' ? ' active' : ''}">
						<a href="#"><%= session.getAttribute("fullname") %> <span class="fa fa-caret-square-o-down"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/myProfile" class="text-right">User Account</a></li>
							<% if(Boolean.TRUE.equals(session.getAttribute("isTutor"))) { %>
							<li><a href="/tutorProfile" class="text-right">Tutor Management</a></li>
							<li><a href="/courseManagement.jsp" class="text-right">Courses Management</a></li>
							<% } else {	%>
							<li><a href="/tutorApplication" class="text-right">Apply as Tutor</a></li>
							<% } %>														
							<% if(Boolean.TRUE.equals(session.getAttribute("isAdmin"))) { %>
							<li><a href="/adminPages/" class="text-right">Admin Management</a></li>
							<% }%>
							<!-- <li><a href="icons.html" class="text-right">Contact TutorMe</a></li>-->
							<li><a onClick="document.forms['logoutform'].submit();" href="#" class="text-right"><span class="fa fa-sign-out"></span> Logout</a></li>
						</ul>
					</li>
					<% } %>
				</ul>
			</form>
			</div>
		</div>
	</nav>