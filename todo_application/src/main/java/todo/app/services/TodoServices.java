package todo.app.services;

import todo.app.dtos.requests.CreateTodoRequest;
import todo.app.dtos.requests.UpdateTodoRequest;
import todo.app.dtos.responses.CreateTodoResponse;
import todo.app.dtos.responses.UpdateTodoResponse;

public interface TodoServices {
    CreateTodoResponse createTodo(CreateTodoRequest request);

    UpdateTodoResponse updateTodo(UpdateTodoRequest updateRequest);

}
