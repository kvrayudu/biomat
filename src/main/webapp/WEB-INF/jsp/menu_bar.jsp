<!-- NAV Start -->  
<nav>
   <ul class="nav nav-tabs" role="tablist">
	<li><a href="home">Home</a></li>
	
    <li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown">Contribute Data<span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu">
			<li><a href="addPointInput">Add Data Point</a></li>
			<li><a href="addBioMaterials">Add Formula</a></li>
			<li><a href="addBioMaterials">Add Range</a></li>
	   </ul>
	</li>

	
    <li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown">Bio-Materials<span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu">
			<li><a href="searchBioMaterials">Search Bio-Materials</a></li>
			<li><a href="addBioMaterials">Add Bio-Material</a></li>
	   </ul>
	</li>

    <li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown">Bio-Variables<span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu">
			<li><a href="searchBioVariables">Search Bio-Variables</a></li>
			<li><a href="addBioVariables">Add Bio-Variable</a></li>
	   </ul>
	</li>
	
	<li><a href="home">Groups</a></li>
        <li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown">xxx<span class="caret"></span></a>
			  <ul class="dropdown-menu" role="menu">
				<li><a href="BookingLookup.html">Bookings Lookup</a></li>
			                       
			  </ul>
		</li>
		<c:choose>
			<c:when test ="${empty pageContext.request.remoteUser}">
				<li><a href="login">Login</a></li>		
			</c:when>
			<c:otherwise>
				<li><a href="login">Logout</a></li>
			</c:otherwise>
		</c:choose>
      </ul>
</nav>
<div id="welcomeBar"> 
		<c:choose>
			<c:when test ="${empty pageContext.request.remoteUser}">
				<div class="col-sm-12 text-primary text-right">
					Welcome Guest
				</div>			
			</c:when>
			<c:otherwise>
				<div class="col-sm-12 text-primary text-right">
					Welcome ${pageContext.request.remoteUser}
				</div>			
			</c:otherwise>
		</c:choose>
</div>
