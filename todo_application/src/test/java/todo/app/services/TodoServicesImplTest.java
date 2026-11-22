package todo.app.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import todo.app.data.models.Todo;
import todo.app.data.repostories.TodoRepository;
import todo.app.dtos.requests.CreateTodoRequest;
import todo.app.dtos.requests.UpdateTodoRequest;
import todo.app.dtos.responses.CreateTodoResponse;
import todo.app.dtos.responses.UpdateTodoResponse;

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
        var createdTodo = createTodo();
        assertEquals(1L, todoRepository.count());
        Todo foundTodo = todoRepository.findById(createdTodo.getId()).get();
        assertEquals(createdTodo.getId(), foundTodo.getId());
        assertEquals(createdTodo.getTitle(), foundTodo.getTitle());
    }
    private CreateTodoResponse createTodo(){
        CreateTodoRequest request = new CreateTodoRequest();
        request.setTitle("Title");
        request.setDescription("Description");
        return todoServices.createTodo(request);
    }

    @Test
    public void testThatTodoCreated_CanBeUpdated(){
        todoRepository.deleteAll();
        var createdTodo = createTodo();
        Todo foundTodo = todoRepository.findById(createdTodo.getId()).get();
        assertEquals(createdTodo.getId(), foundTodo.getId());
        assertEquals("Title", foundTodo.getTitle());

        UpdateTodoRequest updateRequest = new UpdateTodoRequest();
        updateRequest.setId(createdTodo.getId());
        updateRequest.setDescription("Updated Description");
        var updatedTodo = todoServices.updateTodo(updateRequest);

        Todo foundUpdatedTodo = todoRepository.findById(updatedTodo.getId()).get();
        assertEquals(updatedTodo.getTitle(), foundUpdatedTodo.getTitle());
        assertEquals(updatedTodo.getUpdatedAt(), foundUpdatedTodo.getUpdatedAt());
    }
}