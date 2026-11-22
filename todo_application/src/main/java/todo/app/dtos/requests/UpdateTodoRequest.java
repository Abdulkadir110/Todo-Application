package todo.app.dtos.requests;

import lombok.Data;

@Data
public class UpdateTodoRequest {
    private String id;
    private String Title;
    private String Description;
}
