package com.in28minutes.rest.webservices.restfulwebservices.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
public class TodoResource {

    private  TodoService todoService;

    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users/{username}/todos")
    public List<Todo> retriveTodos(@PathVariable String username){
        return todoService.findByUsername(username);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users/{username}/todos/{id}")
    public Todo retriveTodo(@PathVariable String username , @PathVariable int id){
        return todoService.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username , @PathVariable int id){
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username , @PathVariable int id , @RequestBody Todo todo){
        todoService.updateTodo(todo);
        return todo;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username , @RequestBody Todo todo){
        Todo created = todoService.addTodo(username,
                todo.getDescription(),
                todo.getTargetDate() ,
                todo.isDone());
        return created;
    }
}
