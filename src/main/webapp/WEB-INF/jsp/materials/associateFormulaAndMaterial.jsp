<!DOCTYPE HTML>

<html lang="en">

<%@ include file = "./../header.jsp" %>
<script src="js/biomaterial.formula.material.association.js"></script>

<body>

<div class="container">
	<div id="topbar"> <%@ include file = "./../top_bar.jsp" %></div>
	<div id="menubar"> <%@ include file = "./../menu_bar.jsp" %></div>
	<h2 class="text-info">Associate Formula to Material(s)</h2>
	<p class="text-danger">${editBioFormulaForm.errorMessage }</p>
	
	<br>


	<form:form  action="updateFormulaMaterialAssociation"  method="post"  modelAttribute ="editBioFormulaForm"  id ="formulaMaterialAssociationForm">
		<form:hidden id="userAction" path="userAction" /> 
		<form:hidden id="selectedBioMaterialId" path="selectedBioMaterialId" />
		<div class="form-group row">
			<div class="col-sm-12">
				<form:input class="form-control" id="selectedFormulaId" path="selectedFormulaId"  placeholder="Please pick a Formula"/>
			</div>
		</div>

	<c:choose>
		<c:when test="${empty editBioFormulaForm.bioMaterials}">
			<c:if test="${not empty editBioFormulaForm.selectedFormulaId}">
				<p class="text-warning">There are no Materials Associated with the selected Formula.</p>
			</c:if>
		</c:when>
		<c:otherwise>
			<h4 class="text-info">Associated Materials for the Formula</h4>
				<table class="table table-hover table-striped">
				    <thead>
				      <tr>
				      	<th>Material Id</th>
				        <th>Material  Name</th>
				        <th>Short Desc</th>
				      </tr>
				    </thead>
				    <tbody>
						<c:forEach var="bioMaterial" items="${editBioFormulaForm.bioMaterials}">
							<tr>
	        					<td>${bioMaterial.id}</td>
	        					<td>${bioMaterial.commonName}</td>
	        					<td>${bioMaterial.shortDesc}</td>
	        					<td>
	        						<button class="btn btn-info" id= "deleteMaterialFromFormula" value = "${bioMaterial.id}">Delete</button>
	        					</td>
	   						</tr>	
	
						</c:forEach>
					</tbody>
	
				</table>
		
		</c:otherwise>
	</c:choose>


			
			
			
			
			<hr size="10"/>
			<c:if test="${not empty editBioFormulaForm.selectedFormulaId}">
         		<div class="form-group row">
					<div class="col-sm-10">
						<input class="form-control" id="selectedMaterialId" path="selectedMaterialId"   placeholder="Type Material Name to Pick and add to the Formula"/>
					</div>
					<div class="col-sm-2">
						<button type="button" class="btn btn-info" id="addMaterialToFormula">Add Material</button>
					</div>
					
			</div>	
			</c:if>
				

	</form:form>	         

	<div id="footerbar"> <p>&nbsp;</p> <%@ include file = "./../footer_bar.jsp" %></div>	
</div>  

</body> 