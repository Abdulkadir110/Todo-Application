package todo.app.dtos.responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTodoResponse {
    private String id;
    private String title;
    private String description;
    private LocalDate createdAt;
    private boolean completed;
}
