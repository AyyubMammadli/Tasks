package expressBank.task.Repository;


import expressBank.task.Entity.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDetails, Integer> {

    Optional<UserDetails> findByUsername(String username);

}
