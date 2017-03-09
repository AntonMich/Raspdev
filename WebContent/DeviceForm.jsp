<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Device Application</title>
</head>
<body>
	<center>
		<h1>Device CRUD</h1>
		<h2>
			<a href="new">Add New Device</a> &nbsp;&nbsp;&nbsp; <a href="list">List
				All Devices</a>

		</h2>
	</center>
	<div align="center">
		<c:if test="${device != null}">
			<form action="update" method="post">
		</c:if>
		<c:if test="${device == null}">
			<form action="insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${device != null}">
            			Edit Device
            		</c:if>
					<c:if test="${device == null}">
            			Add New Device
            		</c:if>
				</h2>
			</caption>
			<c:if test="${device != null}">
				<input type="hidden" name="id"
					value="<c:out value='${device.id}' />" />
			</c:if>
			<tr>
				<th>Name:</th>
				<td><input type="text" name="name" size="45"
					value="<c:out value='${device.name}' />" /></td>
			</tr>
			<tr>
				<th>GPIOnumber:</th>
				<td><input type="text" name="GPIOnumber" size="45"
					value="<c:out value='${device.GPIOnumber}' />" />
				</td>
			</tr>
			<tr>
				<th>Type:</th>
				<td><select id="type" name="type">
						<option value="button">button</option>
						<option value="led">led</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Save" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>
