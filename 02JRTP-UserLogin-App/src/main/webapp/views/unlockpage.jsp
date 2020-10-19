<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration form</title>
</head>
<body>
 <div align="center">
	<font color="red">${err}</font>
	<font color="green">${succ}</font>

	<h1>Unlock Your Account Here!!!!</h1>

	<f:form action="unlockAcc?email=${unlockobj.email}" method="post" modelAttribute="unlockobj">
		<table style="with: 50%">
			<tr>
				<td><h3>${unlockobj.email}</h3> </td>
			</tr>
			<tr>
				<td>Temporary Password:</td>
				<td><f:input type="text" path="tempPwd" /></td>
			</tr>
			<tr>
				<td>New Password:</td>
				<td><f:input type="text" path="newPwd" /></td>
			</tr>
			<tr>
				<td>Confirm Password:</td>
				<td><f:input type="text" path="cnfrmPwd" /></td>
			</tr>
		
		</table>

		<input type="submit" value="Save" />
		<input type="Reset" value="Reset" />

	</f:form>
	</div>

</body>
</html>