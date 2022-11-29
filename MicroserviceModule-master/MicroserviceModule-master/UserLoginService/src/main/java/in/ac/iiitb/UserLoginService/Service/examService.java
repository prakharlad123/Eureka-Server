package in.ac.iiitb.UserLoginService.Service;

import in.ac.iiitb.UserLoginService.Model.Exam;
import in.ac.iiitb.UserLoginService.Model.User;
import in.ac.iiitb.UserLoginService.Repository.examRepository;

import javax.transaction.Transactional;

import in.ac.iiitb.UserLoginService.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class examService {

    @Autowired
    private final examRepository repo;

    @Autowired
    private final userRepository urepo;

    public examService(examRepository repo, userRepository urepo) {
        this.repo = repo;
        this.urepo = urepo;
    }

    public String createExam(Exam exam) {
        repo.save(exam);
        Exam e = repo.fetchLast();
        String code = e.getExamCode(e.getTitle(),e.getId());
        e.setExamCode(code);
        return code;
    }

    public void addExamUser(String examCode, String name, String email, String password) {
        User u = new User();
        u.setEmail(email);
        u.setName(name);
        u.setPassword(password);
        u.setExam_code(examCode);
        urepo.save(u);
    }
}
