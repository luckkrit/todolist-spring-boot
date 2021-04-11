package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;


    public List<ToDo> getAllToDos() {
        List<ToDo> todos = new ArrayList<>();
        this.toDoRepository.findAll().forEach(todos::add);
        return todos;
    }

    public ToDo getTodo(Integer id) {
        Optional<ToDo> optionalToDo = this.toDoRepository.findById(id);
        if (optionalToDo.isEmpty()) {
            return null;
        } else {
            return optionalToDo.get();
        }
    }

    public ToDo createToDo(ToDo toDo) {
        this.toDoRepository.save(toDo);
        return toDo;
    }

    public ToDo updateToDo(ToDo updateToDo) {
        this.toDoRepository.save(updateToDo);
        return updateToDo;
    }

    public ToDo deleteToDo(Integer id) {
        Optional<ToDo> optionalToDo = this.toDoRepository.findById(id);
        if (optionalToDo.isEmpty()) {
            return null;
        } else {
            ToDo toDo = optionalToDo.get();
            this.toDoRepository.delete(toDo);
            return toDo;
        }
    }
}
