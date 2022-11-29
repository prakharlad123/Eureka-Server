package in.ac.iiitb.UserLoginService.Controller;

import in.ac.iiitb.UserLoginService.Model.Exam;
import in.ac.iiitb.UserLoginService.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class userController {

    @Autowired
    private final userService service;

    public userController(userService service) {
        this.service = service;
    }

    @PostMapping("user/login")
    public String userLogin(@RequestParam(name = "examcode")String examCode, Model model){

        Exam exam = service.userLogin(examCode);
        if(exam == null) {
            model.addAttribute("msg","ExamCode is Invalid or Not Present");
            return "error/err";
        }
        else {
            model.addAttribute("exam", exam);
            return "/user/login";
        }

    }

    @PostMapping("{examcode}/login")
    public String userLogin(@PathVariable(name="examcode")String examcode, @RequestParam(name="email")String email, @RequestParam(name="password")String password, Model model){

        System.out.println(examcode);
        System.out.println(email);
        String msg = service.userLogin(examcode, email, password);
        model.addAttribute("msg", msg);

        if(msg == "Success") {
            model.addAttribute("examcode", examcode);
            return "exam/instructions";
        }
        else{
            return "error/err";
        }

    }
}
