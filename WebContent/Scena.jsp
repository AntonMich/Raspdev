<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Scena</title>
</head>
<body>
<center>
		<h1>Device SCENE</h1>
        <h2>
        	<a href="list">List All Devices</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">Back</a>
        </h2>
	</center>
<div align="center">
<c:if test="${device != null}">
			<form action="status" method="post">
			</c:if>
        <table border="1" cellpadding="5">
            <caption><h2>List of Devices</h2></caption>
            <tr>
                <th>Name</th>
                <th>GPIO</th>
                <th>Type</th>
                <th>Status</th>
            </tr>
            <c:forEach var="device" items="${listDevice}">
                <tr>
                    <td><c:out value="${device.name}" /></td>
                    <td><c:out value="${device.GPIOnumber}" /></td>
                    <td><c:out value="${device.type}" /></td>
                    <td><input type="text" name="GPIOnumber" size="45"
					value="<c:out value='${device.status}' />" /> </td>
                </tr>
            </c:forEach>
            <tr align="center"><input type="submit"
					value="Save" />
			</tr>
        </table>
        </form>
    </div>
</body>
</html>