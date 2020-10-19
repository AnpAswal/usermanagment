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

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

	<script>
		$(document).ready(
				function() {
					$("#userEmail").blur(
							function() {
								$("#errMsg").text("");
								$.ajax({
									type : "GET",
									url : "uniqueMailCheck?email="
											+ $("#userEmail").val(),
									success : function(data) {
										if (data == "Email already registerd") {
											$("#errMsg")
													.text("Duplicate Email");
											$("#submitBtn").prop("disabled",
													true);
										} else {
											$("#submitBtn").prop("disabled",
													false);
											console.log("not error")
										}
									}
								});

							}

							);

					$('#cntryId').on('change',
							function() {
						 $("#stateId").find("option:not(:first)").remove();
						 $("#cityId").find("option:not(:first)").remove();
								$.ajax({
									url : "countryChange?countryId="+$("#cntryId").val(),
									success : function(data) {
										$.each(data, function(key,value) {
											$('#stateId').append(
													$("<option>").attr("value", key).text(value));
										});

									}
								});

							});

					$('#stateId').on(
							'change',
							function() {
								 $("#cityId").find("option:not(:first)").remove();
								$.ajax({
									url :'stateChange?stateId='
											+ $("#stateId").val(),
									success : function(data) {
										$.each(data, function(key,
												value) {
											$('#cityId').append(
													$("<option>").attr("value", key).text(value));
										});

									}
								});

							});

				});
	</script>

	<font color="red">${err}</font>
	<font color="green">${succ}</font>

	<h1>Sign Up Here!!!!</h1>

	<f:form action="/userRegistration" method="post"
		modelAttribute="userobj">
		<table style="with: 50%">
			<tr>
				<td>First Name</td>
				<td><f:input type="text" path="fname" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><f:input type="text" path="lname" /></td>
			</tr>
			<tr>
				<td>Contact No</td>
				<td><f:input type="text" path="phno" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><f:input type="text" path="email" id="userEmail" /><span
					id="errMsg"></span></td>
			</tr>

			<!--  <tr>
				<td>Date Of Birth</td>
				<td><f:input type="text" path="dob" /></td>
			</tr>-->

			<tr>
				<td>Sex:</td>
				<td>Male: <f:radiobutton path="gender" value="Male" /> <br />
					Female: <f:radiobutton path="gender" value="Female" />
				</td>
			</tr>

			<tr>
				<td>Select Country:</td>
				<td><f:select path="cntryId" id="cntryId">
						<f:option value="">-Select-</f:option>
						<f:options items="${cntry}"></f:options>
					</f:select></td>
			</tr>


			<tr>
				<td>Select State:</td>
				<td><f:select path="stateId" id="stateId">
						<f:option value="">-Select-</f:option>
					</f:select></td>
			</tr>

			<tr>
				<td>Select City:</td>
				<td><f:select path="cityId" id="cityId">
						<f:option value="">-Select-</f:option>
					</f:select></td>
			</tr>
		</table>

		<input type="submit" value="Register" id="submitBtn" />
		<input type="Reset" value="Reset" />

	</f:form>

</body>
</html>