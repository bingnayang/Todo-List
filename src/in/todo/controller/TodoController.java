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
			case "DELETE":
				deleteItem(request,response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newItem = request.getParameter("newItem");
		System.out.println("New item: "+newItem);
		
		Todo item = new Todo();
		item.setTodoItem(newItem);
		
		// Insert new item
		if(todoDAO.addItem(item)) {
			System.out.println("Item Added");
		}
		response.sendRedirect("TodoController?action=LIST");
	}
	
	public void getItemList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Todo> allList = todoDAO.getList();		
		// Add the book to request object
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
//		getItemList(request,response);
		response.sendRedirect("TodoController?action=LIST");
	}
}
