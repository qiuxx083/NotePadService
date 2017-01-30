<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/jquery-ui.min.css" />
<script type="text/javascript" src="./scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="./scripts/jquery-ui.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$("button").button();
	
	$("#signup").on("click", function() {
		var form = document.forms[0];
		form.action = form.action.concat("?actionType=showRegisterPage");
		form.submit();
	});
});
</script>
<style type="text/css">
.errorMessage {
	color: red;
	font-weight: bold;
}

.errorLabel {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<s:form action="LogInAction">
		<table align="center" style="position:relative; top:240px;">
			<tr>
				<td><s:actionerror/></td>
			</tr>
		</table> <!-- this table is used for error/message -->
		<table align="center" style="position:relative; top:250px;">
			<tr>
				<td><s:textfield name="userName" label="Username" /></td>
			</tr>
			<tr>
				<td><s:password name="passWord" label="Password" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="right" style="padding-top:10px;">
					<s:submit type="button" name="actionType" value="doLogin" theme="simple">login</s:submit>
					<s:reset type="button" value="clear" theme="simple" />
				</td>
			</tr>
			<tr>
				<td align="right" colspan="2">
					<span>Don't have an account? <u id="signup" style="cursor:pointer;">sign up</u> here!</span>
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html>