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
		List<Todo> allList = todoDAO.getList();		
		// Add the book to request object
		for(Todo item:allList) {
			System.out.println(item.getTodoItem());
		}
		request.setAttribute("allList",allList);
		// Get the request dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		// Forward the request and response objects
		dispatcher.forward(request,response);	
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//	}

}
