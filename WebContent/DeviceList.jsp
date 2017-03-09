<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Device Application</title>
</head>
<body>
	<center>
		<h1>Device CRUD</h1>
        <h2>
        	<a href="new">Add New Device</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Devices</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Devices</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>GPIO</th>
                <th>Type</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="device" items="${listDevice}">
                <tr>
                    <td><c:out value="${device.id}" /></td>
                    <td><c:out value="${device.name}" /></td>
                    <td><c:out value="${device.GPIOnumber}" /></td>
                    <td><c:out value="${device.type}" /></td>
                    <td>
                    	<a href="edit?id=<c:out value='${device.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?id=<c:out value='${device.id}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="startscript?>">start</a>
    </div>	
</body>
</html>
