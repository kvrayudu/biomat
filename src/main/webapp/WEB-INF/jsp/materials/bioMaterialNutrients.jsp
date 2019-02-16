<!DOCTYPE HTML>
<html lang="en">

<%@ include file = "./../header.jsp" %>
 
 <script src="js/biomaterial.dynamic.select.list.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.min.js"></script>
 
 
<body>

<div class="container">
	<div id="topbar"> <%@ include file = "./../top_bar.jsp" %></div>
	<div id="menubar"> <%@ include file = "./../menu_bar.jsp" %></div>
	<h2 class="text-info">Bio-Material Variable Values</h2>
	
	
	<form:form  action="bioMaterialNutrients"  method="post"  modelAttribute ="bioMaterialNutrientForm" >
	
		<div class="form-group row">
			<div class="col-sm-9">
				<form:input class="form-control" id="selectedBioMaterialId" path="selectedBioMaterialId"  placeholder="Please enter bio-material name"/>
				<form:errors  class="text-danger"  path="selectedBioMaterialId" />
			</div>
			<div class="col-sm-1">
				<button id="bio-material-nutrients" name="bio-material-nutrients" class="btn btn-info">Variable Values</button>
			</div>
		</div>
		
			<h4 class="text-info">Variable Values for Material: ${bioMaterialNutrientForm.bioMaterialNutrientList[0].bioMaterial.shortDesc } </h4>
			<table class="table table-hover table-striped">
			    <thead>
			      <tr>
			      	<th>Variable Id</th>
			        <th>Variable  Name</th>
			        <th>Symbol</th>
			        <th>Value</ths>
			        <th>Min. Value</th>
			        <th>max. Value</th>
			      </tr>
			    </thead>
			    <tbody>
					<c:forEach var="bioMaterialNutrient" items="${bioMaterialNutrientForm.bioMaterialNutrientList}">
						<tr>
        					<td>${bioMaterialNutrient.bioVariable.id}</td>
        					<td>${bioMaterialNutrient.bioVariable.name}</td>
        					<td>${bioMaterialNutrient.bioVariable.symbol}</td>
        					<td>${bioMaterialNutrient.nutrientValue}</td>
        					<td>${bioMaterialNutrient.minValue}</td>
        					<td>${bioMaterialNutrient.maxValue}</td>
        				</tr>	
							
					</c:forEach>
				</tbody>
					
			</table>	
			
		
	
	
	</form:form>
	
	
	
	
	<div id="footerbar"> <p>&nbsp;</p> <%@ include file = "./../footer_bar.jsp" %></div>	
</div>  
 
	<script>
	$('#selectedBioMaterialId').inputpicker({
	    url: 'getMaterials',
	    fields:['id','shortDesc'],
	    fieldText : 'shortDesc',
	    fieldValue : 'id',
	    headShow: true,
	    filterOpen: true,
	    autoOpen: true
	});
	</script>
</body>