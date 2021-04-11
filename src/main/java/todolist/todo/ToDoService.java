package todolist.todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    private final List<ToDo> todos = new ArrayList<>(
            Arrays.asList(new ToDo(1, "Task 1", "Summary 1", "Description 1"),
                    new ToDo(2, "Task 2", "Summary 2", "Description 2"),
                    new ToDo(3, "Task 3", "Summary 3", "Description 3")
            )
    );

    public List<ToDo> getAllToDos() {
        return this.todos;
    }

    public ToDo getTodo(Integer id) {
        Optional<ToDo> todo = this.todos.stream().filter(t -> t.id.equals(id)).findFirst();
        return todo.orElse(null);
    }

    public ToDo createToDo(ToDo toDo) {
        int latestId = this.todos.stream().mapToInt(x -> x.id).max().orElse(-1);
        if (latestId == -1) {
            toDo.id = 1;
        } else {

            toDo.id = latestId + 1;
        }
        this.todos.add(toDo);
        return toDo;
    }

    public ToDo updateToDo(ToDo updateToDo) {

        Optional<ToDo> optionalTodo = this.todos.stream().filter(t -> t.id.equals(updateToDo.id)).findFirst();
        if (optionalTodo.isEmpty()) {
            return null;
        } else {
            ToDo toDo = optionalTodo.get();
            toDo.setName(updateToDo.name);
            toDo.setSummary(updateToDo.summary);
            toDo.setDescription(updateToDo.description);
            return toDo;
        }
    }

    public ToDo deleteToDo(Integer id) {
        Optional<ToDo> optionalTodo = this.todos.stream().filter(t -> t.id.equals(id)).findFirst();
        if (optionalTodo.isEmpty()) {
            return null;
        } else {
            ToDo toDo = optionalTodo.get();
            this.todos.remove(toDo);
            return toDo;
        }
    }
}
