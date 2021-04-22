<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="model.Employee" %>


<%
if (request.getParameter("EmpName") != null)
{
	 Employee Obj = new Employee();
	 String stsMsg="";
	if (request.getParameter("hidItemIDSave") == "")
	 {
	 stsMsg = Obj.insertEmployee(request.getParameter("EmpName"),
	 request.getParameter("EmpType"),
	 request.getParameter("EmpPhone"),
	 request.getParameter("EmpEmail"),
	 request.getParameter("EmpPassword"));
	 } else{
		 stsMsg =Obj.updateEmployee(request.getParameter("hidItemIDSave"),
				 request.getParameter("EmpName"),
				 request.getParameter("EmpType"),
				 request.getParameter("EmpPhone"),
				 request.getParameter("EmpEmail"),
				 request.getParameter("EmpPassword"));
		 
		
	 }
	 session.setAttribute("statusMsg", stsMsg);
}

//Delete item----------------------------------
if (request.getParameter("hidItemIDDelete") != null)
{
Employee Obj = new Employee();
String stsMsg = Obj.deleteEmployee(request.getParameter("hidItemIDDelete"));
session.setAttribute("statusMsg", stsMsg);
}
%>

<html>
<head>
<link rel="stylesheet" href="views/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Employee Management</title>
</head>

<body>

<h1>Employee Management</h1>
<form method="post" action="employee.jsp">
<div class="container">
 <div class="row">
 <div class="col">

 Employee Name: <input name="EmpName" type="text"  class="form-control"><br>
 Employee Type: <input name="EmpType" type="text"  class="form-control"><br>
 Employee PhoneNumer: <input name="EmpPhone" type="text"  class="form-control"><br>
 Employee Email: <input name="EmpEmail" type="text"  class="form-control"><br>
 Employee Password: <input name="EmpPassword" type="text"  class="form-control"><br>
 <input name="btnSubmit" type="submit" value="Save"class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
 </div>
 </div>
</div>
</form>
<div class="alert alert-success">
 <%
 out.print(session.getAttribute("statusMsg"));
 %>
</div>
<br>
<%
Employee Obj = new Employee();
 out.print(Obj.readEmployee());
%>

</body>
</html>