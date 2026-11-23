package todo.app.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteTodoRequest {
    private String id;
}
