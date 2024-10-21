package DogMate.User.Registration.Authentication.and.Authorization.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to DogMate";
    }

    @GetMapping("/feed")
    public String feed() {
        return "Feed page";
    }
}
