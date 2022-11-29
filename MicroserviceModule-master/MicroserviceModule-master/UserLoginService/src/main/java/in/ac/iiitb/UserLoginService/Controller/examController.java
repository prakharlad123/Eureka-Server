package in.ac.iiitb.UserLoginService.Controller;

import in.ac.iiitb.UserLoginService.Model.Exam;
import in.ac.iiitb.UserLoginService.Service.examService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class examController {

    @Autowired
    private final examService service;

    public examController(examService service) {
        this.service = service;
    }

    @GetMapping("/exams/create")
    public String showCreateExam(Model model){
        System.out.println("Here");
        model.addAttribute("exam", new Exam());
        return "exam/create";
    }

    @PostMapping("/exams/create")
    public String createExam(Exam exam, Model model){

//        String val = "?examCode=";
        String code = service.createExam(exam);
        model.addAttribute("examCode", code);
//        val.concat(code);

        return "/exam/addUser";
    }

    @PostMapping("/{examCode}/examUser/add")
    public String addExamUser(@PathVariable(name = "examCode")String examCode,@RequestParam(name="name")String name,@RequestParam(name="email")String email,@RequestParam(name="password")String password){
        System.out.println("Here11");
        System.out.println(name);
        System.out.println(examCode);
        service.addExamUser(examCode, name, email, password);
        return "/exam/addUser";
    }
}
