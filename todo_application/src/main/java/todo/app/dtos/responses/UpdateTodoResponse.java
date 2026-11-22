package todo.app.dtos.responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTodoResponse {
    private String title;
    private String description;
    private LocalDate updatedAt;
    private String id;
}
