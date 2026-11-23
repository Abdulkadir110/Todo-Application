package todo.app.services;

import todo.app.dtos.requests.*;
import todo.app.dtos.responses.*;

public interface TodoServices {
    CreateTodoResponse createTodo(CreateTodoRequest request);

    UpdateTodoResponse updateTodo(UpdateTodoRequest updateRequest);

    FindTodoResponse findTodo(FindTodoRequest request);

    DeleteTodoResponse deleteTodo(DeleteTodoRequest request);

    CompletedTodoResponse markCompleted(CompletedTodoRequest request);
}
