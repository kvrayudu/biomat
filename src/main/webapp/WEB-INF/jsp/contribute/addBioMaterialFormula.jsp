<!DOCTYPE HTML>
<html lang="en">

<%@ include file = "./../header.jsp" %>
 <script src="js/biomaterial.dynamic.measurement.input.js"></script>

<body>

	<div class="container">
		<div id="topbar"> <%@ include file = "./../top_bar.jsp" %></div>
		<div id="menubar"> <%@ include file = "./../menu_bar.jsp" %></div>
		
				
				<h2 class="text-info">Add Your Bio-Material Formula</h2>
				<h5 class="text-success">${successMessage}</h5>
				
				<p>&nbsp;</p>
				

				<form:form  class="form-horizontal" action="addBioMaterialFormula"  method="post"  modelAttribute="bioFormulaForm">

					<div class="form-group">
				    	<form:label class="text-info col-sm-2" path = "name">Name Your Formula:</form:label>
					    <div class="col-sm-10"> 
				      		<form:input class="form-control" path = "name"  value ="${bioFormulaForm.name}" />
				      		<form:errors  class="text-danger"  path="name" />
					    </div>
			   		</div>
		   		
					<div class="form-group">
				    	<form:label class="text-info col-sm-2" path = "formula">Formula:</form:label>
					    <div class="col-sm-10"> 
				      		<form:input class="form-control" path = "formula"  value ="${bioFormulaForm.formula}" />
				      		<form:errors  class="text-danger"  path="formula" />
					    </div>
			   		</div>
										
					<h6><small>${bioFormulaForm.formula}</small></h6>
					
					<div class="form-group">
				    	<form:label class="text-info col-sm-2" path = "minRange">Min Range:</form:label>
					    <div class="col-sm-10"> 
				      		<form:input class="form-control" path = "minRange"  value ="${bioFormulaForm.minRange}" />
				      		<form:errors  class="text-danger"  path="minRange" />
					    </div>
			   		</div>
					
					<div class="form-group">
				    	<form:label class="text-info col-sm-2" path = "maxRange">Max Range:</form:label>
					    <div class="col-sm-10"> 
				      		<form:input class="form-control" path = "maxRange"  value ="${bioFormulaForm.maxRange}" />
				      		<form:errors  class="text-danger"  path="maxRange" />
					    </div>
			   		</div>
			   		
			   		
			   		<div class="form-group">
	    				<form:label class="text-info col-sm-2" path = "citation">Citation</form:label>
		    			<div class="col-sm-10"> 
					    	<form:textarea class="form-control" path = "citation"  value ="${bioFormulaForm.citation}" />
					    	<form:errors  class="text-danger"  path="citation" />
					    </div>
		   			</div>
			   		
			   		<div class="form-group">
	    				<form:label class="text-info col-sm-2" path = "doi">DOI</form:label>
		    			<div class="col-sm-10"> 
					    	<form:textarea class="form-control" path = "doi"  value ="${bioFormulaForm.doi}" />
					    	<form:errors  class="text-danger"  path="doi" />
					    </div>
		   			</div>
		   			
					<button type="submit" class="btn btn-info">Save Formula</button>

				
				</form:form>					
				
				
		<div id="footerbar"> <p>&nbsp;</p> <%@ include file = "./../footer_bar.jsp" %></div>	
	</div>  
 

</body> 