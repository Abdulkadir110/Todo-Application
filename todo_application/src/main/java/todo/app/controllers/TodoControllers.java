package todo.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import todo.app.dtos.requests.CreateTodoRequest;

@RestController
public class TodoControllers {
    public ResponseEntity<ApiResponse> createTodo(CreateTodoRequest request){

    }
}
