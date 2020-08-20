package in.todo.dao;

import java.util.List;
import in.todo.entity.Todo;

public interface TodoDAO {
	List<Todo> getList();
	boolean delete(int id);
}
