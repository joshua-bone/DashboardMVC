<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script> -->

<script>
function goBack() {
    window.history.back()
}
</script>

</head>
<body>

<div data-role="page" id="pageone">
  <div data-role="header">
    <h1>Simple Item View</h1>
  </div>

  <div data-role="main" class="ui-content">
    <h2><c:out value="${manager.currentItem.name}"/></h2>
    <hr>
     <input type="button" value="Back" onclick="location.href='viewIndexCard.do'"/>  
     <input type="button" value="Edit" onclick="location.href='editItemView.do'"/>
     <input type="button" value="Delete" onclick="location.href='deleteItem.do'"/> 
    
    <c:if test="${not empty manager.currentItem.parents}">
	    <hr>
	    Child of:
	    <c:forEach var="card" items="${manager.currentItem.parents}">
	    		<a href="viewIndexCard.do?cid=<c:out value="${card.customID}"/>"><c:out value="${card.name}"/></a>
	    </c:forEach> 
    </c:if>
    <hr>
   </div>

  <div data-role="footer">
    <h1>Insert Footer Text Here</h1>
  </div>
</div>

</body>
</html>