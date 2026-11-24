package todo.app.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import todo.app.exceptions.TodoExceptionHandler;
import todo.app.services.TodoServices;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class TodoControllersTest {
    private TodoServices todoServices;
    private MockMvc mockMvc;

    public void setUp(){
        todoServices = mock(TodoServices.class);
        mockMvc = standaloneSetup(new TodoControllers(todoServices))
                .setControllerAdvice(new TodoExceptionHandler())
                .build();
    }
    @Test
    public void createTodoWithInvalidRequestReturnsBadRequest() throws Exception{
        mockMvc.perform(post("/api/create-todo")
                .contentType("application/json")
                .content("""
                        {
                            "Title" : "";
                            "Description" : "";
                        
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
        verifyNoInteractions(todoServices);
    }
}