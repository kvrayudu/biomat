<!DOCTYPE HTML>
<html lang="en">

<%@ include file = "./../header.jsp" %>
 <script src="js/biomaterial.dynamic.measurement.input.js"></script>

<body>

<div class="container">
	<div id="topbar"> <%@ include file = "./../top_bar.jsp" %></div>
	<div id="menubar"> <%@ include file = "./../menu_bar.jsp" %></div>
	
			
			<h2 class="text-info">Add Discreet Dataset</h2>
			<h5 class="text-success">${successMessage}</h5>
			
			<p>&nbsp;</p>
			
			<form:form  action="addDiscreetData"  method="post"  modelAttribute="bioDiscreetData" >
			<c:choose>
				<c:when test="${empty successMessage}">
				<div class="form-group row">
					<div class="col-sm-2">
						<form:label  class="text-info" path="materialId" >Select Material: </form:label>
					</div>
					<div class="col-sm-10">
						<form:input class="form-control" id="materialId" path="materialId"   placeholder="Please enter bio-material name"/>
						<form:errors  class="text-danger"  path="materialId" />
					</div>
				</div>	
				<div class="form-group row">
					<div class="col-sm-2">
						<form:label  class="text-info" path="authorName" >Author Name: </form:label>
					</div>
					<div class="col-sm-10">
						<form:input class="form-control" id="authorName" path="authorName"   placeholder="Author Name"/>
						<form:errors  class="text-danger"  path="authorName" />
					</div>
				</div>	
				
				<div class="form-group row">
					<div class="col-sm-2">
						<form:label  class="text-info" path="year" >Publish Year: </form:label>
					</div>
					<div class="col-sm-10">
						<form:input class="form-control" id="year" path="year"   placeholder="Publish Year"/>
						<form:errors  class="text-danger"  path="year" />
					</div>
				</div>	
				
				   <p>&nbsp;</p>
			        <div class="form-group row">
		  				<div class="col-sm-offset-4 col-sm-2">
		     				<button  type="submit" class="btn btn-info btn-default">Save Discreet DataSet</button>
		   				</div>
		    		</div>
		    		</c:when>
		    		<c:otherwise>
		    			
		    		</c:otherwise>
		    		</c:choose>
		</form:form>
	
	<p>&nbsp;</p>
	
	<div id="footerbar"> <p>&nbsp;</p> <%@ include file = "./../footer_bar.jsp" %></div>	
</div>  
 
<script>
$('#materialId').inputpicker({
    url: 'getMaterials',
    fields:['id','shortDesc'],
    fieldText : 'shortDesc',
    fieldValue : 'id',
    headShow: true,
    filterOpen: true,
    autoOpen: true
});


$('#yVariableId').inputpicker({
    url: 'getVariables',
    fields:['id','name'],
    fieldText : 'name',
    fieldValue : 'id',
    headShow: true,
    filterOpen: true,
    autoOpen: true
});

$('#xVariableId').inputpicker({
    url: 'getVariables',
    fields:['id','name'],
    fieldText : 'name',
    fieldValue : 'id',
    headShow: true,
    filterOpen: true,
    autoOpen: true
});


</script>

</body> 