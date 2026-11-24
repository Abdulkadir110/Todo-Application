package todo.app.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.app.dtos.requests.CreateTodoRequest;
import todo.app.dtos.responses.ApiResponse;
import todo.app.dtos.responses.CreateTodoResponse;
import todo.app.services.TodoServices;

@RestController
public class TodoControllers {
    private final TodoServices todoServices;
    public TodoControllers(TodoServices todoServices){
        this.todoServices = todoServices;
    }

    @PostMapping
    @RequestMapping("/api/create-todo")
    public ResponseEntity<ApiResponse> createTodo(@Valid @RequestBody CreateTodoRequest request){
        CreateTodoResponse response = todoServices.createTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, response));
    }
}
