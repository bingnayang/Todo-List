package in.todo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.todo.dao.TodoDAO;
import in.todo.dao.TodoDAOImplement;
import in.todo.entity.Todo;


public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	TodoDAO todoDAO= null;
	
    public TodoController() {
    	todoDAO = new TodoDAOImplement();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action == null) {
			action = "LIST";
		}
		switch(action) {
			case "LIST":
				getItemList(request,response);
				break;
			case "EDIT":
				editItem(request,response);
				break;
			case "DELETE":
				deleteItem(request,response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newItem = request.getParameter("newItem");
		String id = request.getParameter("itemId");

		Todo item = new Todo();
		item.setTodoItem(newItem);
		
		if(id.isEmpty() || id == null) {
			// Insert new item
			if(todoDAO.addItem(item)) {
				System.out.println("Item Added");
			}
		}else {
			// Update item
			item.setTodo_Id(Integer.parseInt(id));
			if(todoDAO.updateItem(item)) {
				System.out.println("Item Update");
			}
		}
		response.sendRedirect("TodoController?action=LIST");
	}
	
	public void getItemList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Todo> allList = todoDAO.getList();		
//		for(Todo item:allList) {
//			System.out.println(item.getTodo_Id());
//			System.out.println(item.getTodoItem());
//		}
		request.setAttribute("allList",allList);
		// Get the request dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/todo.jsp");
		// Forward the request and response objects
		dispatcher.forward(request,response);	
		
	}
	public void deleteItem(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		String id = request.getParameter("id");
		System.out.println("Delete item ID: "+id);
		
		if(todoDAO.delete(Integer.parseInt(id))){
			System.out.println("Item Delete");
		}
		// Get update todo item list
		response.sendRedirect("TodoController?action=LIST");
	}
	public void editItem(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
			
		String id = request.getParameter("id");
		System.out.println("Edit item ID: "+id);
		
		Todo item = todoDAO.editItem(Integer.parseInt(id));	
		request.setAttribute("item",item);
		// Also display the todo list
		List<Todo> allList = todoDAO.getList();		
		request.setAttribute("allList",allList);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/todo.jsp");
		// Forward the request and response objects
		dispatcher.forward(request,response);	
	
	}
}
