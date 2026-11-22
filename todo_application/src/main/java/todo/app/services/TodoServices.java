package todo.app.services;

import todo.app.dtos.requests.CreateTodoRequest;
import todo.app.dtos.responses.CreateTodoResponse;

public interface TodoServices {
    CreateTodoResponse createTodo(CreateTodoRequest request);
}
