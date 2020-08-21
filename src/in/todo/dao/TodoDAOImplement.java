package in.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.todo.entity.Todo;
import in.todo.util.DBConnectionUtil;

public class TodoDAOImplement implements TodoDAO{

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	
	@Override
	public List<Todo> getList() {
		String sql = "SELECT * FROM todo";
		// Create reference variables
		List<Todo> list = null;
		Todo item = null;
		
		try {
			list = new ArrayList<Todo>();
			// Get the database connection
			connection = DBConnectionUtil.openConnection();
			// Create a statement
			statement = connection.createStatement();
			// Execute the query
			resultSet = statement.executeQuery(sql);
			// Process the resultSet
			while(resultSet.next()) {
				item = new Todo();
				item.setTodo_Id(resultSet.getInt("todo_Id"));
				item.setTodoItem(resultSet.getString("todoItem"));
				// Add item to list
				list.add(item);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM todo WHERE todo_Id = "+id;
		boolean flag = false;
		try {

			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
			
		}catch(SQLException e) {;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addItem(Todo item) {
		boolean flag = false;
		String sql = "INSERT INTO todo (todoItem) " + 
				"VALUES('"+item.getTodoItem()+"')";
		try {
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Todo editItem(int id) {
		String sql = "SELECT * FROM todo WHERE todo_Id ="+id;
		// Create reference variables
		Todo item = null;
		
		try {
			// Get the database connection
			connection = DBConnectionUtil.openConnection();
			// Create a statement
			statement = connection.createStatement();
			// Execute the query
			resultSet = statement.executeQuery(sql);
			// Process the resultSet
			while(resultSet.next()) {
				item = new Todo();
				item.setTodo_Id(resultSet.getInt("todo_Id"));
				item.setTodoItem(resultSet.getString("todoItem"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public boolean updateItem(Todo item) {
		boolean flag = false;
		try {
			String sql = "UPDATE todo SET todoItem = '"+item.getTodoItem()+"' WHERE todo_Id = "+item.getTodo_Id();
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
		
		
	}

}
