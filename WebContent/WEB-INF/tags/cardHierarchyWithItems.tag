<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="card" type="dashboard.IndexCard" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div data-role="collapsible" class="custom-li">
<h4><c:out value="${card.name}"/></h4>
<ul data-role="listview">
<li class="custom-li"><a href="viewIndexCard.do?name=<c:out value="${card.uri}"/>"><c:out value="${card.name}"/></a></li>
  		<ul>
    	    <c:forEach var="item" items="${card.items}">
	    		<li><a href="viewDashboardItem.do?uuid=<c:out value="${item.uuid}"/>"><c:out value="${item.name}"/></a></li>
	    </c:forEach>
	    </ul>
<c:forEach var="child" items="${card.children}">
	<c:choose>
	<c:when test="${empty child.children}">
		<li class="custom-li"><a href="viewIndexCard.do?name=<c:out value="${child.uri}"/>"><c:out value="${child.name}"/></a></li>
		<ul>
    	    <c:forEach var="item" items="${child.items}">
	    		<li><a href="viewDashboardItem.do?uuid=<c:out value="${item.uuid}"/>"><c:out value="${item.name}"/></a></li>
	    </c:forEach>
	    </ul>
	</c:when>
	<c:otherwise>
		<myTags:cardHierarchyWithItems card="${child}"/>
	</c:otherwise>
	</c:choose>
</c:forEach>
</ul>
</div>


