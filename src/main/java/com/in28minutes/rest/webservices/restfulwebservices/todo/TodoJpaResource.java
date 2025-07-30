package com.in28minutes.rest.webservices.restfulwebservices.todo;

import com.in28minutes.rest.webservices.restfulwebservices.todo.repository.TodoRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoJpaResource {

    private  TodoService todoService;
    private TodoRepo todoRepo;

    public TodoJpaResource(TodoService todoService , TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
        this.todoService = todoService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users/{username}/todos")
    public List<Todo> retriveTodos(@PathVariable String username){

        return todoRepo.findByUsername(username);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users/{username}/todos/{id}")
    public Todo retriveTodo(@PathVariable String username , @PathVariable int id){

        return todoRepo.findById(id).get();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username , @PathVariable int id){
        todoRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }



    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username , @PathVariable int id , @RequestBody Todo todo){
        //todoService.updateTodo(todo);
        todoRepo.save(todo);
        return todo;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username , @RequestBody Todo todo){

        todo.setUsername(username);
        todo.setId(null);
        return todoRepo.save(todo);
//        Todo created = todoService.addTodo(username,
//                todo.getDescription(),
//                todo.getTargetDate() ,
//                todo.isDone());
//        return created;
    }
}
