<!DOCTYPE HTML>
<html lang="en">

<%@ include file = "./../header.jsp" %>

<body>

<div class="container">
	<div id="topbar"> <%@ include file = "./../top_bar.jsp" %></div>
	<div id="menubar"> <%@ include file = "./../menu_bar.jsp" %></div>
	
		
		<p>&nbsp;</p>	
		<h5 class="text-info">Update Bio-Material - ${bioMaterial.id} - ${bioMaterial.shortDesc} - Last Updated By: ${bioMaterial.updatedBy} - Last Updated at: ${bioMaterial.updatedAt}</h2>
		<h5 class="text-success">${successMessage}</h5>
		<p>&nbsp;</p>


		<form:form  class="form-horizontal" action="updateBioMaterial"  method="post"  modelAttribute="bioMaterial">
			<form:hidden  value= "${bioMaterial.id}" path="id"/>
			<form:hidden  value= "${bioMaterial.usdaId}" path="usdaId"/>
		    <div class="form-group">
		    	<form:label class="text-info col-sm-2" path = "shortDesc">Short Desc</form:label>
			    <div class="col-sm-10"> 
			      <form:input class="form-control" path = "shortDesc"  value ="${bioMaterial.shortDesc}" />
			      <form:errors  class="text-danger"  path="shortDesc" />
			    </div>
		   </div>
  
		    <div class="form-group">
		    	<form:label class="text-info col-sm-2" path = "longDesc">Long Desc</form:label>
			    <div class="col-sm-10"> 
			      <form:input class="form-control" path = "longDesc"  value ="${bioMaterial.longDesc}" />
			    </div>
		   </div>
		    
		   <div class="form-group">
		    	<form:label class="text-info col-sm-2" path = "commonName">Common Name</form:label>
			    <div class="col-sm-10"> 
			      <form:input class="form-control" path = "commonName"  value ="${bioMaterial.commonName}" />
			    </div>
		   </div>
		   
		   <div class="form-group">
		    	<form:label class="text-info col-sm-2" path = "mfgName">Mfg Name</form:label>
			    <div class="col-sm-10"> 
			      <form:input class="form-control" path = "mfgName"  value ="${bioMaterial.mfgName}" />
			    </div>
		   </div>
		   
		   <div class="form-group">
		    	<form:label class="text-info col-sm-2" path = "refuseDesc">Refuse Desc</form:label>
			    <div class="col-sm-10"> 
			      <form:input class="form-control" path = "refuseDesc"  value ="${bioMaterial.refuseDesc}" />
			    </div>
		   </div>
		   
		   <div class="form-group">
		    	<form:label class="text-info col-sm-2" path = "refusePercentage">Refuse %</form:label>
			    <div class="col-sm-10"> 
			      <form:input class="form-control" onkeypress="return isNumericKey(event)"  path = "refusePercentage"  value ="${bioMaterial.refusePercentage}" />
			      <form:errors  class="text-danger"  path="refusePercentage" />
			    </div>
		   </div>
		   

		   <div class="form-group">
		    	<form:label class="text-info col-sm-2" path = "scientificName">Scientific Name</form:label>
			    <div class="col-sm-10"> 
			      <form:input class="form-control" path = "scientificName"  value ="${bioMaterial.scientificName}" />
			    </div>
		   </div>
		   
		   <div class="form-group">
		    	<form:label class="text-info col-sm-2" path = "citation">Citation</form:label>
			    <div class="col-sm-10"> 
			      <form:textarea class="form-control" path = "citation"  value ="${bioMaterial.citation}" />
			    </div>
		   </div>
		   <div class="form-group">
		   		<form:label class="text-info col-sm-3" path = "pFactor">&nbsp;</form:label>
		    	<form:label class="text-info col-sm-1" path = "nFactor">nFactor</form:label>
			    <div class="col-sm-1"> 
			      <form:input class="form-control" path = "nFactor"  value ="${bioMaterial.nFactor}" />
			      <form:errors  class="text-danger"  path="nFactor" />
			    </div>
		    	<form:label class="text-info col-sm-1" path = "pFactor">pFactor</form:label>
			    <div class="col-sm-1"> 
			      <form:input class="form-control" path = "pFactor"  value ="${bioMaterial.pFactor}" />
			      <form:errors  class="text-danger"  path="pFactor" />
			    </div>
		    	<form:label class="text-info col-sm-1" path = "pFactor">choFactor</form:label>
			    <div class="col-sm-1"> 
			      <form:input class="form-control" path = "choFactor"  value ="${bioMaterial.choFactor}" />
			      <form:errors  class="text-danger"  path="choFactor" />
			    </div>
		    	<form:label class="text-info col-sm-3" path = "pFactor">&nbsp;</form:label>
			    
		   </div>
		   
			<button type="submit" class="btn btn-info">Update</button>
		
		  </form:form>	
	


	
	<div id="footerbar"> <p>&nbsp;</p> <%@ include file = "./../footer_bar.jsp" %></div>	
</div>  




</body> 