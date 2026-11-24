package todo.app.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateTodoRequest {
    @NotBlank(message = "id must not be blank")
    private String id;
    @NotBlank(message = "title must not be blank")
    private String Title;
    @NotBlank(message = "description must not be blank")
    private String Description;
}
