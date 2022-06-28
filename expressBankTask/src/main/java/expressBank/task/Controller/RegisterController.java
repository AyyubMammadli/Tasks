package expressBank.task.Controller;

import expressBank.task.Entity.RegistrationDto;
import expressBank.task.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @GetMapping ("")
    public String getRegisterPage(Model model){
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("user", registrationDto);
        logger.info("Registration.html page is opening. RegistrationDto is passed!");
        return "register";
    }

    @PostMapping("")
    public String registerUser(RegistrationDto registrationDto) {
        logger.info("RegistrationDto is accepted. Let's create user!");
        try {
            userService.register(registrationDto);
        }catch (Exception e) {
            logger.error("Following error happens: " + e.getMessage());
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}
