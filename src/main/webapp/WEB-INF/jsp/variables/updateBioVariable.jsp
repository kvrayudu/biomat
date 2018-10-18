<!DOCTYPE HTML>
<html lang="en">

<%@ include file = "./../header.jsp" %>

<body>

<div class="container">
	<div id="topbar"> <%@ include file = "./../top_bar.jsp" %></div>
	<div id="menubar"> <%@ include file = "./../menu_bar.jsp" %></div>
	
		
		<p>&nbsp;</p>	
		<h5 class="text-info">Update Bio-Variable - ${bioVariable.id} - ${bioVariable.name} - Last Updated By: ${bioVariable.updatedBy} - Last Updated at: ${bioVariable.updatedAt}</h2>
		<h5 class="text-success">${message}</h2>
		<p>&nbsp;</p>


		<form:form  class="form-horizontal" action="./updateBioVariable"  method="post"  modelAttribute="bioVariable">
			<form:hidden  value= "${bioVariable.id}" path="id"/>
			
		    <div class="form-group">
		    	<form:label class="text-info col-sm-2" path = "Name">Name</form:label>
			    <div class="col-sm-10"> 
			      <form:input class="form-control" path = "name"  value ="${bioVariable.name}" />
			      <form:errors  class="text-danger"  path="name" />
			    </div>
		   </div>
  
		    <div class="form-group">
		    	<form:label class="text-info col-sm-2" path = "description">Desc</form:label>
			    <div class="col-sm-10"> 
			      <form:input class="form-control" path = "description"  value ="${bioVariable.description}" />
			    </div>
		   </div>
		    
		   <div class="form-group">
		    	<form:label class="text-info col-sm-2" path = "siUnit">SI Unit</form:label>
			    <div class="col-sm-10"> 
			      <form:input class="form-control" path = "siUnit"  value ="${bioVariable.siUnit}" />
			    </div>
		   </div>
		   
		   <div class="form-group">
		    	<form:label class="text-info col-sm-2" path = "isFactor">Is Factor?</form:label>
			    <div class="col-sm-10">
			    <form:select class="form-control" id="isFactor" path="isFactor">
			    	<c:choose>
			    		<c:when test="${bioVariable.isFactor eq  1 }">
				    		<option value="1" SELECTED>Yes</option>
	    					<option value ="0">No</option>
    					</c:when>
			    		<c:otherwise>
				    		<option value="1" >Yes</option>
	    					<option value ="0" SELECTED>No</option>
    					</c:otherwise>
    					
    				</c:choose>
				 </form:select>
			     
			      
			    </div>
		   </div>
		   
		   
			<button type="submit" class="btn btn-info">Update</button>
		
		  </form:form>	
	


	
	<div id="footerbar"> <p>&nbsp;</p> <%@ include file = "./../footer_bar.jsp" %></div>	
</div>  




</body> 