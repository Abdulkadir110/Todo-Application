package todo.app.data.repostories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import todo.app.data.models.Todo;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {

}
