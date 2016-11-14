<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Local stylesheet -->
<link rel="stylesheet" type="text/css" href="stylesheet.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

  <div class="container-fluid">
    <button type="button" class="btn btn-info btn-sm" onclick="location.href='showIndexCards.do'">
        <span class="glyphicon glyphicon-chevron-left"></span>Go back to Main view
    </button> 
    	<myTags:cardHierarchy card="${manager.currentCard}" />
    	
    <c:if test="${not empty manager.currentCard.parents }">
	    <hr>
	    Child of:
	    <c:forEach var="card" items="${manager.currentCard.parents}">
	    		<a href="viewIndexCard.do?cid=<c:out value="${card.customID}"/>"><c:out value="${card.name}"/></a>
	    </c:forEach> 
    </c:if>
  </div>
  
</body>
</html>