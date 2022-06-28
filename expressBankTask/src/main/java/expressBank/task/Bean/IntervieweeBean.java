package expressBank.task.Bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


public class IntervieweeBean {

    private String firstName;

    private String lastName;

    private int id;

    public IntervieweeBean() {
    }

    public IntervieweeBean(int id,String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id=id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
