<!DOCTYPE HTML>
<html lang="en">

<%@ include file = "header.jsp" %>

<body>

<div class="container">
	<div id="topbar"> <%@ include file = "top_bar.jsp" %></div>
	<div id="menubar"> <%@ include file = "menu_bar.jsp" %></div>
	
		<c:if test="${param.error}">
            Invalid username and password.
        </c:if>
        <c:if test="${param.logout}">
            You have been logged out.
        </c:if>
        
        <form:form  action="/login"  method="post"   >
          	 User Name:   <input class="form-control"  name="username"   placeholder="Please Enter User Name"/>
             Password:    <input type = "password" class="form-control"  name="password"   placeholder="Please Enter Password"/>
           
             <button type="submit" class="btn btn-info">Sign In</button>
        </form:form>
	
	
	<div id="footerbar"> <p>&nbsp;</p> <%@ include file = "footer_bar.jsp" %></div>	
</div>  




</body> 