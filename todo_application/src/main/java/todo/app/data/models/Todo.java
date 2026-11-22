package todo.app.data.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Todo {
    @Id
    private String id;
    private String Title;
    private String Description;
    @CreatedDate
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean completed;

}
