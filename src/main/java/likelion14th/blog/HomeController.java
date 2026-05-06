package likelion14th.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome Likelion14th to String MVC!");
        return "home";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam(name = "name", defaultValue = "Likelion14th") String name, Model model) {
        String greeting = "Hello, " + name + "!";
        model.addAttribute("greeting", greeting);
        return "greet";
    }

    @GetMapping("/api/hello")
    @ResponseBody
    public String helloApi(){
        return "Hello Likelion14th. String MVC API!";
    }
}
