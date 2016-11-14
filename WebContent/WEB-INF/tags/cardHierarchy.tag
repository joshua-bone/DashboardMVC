<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="card" type="dashboard.IndexCard" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="panel panel-default">
	<div class="panel-heading">
        <div class="panel-title clearfix">
			<a href="viewIndexCard.do?cid=<c:out value="${card.customID}"/>">
			<c:out value="${card.name} (${card.numItems})"/>
			</a> 
			
			<!-- Delete button -->
			<button type="button" class="btn btn-danger btn-sm pull-right" onclick="location.href='deleteIndexCard.do?cid=${card.customID}'">
          		<span class="glyphicon glyphicon-trash"></span>
        		</button> 
        		 
			<!-- Edit button -->
			<button type="button" class="btn btn-warning btn-sm pull-right" onclick="location.href='editIndexCardView.do?cid=${card.customID}'">
          		<span class="glyphicon glyphicon-pencil"></span>
        		</button> 
        		
			<!-- Inspect button -->
			<button type="button" class="btn btn-info btn-sm pull-right" onclick="location.href='viewIndexCard.do?cid=${card.customID}'">
          		<span class="glyphicon glyphicon-zoom-in"></span>
        		</button> 
        		
			<!-- Add Item button -->
			<button type="button" class="btn btn-primary btn-sm pull-right" onclick="location.href='newItemView.do?cid=${card.customID}'">
          		<span class="glyphicon glyphicon-plus"></span>
        		</button> 
        		   
        </div>
     </div>
	<div class="panel-body">
	
	<!-- Show the DashboardItems on this card -->
	<c:forEach var="item" items="${card.items}">
		<a href="viewDashboardItem.do?cid=<c:out value="${item.customID}"/>"><c:out value="${item.name}"/></a><br>
	</c:forEach>
	
	<hr>
	
	<!-- Show the IndexCards that are children of this card -->
	<c:forEach var="child" items="${card.children}">
			<myTags:cardHierarchy card="${child}"/>
	</c:forEach>
	</div>
</div>


