package in.todo.dao;

import java.util.List;
import in.todo.entity.Todo;

public interface TodoDAO {
	List<Todo> getList();
	boolean addItem(Todo item);
	boolean delete(int id);
	Todo editItem(int id);
	boolean updateItem(Todo item);
}
