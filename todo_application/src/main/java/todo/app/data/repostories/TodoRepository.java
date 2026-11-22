package todo.app.data.repostories;

import org.springframework.data.mongodb.repository.MongoRepository;
import todo.app.data.models.Todo;


public interface TodoRepository extends MongoRepository<Todo, String> {

}
