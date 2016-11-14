<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
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

<!-- Body -->

<body>
	<div class="container-fluid">	
				<div class="jumbotron">
				<h2>Your Index Cards</h2>
				<button type="button" class="btn btn-sm btn-primary" onclick="location.href = 'newIndexCardTemplate.do'">
				Create a new card
				</button>
				</div>	
				<div>
				<c:forEach var="card" items="${manager.cardSet}">
					<c:if test="${empty card.parents}">
					 	<myTags:cardHierarchy card="${card}" />
					</c:if>	
				</c:forEach>
				</div>
	</div>
</body>
</html>