<!DOCTYPE HTML>
<html lang="en">

<%@ include file = "./../header.jsp" %>

<body>

<div class="container">
	<div id="topbar"> <%@ include file = "./../top_bar.jsp" %></div>
	<div id="menubar"> <%@ include file = "./../menu_bar.jsp" %></div>
	
			
			<h2 class="text-info">Input Data Point</h2>
			<p>&nbsp;</p>
	

			<form:form>
				<input class="form-control" id="test" value="" />
				
				<script>
				$('#test').inputpicker({
				    url: 'getMaterials',
				    fields:['id','shortDesc'],
				    fieldText : 'shortDesc',
				    fieldValue : 'id',
				    headShow: true,
				    filterOpen: true,
				    autoOpen: true
				});
				</script>				</script>			
			</form:form>
	


	
	<div id="footerbar"> <p>&nbsp;</p> <%@ include file = "./../footer_bar.jsp" %></div>	
</div>  




</body> 