<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">

<title>Todo List</title>
</head>
<body>
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-4">Todo List</h1>
		</div>
		<div class="container">
			<form action="${pageContext.request.contextPath}/TodoController" method="POST">
				<div class="form-group row">
					<div class="col-sm-10">
						<input type="text" class="form-control" placeholder="Enter todo item" name="newItem">
					</div>
					<div class="col-sm-2">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="container">
		<c:forEach items="${allList}" var="item">
			<li
				class="list-group-item d-flex justify-content-between align-items-center">
				${item.todoItem}
				<div>
					<a type="button" class="btn btn-outline-info btn-sm"
						href="${pageContext.request.contextPath}/TodoController?action=EDIT&id=${item.todo_Id}">Edit</a>
					/ <a type="button" class="btn btn-outline-danger btn-sm"
						href="${pageContext.request.contextPath}/TodoController?action=DELETE&id=${item.todo_Id}">Delete</a>
				</div>
			</li>
		</c:forEach>
	</div>

</body>
</html>