package in.ac.iiitb.UserLoginService.Service;

import in.ac.iiitb.UserLoginService.Model.Exam;
import in.ac.iiitb.UserLoginService.Model.User;
import in.ac.iiitb.UserLoginService.Repository.examRepository;
import in.ac.iiitb.UserLoginService.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class userService {

    @Autowired
    private final examRepository eRepo;

    @Autowired
    private final userRepository uRepo;

    public userService(examRepository eRepo, userRepository uRepo) {
        this.eRepo = eRepo;
        this.uRepo = uRepo;
    }

    public Exam userLogin(String examCode) {

        if (checkValidExamCode(examCode)) {

            System.out.println("Yes Valid Code");
            Long examId = Long.valueOf(examCode.split("-")[1]);
            if(eRepo.findById(examId).isPresent()) {
                System.out.println("Yes Present");
                Exam exam = eRepo.findById(examId).get();
                System.out.println(exam.getTitle());
                return exam;
            }
        }

        return null;
    }

    public String userLogin(String examCode, String email, String password) {

        if(checkValidExamCode(examCode)){
            User user= uRepo.findByEmail(email);
            if(user==null){
                return "Email Does Not Exist";
            }
            Long exam_id= Long.valueOf(examCode.split("-")[1]);
            Exam exam= eRepo.findById(exam_id).get();
            Long currentTime=new Date().getTime();
            Long examTime = exam.getStartDate().getTime();
            if(exam.isOver()){
                //Exam is over Sorry you are late

                return "Exam is over Sorry you are late";
            }
            if(examTime-currentTime>900000){
                // You can login only before 15 mins of exam start time

                return "You can login only before 15 mins of exam start time";
            }
            if(currentTime-examTime>1800000){
                // You can login only after 30 mins of exam start time
                return "You can login only after 30 mins of exam start time";
            }


            if(!user.getExam_code().equals(examCode) || !user.getPassword().equals(password)){
                // Invalid Email or password
                return "Invalid Password";
            }
                return "Success";

        }else{
            return "Invalid Exam Code";
        }
    }

    private boolean checkValidExamCode(String examCode) {

        System.out.println(examCode);
        Long examId = Long.valueOf(examCode.split("-")[1]);
        System.out.println(examId);
        if(eRepo.findById(examId).isPresent()){
            Exam exam = eRepo.findById(examId).get();
            return exam.getExamCode().equals(examCode);
        }
        else{
            return  false;
        }
    }
}
