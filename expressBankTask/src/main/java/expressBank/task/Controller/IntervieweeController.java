package expressBank.task.Controller;

import expressBank.task.Entity.Interviewee;
import expressBank.task.Service.IntervieweeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/interviewee")
public class IntervieweeController {

    private final IntervieweeService intervieweeService;

    public IntervieweeController(IntervieweeService intervieweeService) {
        this.intervieweeService = intervieweeService;
    }
    Logger logger = LoggerFactory.getLogger(IntervieweeController.class);

    @GetMapping("/list")
    public String getInterviewees(Model model) {

        List<Interviewee> interviewees = intervieweeService.getIntervieweeList();

        model.addAttribute("interviewees", interviewees);
        logger.info("Everything is okay in /list ");

        return "interviewee_list";
    }

    @GetMapping("/create") //creating new interviewee
    public String getNewIntervieweeForm(Model model) {
        model.addAttribute("interviewee", new Interviewee());
        logger.error("Error is occured while trying to /create interviewee");
        return "interviewee_form";
    }

    @GetMapping("/edit/{id}") //updating the data of interviewee
    public String getUpdateIntervieweeForm(Model model, @PathVariable Integer id) {
logger.info("User is in updating page");
try {
    model.addAttribute("interviewee", intervieweeService.getById(id));
}
catch(Exception e) {
    logger.error("Be careful while updating! Error is "+e.getMessage());
}

        return "interviewee_form";


    }

    @PostMapping("/save") //saving updated data
    public String saveInterviewee(Model model, @Valid Interviewee interviewee, BindingResult bindingResult) {

          if (bindingResult.hasErrors()){

              return "interviewee_form";

           }


        Interviewee updatedInterviewee = intervieweeService.save(interviewee);
        model.addAttribute("interviewee", updatedInterviewee);

        logger.info("Everything is okay while trying to save changes");
        logger.error("Error is occured while trying to /save changes");

        return "interviewee_info";

    }


    @GetMapping("/{id}") //getting interviewee by id
    public String getInterviewee(Model model, @PathVariable Integer id) throws Exception {

        if (!intervieweeService.exist(id)){
            throw new Exception("Interviewee not found");
        }
        model.addAttribute("interviewee", intervieweeService.getById(id));

        logger.info("Everything is okay while trying to search by /id");
        logger.error("Error is occured while trying to search by /id");

        return "interviewee_info";
    }


    @GetMapping(value = "/delete/{id}") // It is for deleting by id.
                                      // We should use here DeleteMapping,but we are checking it with browser, not postman, so we should use here Get in order to see results.
    public String deleteInterviewee(Model model, @PathVariable Integer id) throws Exception {
try {
    if (!intervieweeService.exist(id)) {
        throw new Exception("Interviewee not found");
    } else {
        intervieweeService.delete(id);
        model.addAttribute("interviewee", intervieweeService.delete(id));
    }


}
catch(Exception ignore){
    System.out.println("interviewee is deleted!");
}
        return "redirect:/interviewee/list";
    }

    @GetMapping("/both/{firstName}/{lastName}") //searching for a name and surname
    public String findForNameAndSurname(Model model, @PathVariable String firstName, @PathVariable String lastName) {

        model.addAttribute("interviewee", intervieweeService.findByFirstNameAndLastName(firstName, lastName));
        logger.info("Everything is okay while trying to search by /both/firstName/lastName");
        logger.error("Error is occured while trying to search by /both/firstName/lastName");
        return "interviewee_info";
    }

    @GetMapping("/{firstName}/or/{lastName}") //searching for a name or surname
    public String findForNameOrSurname(Model model, @PathVariable String firstName, @PathVariable String lastName) {
        model.addAttribute("interviewees", intervieweeService.findByFirstNameOrLastName(firstName, lastName));
        logger.info("Everything is okay while trying to search by /firstName/or/lastName");
        logger.error("Error is occured while trying to search by /firstName/or/lastName");
        return "interviewee_list";
    }

    @GetMapping("/name/{firstName}") //searching for a name
    public String findForName(Model model, @PathVariable String firstName) {
        model.addAttribute("interviewees", intervieweeService.findAllByFirstName(firstName));
        logger.info("Everything is okay while trying to search by name/firstName");
        logger.error("Error is occured while trying to search by name/firstName");
        return "interviewee_list";
    }




}

