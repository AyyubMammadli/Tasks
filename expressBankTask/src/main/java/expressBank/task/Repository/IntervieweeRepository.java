package expressBank.task.Repository;


import expressBank.task.Entity.Interviewee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IntervieweeRepository extends CrudRepository<Interviewee, Integer> {



    Optional<Interviewee> findByFirstNameAndLastNameAndStatus(String firstName, String lastName, Boolean status);


    Iterable<Interviewee> findByFirstNameOrLastName(String firstName, String lastName);

    List<Interviewee> findAllByFirstName(String name);



}
