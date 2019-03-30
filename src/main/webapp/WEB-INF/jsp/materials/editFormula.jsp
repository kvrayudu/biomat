<!DOCTYPE HTML>
<%@page import="java.net.http.HttpResponse"%>
<html lang="en">

<%@ include file = "./../header.jsp" %>

 <script src="js/biomaterial.dynamic.select.list.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.min.js"></script>


<body>

<div class="container">
	<div id="topbar"> <%@ include file = "./../top_bar.jsp" %></div>
	<div id="menubar"> <%@ include file = "./../menu_bar.jsp" %></div>
	<h2 class="text-info">Edit Bio Formula Name to Search</h2>


	<form:form  action="editFormula"  method="post"  modelAttribute ="editBioFormulaForm" onSubmit="populate()" id ="originalForm">

		<div class="form-group row">
			<div class="col-sm-9">
				<form:input class="form-control" id="selectedFormulaId" path="selectedFormulaId"  placeholder="Please enter formula name to edit"/>
				<form:errors  class="text-danger"  path="selectedFormulaId" />
				<form:hidden path="formulaName" id="formulaName" />
			</div>
			<div class="col-sm-1">
				<button id="search-material" name="search-material" class="btn btn-info">List Associated Materials</button>
			</div>
		</div>

		<h4 class="text-info">Associated Materials: ${editBioFormulaForm.formulaName}</h4>
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
        					<button class="btn btn-info" onClick=deleteAssociation(${bioMaterial.id})>Delete</button>
        					</td>
        				</tr>	

					</c:forEach>
				</tbody>

			</table>	
			<c:if test="${editBioFormulaForm.formulaName != null}">
				<button type="button" class="btn btn-info" id="addMaterial">Add Material</button>
			</c:if>

	</form:form>	         


	<!-- Modal -->
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog">

	      <!-- Modal content-->
	      <div class="modal-content">
	        <div class="modal-header" >
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4>Add Material</h4>
	        </div>
	        <div class="modal-body" style="padding:40px 50px;">

	          <form role="form"  action="addMaterialToFormula"  method="post" id = "addMForm">
	            <div class="form-group">
	            	<input type="text" id="formulaId" style="visibility: hidden;" name="formulaId" value=""/>
	              <input class="form-control" id="selectedBioMaterialId" name="selectedBioMaterialId" placeholder="Enter material to search">

	              <input type="submit" id="modalSubmit" class="btn btn-success btn-block" value="Add Material"/>
	             </div>
	          </form>

	        </div>
	        <div class="modal-footer">
	          <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
	        </div>
	      </div>

	    </div>
	  </div> 


	<div id="footerbar"> <p>&nbsp;</p> <%@ include file = "./../footer_bar.jsp" %></div>	
</div>  

	<script>
	$('#selectedFormulaId').inputpicker({
	    url: 'getFormula',
	    fields:['id','name'],
	    fieldText : 'name',
	    fieldValue : 'id',
	    headShow: true,
	    filterOpen: true,
	    autoOpen: true
	});
	
	$('#selectedBioMaterialId').inputpicker({
	    url: 'getMaterials',
	    fields:['id','shortDesc'],
	    fieldText : 'shortDesc',
	    fieldValue : 'id',
	    headShow: true,
	    filterOpen: true,
	    autoOpen: true
	});
	
	function populate(){
		document.getElementById('formulaName').value = document.getElementById('inputpicker-1').value;
	}
	
	
	$("#modalSubmit").on("click", function(e) {
	    e.preventDefault();
	    formulaId = document.getElementById('formulaId').value;
	    selectedBioMaterialId = document.getElementById('selectedBioMaterialId').value;
	    document.getElementById('addMForm').reset();
	    $('#myModal').modal('hide');
	    //post to addBioMaterialForm
	    var myKeyVals = { "formulaId" : formulaId, "selectedBioMaterialId" : selectedBioMaterialId}
	    var saveData = $.ajax({
	          type: 'POST',
	          url: "/addBioMaterialForm",
	          data: myKeyVals,
	          dataType: "text",
	          success: function(resultData) { alert("Save Complete. Please refresh list"); document.getElementById('originalForm').submit(); }
	    });
	    saveData.error(function() { alert("Something went wrong"); });
	    
	});
	
	
	function deleteAssociation(bioMaterialId){
		var formulaId = document.getElementById('selectedFormulaId').value;
		var source = event.target || event.srcElement;
		source.disabled = true;
		source.innerText= "Deleted"
		
		const url = "deleteBioMaterialFormula?formulaId="+formulaId+"&materialId="+bioMaterialId;
		fetch(url, {
		    method : "GET"
		}).then(
		    response => response.text() 
		).then(function(){
			document.getElementById('originalForm').submit();	
		});
		
	}
	
	$(document).ready(function(){
		  $("#addMaterial").click(function(){
		    $("#myModal").modal();
		    document.getElementById('formulaId').value = document.getElementById('selectedFormulaId').value;
		    document.getElementById('inputpicker-div-2').style.width = "100%";
		  });
		});
	
	</script>
</body> 