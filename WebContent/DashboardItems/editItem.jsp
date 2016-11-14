<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Edit Item</title>
<link rel="stylesheet" href="sol.css">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="sol.js"></script>
</head>
<body>
	<form action="editItem.do">
		Name: <input type="text" name="name" value="${manager.currentItem.name}"><br>
		<input type="submit" value="Save Changes">
		<input type="submit" value="Cancel" formaction="viewDashboardItem.do">
		<hr>

		Parent Cards:
	<div>
		<select id="my-select" name="parents[]" multiple="multiple">
			<c:forEach var="card" items="${manager.cardSet}">
				<c:choose>
				<c:when test="${card == manager.currentCard}">					
					<option value="<c:out value="${card.name}"/>" selected> ${card.name}</option>
				</c:when>
				<c:otherwise>
					<option value="<c:out value="${card.name}"/>"> ${card.name}</option>
				</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>

		<script type="text/javascript">
			$(function() {
				// initialize sol
				$('#my-select').searchableOptionList({maxHeight: '250px'});
			});
		</script>

	</div>
	</form>
</body>
</html>