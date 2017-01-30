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
<title>Sign up</title>
<script type="text/javascript">
$(document).ready(function() {
	$("button").button();
});
</script>
</head>
<body>
	<s:form action="RegisterAction">
		<table>
			<tr>
				<td>
					<h4>Register</h4>
				</td>
			</tr>
			<tr><td><s:textfield name="userName" label="Username" /></td></tr>
			<tr><td><s:textfield name="passWord" label="Password" /></td></tr>
			<tr><td><s:textfield name="rePWord" label="Re-Enter Password" /></td></tr>
			<tr><td><s:textfield name="age" label="Age" /></td></tr>
			<tr><td><s:radio label="Gender" name="gender" list="#{'Male':'Male', 'Female':'Female'}"></s:radio></tr>
			<tr>
				<td>
					<s:submit type="button" name="actionType" value="doRegister" theme="simple">Submit</s:submit>
					<s:reset type="button" value="clear" theme="simple" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html>