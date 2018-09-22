<!DOCTYPE HTML>
<html lang="en">

<%@ include file = "./../header.jsp" %>
 <script src="js/biomaterial.dynamic.measurement.input.js"></script>

<body>

<div class="container">
	<div id="topbar"> <%@ include file = "./../top_bar.jsp" %></div>
	<div id="menubar"> <%@ include file = "./../menu_bar.jsp" %></div>
	
			
			<h2 class="text-info">Input Data Point</h2>
			<h5 class="text-success">${successMessage}</h5>
			<p>&nbsp;</p>
			<form:form  action="/addPointInput"  method="post"  modelAttribute="bioMeasurementForm" >
			<c:choose>
				<c:when test="${empty successMessage}">
					<div class="form-group row">
						<div class="col-sm-2">
							<form:label  class="text-info" path="materialId" >Select Material: </form:label>
						</div>
						<div class="col-sm-5">
							<form:input class="form-control" id="materialId" path="materialId"   placeholder="Please enter bio-material name"/>
							<form:errors  class="text-danger"  path="materialId" />
						</div>
					</div>	
	
	
	
					<div class="form-group row">
						<div class="col-sm-2">
							<form:label class="text-info" path="variableId" >Select Variable/Factor: </form:label>
						</div>
						<div class="col-sm-5">
							<form:input class="form-control" id="variableId" path="variableId"   placeholder="Please enter variable name"/>
							<form:errors  class="text-danger"  path="variableId" />
						</div>
					</div>
					
					<div class="form-group row">
						<div class="col-sm-2">
							<form:label class="text-info" path="citation" >Citation:</form:label>
						</div>
						<div class="col-sm-8">
							<form:textarea class="form-control" id="citation" path="citation" value = "${bioMeasurementForm.citation}"  placeholder="Please enter citation"/>
							<form:errors  class="text-danger"  path="citation" /> 
						</div>
					</div>	
					
						
					<div class="form-group row">
						<div class="col-sm-2">
							<form:label class="text-info" path="doi" >DOI: </form:label>
						</div>
						<div class="col-sm-8">
							<form:textarea class="form-control" id="doi" path="doi"  value = "${bioMeasurementForm.doi}" placeholder="Please enter DIO"/>
							<form:errors  class="text-danger"  path="doi" /> 
						</div>
					</div>
					
					<c:forEach  items="${bioMeasurementForm.measurementPairs}" varStatus="status">	
						<div class="form-group row">
						
							<div class="col-sm-2">
								&nbsp;
							</div>
							<div class="col-sm-2">
								<form:label class="text-info" path="measurementPairs[${status.index }].measurementValue"  >Measurement Value</form:label>
							</div>
							
							<div class="col-sm-2">
								<form:label class="text-info" path="measurementPairs[${status.index }].errorValue"  >Error Value</form:label>
							</div>
							<div class="col-sm-6">
								&nbsp;
							</div>
							
						</div>
						
						<div class="form-group row">
							<div class="col-sm-2">
								&nbsp;
							</div>
							<div class="col-sm-2">
								<form:input class="form-control" id="measurementPairs[${status.index }].measurementValue" path="measurementPairs[${status.index }].measurementValue"   placeholder="Enter Measurement Value"/>
							</div>
							<div class="col-sm-2">
								<form:input class="form-control" id="measurementPairs[${status.index }].errorValue" path="measurementPairs[${status.index }].errorValue"   placeholder="Enter Error Value"/>
							</div>
							<div class="col-sm-6">
								&nbsp;
							</div>
						</div>
						
					</c:forEach>
	
					
			        <div id="field0"></div>
			        <div class="form-group row">
		  				<div class="col-sm-offset-2 col-sm-4">
		     				<button  id="add-more" name="add-more" class="btn btn-primary">Add Another Measurement</button>
		     				<button  type="submit" class="btn btn-info btn-default">Save Point Input</button>
		   				</div>
		    		</div>
			</c:when>
			<c:otherwise>
			
							
					<div class="form-group row">
						<div class="col-sm-2">
							<form:label  class="text-info" path="materialId" >Select Material: </form:label>
						</div>
						<div class="col-sm-10">
							 ${bioMeasurementForm.variableId} 
						</div>
						
					</div>	
	
	
	
					<div class="form-group row">
						<div class="col-sm-2">
							<form:label class="text-info" path="variableId" >Select Variable/Factor:  </form:label>
						</div>
						<div class="col-sm-10">
							 ${bioMeasurementForm.variableId} 
						</div>
						
					</div>
					
					<div class="form-group row">
						<div class="col-sm-2">
							<form:label class="text-info" path="citation" >Citation: </form:label>
						</div>
						<div class="col-sm-8">
							<form:textarea disabled="true" class="form-control" id="citation" path="citation" value = "${bioMeasurementForm.citation}"  placeholder="Please enter citation"/>
						</div>
					</div>	
					
						
					<div class="form-group row">
						<div class="col-sm-2">
							<form:label class="text-info" path="doi" >DOI: </form:label>
						</div>
						<div class="col-sm-8">
							<form:textarea disabled = "true" class="form-control" id="doi" path="doi"  value = "${bioMeasurementForm.doi}" placeholder="Please enter DIO"/>
						</div>
					</div>
					
					<c:forEach  items="${bioMeasurementForm.measurementPairs}" varStatus="status">	
						<div class="form-group row">
						
							<div class="col-sm-2">
								&nbsp;
							</div>
							<div class="col-sm-2">
								<form:label class="text-info" path="measurementPairs[${status.index }].measurementValue"  >Measurement Value</form:label>
							</div>
							
							<div class="col-sm-2">
								<form:label class="text-info" path="measurementPairs[${status.index }].errorValue"  >Error Value</form:label>
							</div>
							<div class="col-sm-6">
								&nbsp;
							</div>
							
						</div>
						
						<div class="form-group row">
							<div class="col-sm-2">
								&nbsp;
							</div>
							<div class="col-sm-2">
								<form:input disabled = "true" class="form-control" id="measurementPairs[${status.index }].measurementValue" path="measurementPairs[${status.index }].measurementValue"   placeholder="Enter Measurement Value"/>
							</div>
							<div class="col-sm-2">
								<form:input disabled = "true" class="form-control" id="measurementPairs[${status.index }].errorValue" path="measurementPairs[${status.index }].errorValue"   placeholder="Enter Error Value"/>
							</div>
							<div class="col-sm-6">
								&nbsp;
							</div>
						</div>
						
					</c:forEach>
			
			
			
			
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


$('#variableId').inputpicker({
    url: 'getVariables',
    fields:['id','name', 'isFactor'],
    fieldText : 'name',
    fieldValue : 'id',
    headShow: true,
    filterOpen: true,
    autoOpen: true
});

</script>

</body> 