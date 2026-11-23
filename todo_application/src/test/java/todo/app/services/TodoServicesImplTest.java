package todo.app.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import todo.app.data.models.Todo;
import todo.app.data.repostories.TodoRepository;
import todo.app.dtos.requests.*;
import todo.app.dtos.responses.CreateTodoResponse;
import todo.app.exceptions.TodoNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
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

        Todo foundUpdatedTodo = todoRepository.findById(createdTodo.getId()).get();
        assertEquals(updatedTodo.getTitle(), foundUpdatedTodo.getTitle());
        assertEquals(updatedTodo.getUpdatedAt(), foundUpdatedTodo.getUpdatedAt());

        assertThat(todoRepository.count()).isEqualTo(1L);
    }
    @Test
    public void testThatATodoCreated_CanBeFound() {
        todoRepository.deleteAll();
        var createTodo = createTodo();
        FindTodoRequest request = new FindTodoRequest();
        request.setId(createTodo.getId());
        var foundTodo = todoServices.findTodo(request);

        assertEquals(foundTodo.getTitle(), createTodo.getTitle());
        assertEquals(foundTodo.getDescription(), createTodo.getDescription());

    }
    @Test
    public void test_FindATodo_WithWrongId_ThrowsException(){
        todoRepository.deleteAll();
        FindTodoRequest request = new FindTodoRequest();
        request.setId("9281");
        assertThrows(TodoNotFoundException.class, () -> todoServices.findTodo(request));
    }
    @Test
    public void test_toUpdateATodoWithWrongId_ThrowsException(){
        todoRepository.deleteAll();
        var createTodo = createTodo();
        UpdateTodoRequest request = new UpdateTodoRequest();
        request.setId("9234");
        request.setDescription("Wrong Description");
        assertThrows(TodoNotFoundException.class, () -> todoServices.updateTodo(request));
    }
    @Test
    public void test_createTodo_CountIsOne_deleteTheTodo_Count_IsZero(){
        todoRepository.deleteAll();
        var createTodo = createTodo();
        assertThat(todoRepository.count()).isEqualTo(1L);
        DeleteTodoRequest request = new DeleteTodoRequest();
        request.setId(createTodo.getId());
        todoServices.deleteTodo(request);
        assertThat(todoRepository.count()).isEqualTo(0L);

    }
    @Test
    public void test_createTodo_markCreatedTodo_As_Completed(){
        todoRepository.deleteAll();
        var createTodo = createTodo();
        assertThat(createTodo.isCompleted()).isFalse();
        CompletedTodoRequest request = new CompletedTodoRequest();
        request.setId(createTodo.getId());
        var completedTodo = todoServices.markCompleted(request);
        assertThat(completedTodo.isCompleted()).isTrue();
        assertThat(todoRepository.findById(createTodo.getId()).orElseThrow().isCompleted()).isTrue();
    }

}
