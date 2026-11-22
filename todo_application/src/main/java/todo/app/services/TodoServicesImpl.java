package todo.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.app.data.models.Todo;
import todo.app.data.repostories.TodoRepository;
import todo.app.dtos.requests.CreateTodoRequest;
import todo.app.dtos.responses.CreateTodoResponse;

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
        Todo savedTodo = todoRepository.save(todo);
        CreateTodoResponse response = new CreateTodoResponse();
        response.setId(savedTodo.getId());
        response.setDescription(savedTodo.getDescription());
        response.setCreatedAt(savedTodo.getCreatedAt());
        return response;
    }
}
