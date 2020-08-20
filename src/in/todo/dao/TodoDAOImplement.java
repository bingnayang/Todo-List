package in.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

}
