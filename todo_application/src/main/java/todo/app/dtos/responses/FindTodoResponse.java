package todo.app.dtos.responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FindTodoResponse {
    private String description;
    private String title;
    private LocalDate createdAt;
}
