package com.exam.portal.organiser.Controller;

import com.exam.portal.organiser.Model.Exam;
import com.exam.portal.organiser.Model.Organiser;
import com.exam.portal.organiser.OrganiserDetails;
import com.exam.portal.organiser.Repository.ExamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    ExamRepository repo;

    @GetMapping("/organiser/exams")
    public String showExams(Model model){
        Object user=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof OrganiserDetails){
            Organiser org = ((OrganiserDetails) user).getOrg();
            model.addAttribute("exams",repo.findByOrganiserId(org.getId()));
            return "organiser/exam/list";
        }
        else {
            return OrganiserController.LOGIN_ROUTE;
        }
    }

    @GetMapping("/organiser/exams/create")
    public String showCreateExam(Model model){
        model.addAttribute("exam",new Exam());
        return "organiser/exam/create";
    }

    @PostMapping("/organiser/exams/create")
    public String createExam(Exam exam){
        Object user=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof OrganiserDetails){
            exam.setOrganisers(((OrganiserDetails) user).getOrg());
            repo.save(exam);
            return "redirect:/organiser/exams";
        }
        else{
            return OrganiserController.LOGIN_ROUTE;

        }
    }
    @GetMapping("/organiser/exams/edit")
    public String editExam(@RequestParam(name = "id")Long exam_id, Model model){
        Exam oldExam=repo.findById(exam_id).get();
        model.addAttribute("oldExam",oldExam);
        return "organiser/exam/edit";
    }

    @PostMapping("/organiser/exams/edit")
    public String editSaveExam(Exam exam){
        Exam exam1=repo.findById(exam.getId()).get();
        exam.setOrganisers(exam1.getOrganisers());
        repo.save(exam);
        return "redirect:/organiser/exams";
    }

    @GetMapping("/organiser/exams/view")
    public String viewExam(@RequestParam(name = "id",required = true ) Long id,Model model){
        Exam exam=repo.findById(id).get();
        model.addAttribute("exam",exam);
        return "organiser/exam/view";
    }
}
