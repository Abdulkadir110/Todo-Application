package todo.app.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompletedTodoRequest {
    @NotBlank(message = "id must not be blank")
    private String id;
}
