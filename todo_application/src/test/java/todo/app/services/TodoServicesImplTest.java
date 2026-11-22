package todo.app.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import todo.app.data.models.Todo;
import todo.app.data.repostories.TodoRepository;
import todo.app.dtos.requests.CreateTodoRequest;
import todo.app.dtos.responses.CreateTodoResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

public class TodoServicesImplTest {
    @Autowired
    private TodoServices todoServices;
    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testThatATodoCanBeCreated(){
        todoRepository.deleteAll();
        CreateTodoRequest request = new CreateTodoRequest();
        request.setTitle("Title");
        request.setDescription("Description");
        var createdTodo = todoServices.createTodo(request);
        assertEquals(1L, todoRepository.count());
        Todo foundTodo = todoRepository.findById(createdTodo.getId()).get();
        assertEquals(createdTodo.getId(), foundTodo.getId());
        assertEquals(createdTodo.getTitle(), foundTodo.getTitle());

    }
}