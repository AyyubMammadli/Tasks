package expressBank.task.Service;

import expressBank.task.Entity.Interviewee;
import expressBank.task.Repository.IntervieweeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IntervieweeService {


    private final IntervieweeRepository intervieweeRepository;


    public Interviewee save(Interviewee interviewee) {

        return intervieweeRepository.save(interviewee);
    }


    public Interviewee getById(Integer id) {
        Optional<Interviewee> inter = intervieweeRepository.findById(id);

        return inter.orElseGet(() -> new Interviewee(null, "Interviewee", "does not exit"));

    }


    public Interviewee findByFirstNameAndLastName(String firstName, String lastName) {
        Optional<Interviewee> inter = intervieweeRepository.findByFirstNameAndLastNameAndStatus(firstName, lastName, true);

        return inter.orElseGet(() -> new Interviewee(null, "Interviewee", "does not exit"));

    }

    public List<Interviewee> findByFirstNameOrLastName(String firstName, String lastName) {
        return (List<Interviewee>) intervieweeRepository.findByFirstNameOrLastName(firstName, lastName);
    }


    public List<Interviewee> getIntervieweeList() {
        return (List<Interviewee>) intervieweeRepository.findAll();
    }


    public Object findAllByFirstName(String firstName) {
        return (List<Interviewee>) intervieweeRepository.findAllByFirstName(firstName);
    }

    public boolean exist(Integer id) {
        Optional<Interviewee> inter = intervieweeRepository.findById(id);
        return inter.isPresent();
    }

    public Interviewee delete(Integer id) {
        intervieweeRepository.deleteById(id);

        Optional<Interviewee> inter = intervieweeRepository.findById(id);

        Interviewee interviewee = inter.orElseGet(() -> new Interviewee(null, "Interviewee", "does not exit"));
        return interviewee;

    }

}

