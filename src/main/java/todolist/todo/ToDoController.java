package todolist.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @RequestMapping("/todos")
    public List<ToDo> getAllTodos() {
        return this.toDoService.getAllToDos();
    }

    @RequestMapping("/todos/{id}")
    public ResponseEntity<ToDo> getTodo(@PathVariable Integer id) {
        ToDo todo = this.toDoService.getTodo(id);
        if (todo != null) {
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/todos")
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo toDo) {
        ToDo createToDo = this.toDoService.createToDo(toDo);
        return new ResponseEntity<>(createToDo, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/todos/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable Integer id, @RequestBody ToDo updateToDo) {
        ToDo toDo = this.toDoService.updateToDo(updateToDo);
        if (toDo != null) {
            return new ResponseEntity<>(toDo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/todos/{id}")
    public ResponseEntity<Integer> deleteToDo(@PathVariable Integer id) {
        ToDo toDo = this.toDoService.deleteToDo(id);
        if (toDo != null) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
