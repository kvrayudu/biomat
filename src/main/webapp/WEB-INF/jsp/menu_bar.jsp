
<!-- NAV Start -->  
<nav>
   <ul class="nav nav-tabs" role="tablist">
	<li><a href="home">Home</a></li>
	
	<li><li><a href="searchBioMaterials">Bio-Materials</a></li></li>
	<li><li><a href="bioMaterialNutrients">Bio-Material Nutrients</a></li></li>
	<li><li><a href="bioObservedPoints">Observed Points</a></li></li>
	<li><li><a href="graphBioMaterial">Chart Formula</a></li></li>
	
    <li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown">Bio-Variables<span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu">
			<li><a href="searchBioVariables">Search Bio-Variables</a></li>
			<li><a href="addBioVariables">Add Bio-Variable</a></li>
	   </ul>
	</li>
	

	
	<c:choose>
		<c:when test ="${userRole eq 'ADMIN'}">	
		    <li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown">Admin<span class="caret"></span></a>
				 <ul class="dropdown-menu" role="menu">
					<li><a href="s.html">Manage Materials</a></li>
					<li><a href="s.html">Manage Variables</a></li>
					<li><a href="s.html">Manage Formula</a></li>
					<li><a href="s.html">Manage Input Points</a></li>
				  </ul>
			</li>
			</c:when>
			<c:when test ="${userRole eq 'CONTRIBUTOR'}">
			    <li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown">Contribute Data<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="addBioMaterial">Add Bio-Material</a></li>
						<li><a href="addPointInput">Add Data Point</a></li>
						<li><a href="addBioMaterialFormula">Add Formula</a></li>
						<li><a href="addBioMaterials">Add Range</a></li>
						<li><li><a href="editFormula">Edit Bio Formula</a></li></li>
				   </ul>
				</li>
				
			    <li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown">My Contributions<span class="caret"></span></a>
					 <ul class="dropdown-menu" role="menu">
					 	<li><a href="myPointInputs">View/Edit My Input Points</a></li>
						<li><a href="s.html">View/Edit My Materials</a></li>
						<li><a href="s.html">View/Edit My Variables</a></li>
						<li><a href="s.html">View/Edit My Formula</a></li>
					  </ul>
				</li>
		</c:when>
	</c:choose>
	
 </ul>
</nav>
<div id="welcomeBar"> 
		<c:choose>
			<c:when test ="${userRole eq 'Guest'}">
				<div class="col-sm-12 text-primary text-right">
					Welcome Guest &nbsp; <a href="login">Login</a>&nbsp;  <a href="signUp">Sign up</a>
				</div>			
			</c:when>
			<c:otherwise>
				<div class="col-sm-12 text-primary text-right">
					<form name="logoutForm" method="POST" action="logout">
    					Welcome ${pageContext.request.remoteUser}  (${userRole})&nbsp;<A HREF="javascript:document.logoutForm.submit()">Logout</A>
					</form>
				</div>			
			</c:otherwise>
		</c:choose>
		
</div>
