package todo.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.app.data.models.Todo;
import todo.app.data.repostories.TodoRepository;
import todo.app.dtos.requests.*;
import todo.app.dtos.responses.*;
import todo.app.exceptions.TodoNotFoundException;

import java.time.LocalDate;

@Service
public class TodoServicesImpl implements TodoServices{
    private TodoRepository todoRepository;

    public TodoServicesImpl(@Autowired TodoRepository repository){
        this.todoRepository = repository;
    }
    @Override
    public CreateTodoResponse createTodo(CreateTodoRequest request) {
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCompleted(false);
        Todo savedTodo = todoRepository.save(todo);
        CreateTodoResponse response = new CreateTodoResponse();
        response.setId(savedTodo.getId());
        response.setTitle(savedTodo.getTitle());
        response.setDescription(savedTodo.getDescription());
        response.setCreatedAt(savedTodo.getCreatedAt());
        response.setCompleted(savedTodo.isCompleted());
        return response;
    }

    @Override
    public UpdateTodoResponse updateTodo(UpdateTodoRequest updateRequest) {
        Todo foundTodo = todoRepository.findById(updateRequest.getId())
                .orElseThrow(() -> new TodoNotFoundException("Todo not found"));
        foundTodo.setTitle(updateRequest.getTitle());
        foundTodo.setDescription(updateRequest.getDescription());
        foundTodo.setUpdatedAt(LocalDate.now());
        Todo updatedTodo = todoRepository.save(foundTodo);
        UpdateTodoResponse response = new UpdateTodoResponse();
        response.setTitle(updatedTodo.getTitle());
        response.setDescription(updatedTodo.getDescription());
        response.setUpdatedAt(updatedTodo.getUpdatedAt());
        return response;

    }

    @Override
    public FindTodoResponse findTodo(FindTodoRequest request) {
        Todo foundTodo = todoRepository.findById(request.getId())
                .orElseThrow(() -> new TodoNotFoundException("Todo not found"));
        FindTodoResponse response = new FindTodoResponse();
        response.setTitle(foundTodo.getTitle());
        response.setCreatedAt(foundTodo.getCreatedAt());
        response.setDescription(foundTodo.getDescription());
        return response;
    }

    @Override
    public DeleteTodoResponse deleteTodo(DeleteTodoRequest request) {
        Todo foundTodo = todoRepository.findById(request.getId())
            .orElseThrow(() -> new TodoNotFoundException("Todo not found"));
        todoRepository.delete(foundTodo);
        DeleteTodoResponse response = new DeleteTodoResponse();
        response.setDeleted(true);
        return response;
    }

    @Override
    public CompletedTodoResponse markCompleted(CompletedTodoRequest request) {
        Todo foundTodo = todoRepository.findById(request.getId())
                .orElseThrow(() -> new TodoNotFoundException("Todo not found"));
        foundTodo.setCompleted(true);
        Todo completedTodo = todoRepository.save(foundTodo);
        CompletedTodoResponse response = new CompletedTodoResponse();
        response.setCompleted(completedTodo.isCompleted());
        return response;
    }
}
