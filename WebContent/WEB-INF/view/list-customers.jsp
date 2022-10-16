<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.srikanth.springdemo.util.SortUtils" %>
<!DOCTYPE html>
<html>
	<head>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
		<title>list customers</title>
	</head>
	<body>
		<div id="wrapper" >
			<div id="header">
				<h2>CRM - Customer Relationship Manager</h2>
			</div>
		</div>
		
		<div id="container">
			<div id="content">
				
					<input type="button" value="Add Customer"
						onClick="window.location.href='showFormForAdd'; return false;"
						class="add-button"/>
					
				<form:form action="search" method="GET">
				Search Customer:<input type="text" name="searchName"/>
				<input type="submit" value="Search" class-name="add-button"/>
				
				</form:form>
				<c:url var="sortLinkFirstName" value="/customer/list">
					<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" />
				</c:url>
				<c:url var="sortLinkLastName" value="/customer/list">
					<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>" />
				</c:url>
				<c:url var="sortLinkEmail" value="/customer/list">
					<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>" />
				</c:url>
				<br>
				<table>
					<tr> 
						<th><a href="${sortLinkFirstName}">First Name </a> </th>
						<th><a href="${sortLinkLastName}">Last Name</a></th>
						<th><a href="${sortLinkEmail}">Email</a></th>
						<th> Update</th>
					</tr>
					<c:forEach var="tempCustomer" items="${customers}">
						<c:url var="updateLink" value="/customer/showFormForUpdate">
							<c:param name="customerId" value="${tempCustomer.id}"></c:param>
						</c:url>
						<c:url var="deleteLink" value="/customer/delete">
							<c:param name="customerId" value="${tempCustomer.id}"></c:param>
						</c:url>
						<tr>
							<td> ${tempCustomer.firstName} </td>
							<td> ${tempCustomer.lastName} </td>
							<td> ${tempCustomer.email} </td>
							
							<td> 
								<a href="${updateLink}">Update</a>
								|
								<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this Customer?'))) return false">Delete</a>
							</td>
						</tr>
					
					</c:forEach>
					
					
				</table>
			</div>
			
		</div>
	</body>
</html>