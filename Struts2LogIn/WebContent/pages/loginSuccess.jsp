<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success!</title>
<link rel="stylesheet" href="./css/jquery-ui.min.css" />
<script type="text/javascript" src="./scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="./scripts/jquery-ui.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$("button").button();
	
	// event hander
	$("#userDetails").on("click", function() {
		var objForm = document.createElement("FORM");
		var objInput = document.createElement("INPUT");
		objForm.setAttribute("action", "LogInAction");
		objInput.setAttribute("type", "hidden");
		objInput.setAttribute("name", "actionType");
		objInput.setAttribute("value", "doLogout");
		objForm.appendChild(objInput);
		document.body.appendChild(objForm);
		// submit
		objForm.submit();
	});
	
	if ('<s:property value="#request.windowType" />' != null && '<s:property value="#request.windowType" />' == 'viewWindow') {
		$("#addSection").dialog({
			modal: true,
			title: "Add",
			resizable: true,
			height:500,
			width:"auto"
		});
	}
	
	// add link
	$("#addNotesLink").on("click", function() {
		$("#addSection").dialog({
			modal: true,
			title: "Add",
			resizable: true,
			height:500,
			width:"auto"
		});
	});
	
	$("#saveNoteButton").on("click", function() {
		var objForm = document.forms['NotesActionForm'];
		objForm.actionType.value = "saveNotes";
		objForm.submit();
	});
});

function viewNoteById(id) {
	var objForm = document.forms['NotesActionForm'];
	objForm.actionType.value = "viewNote";
	objForm.noteId.value = id;
	// submit
	objForm.submit();
}
</script>
</head>
<body>
	<div>You are logged in as <span id="userDetails" style="text-decoration: underline; cursor: pointer; font-style: italic;">${userName }</span>.</div>
	<u style="cursor: pointer;" id="addNotesLink">Add</u>
	<div> <!-- for presenting data -->
		<table>
			<thead>
				<tr>
					<td>
						Name
					</td>
					<td>
						Created Date
					</td>
				</tr>
			</thead>
			<s:iterator value="#request.notesList">
				<tr>
					<td style="text-decoration: underline; cursor: pointer;" onclick='viewNoteById("<s:property value='noteId' />")'>
						<s:property value="noteName" />
					</td>
					<td>
						<s:property value="createdTime" />
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<div style="display:none;" id="addSection"> <!-- for popup -->
		<s:form action="NotesAction" id="NotesActionForm">
		<s:hidden name="actionType" />
		<s:hidden name="noteId" />
			<table>
				<tr>
					<td>
						<s:if test="#request.windowType != 'viewWindow'">
							<s:textfield name="noteName" label="Title" style="width:250px;"></s:textfield>
						</s:if>
						<s:else>
							<s:textfield name="noteName" label="Title" style="width:250px; background-color: lightgrey;" readonly="true"></s:textfield>
						</s:else>
					</td>
				</tr>
				<tr>
					<td>
						<s:if test="#request.windowType != 'viewWindow'">
							<s:textarea name="content" label="Content" style="width:250px; height:300px;"></s:textarea>
						</s:if>
						<s:else>
							<s:textarea name="content" label="Content" style="width:250px; height:300px; background-color: lightgrey;" readonly="true"></s:textarea>
						</s:else>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<s:if test="#request.windowType != 'viewWindow'">
							<button type="button" id="saveNoteButton">Save</button>
							<s:reset type="button" value="clear" theme="simple" />
						</s:if>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>