package in.ac.iiitb.UserLoginService.Model;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exams")
public class Exam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, name = "title")
    private String title;

    @Column(length = 100, name = "description")
    private String description;

    @Column(length = 150, name = "instructions")
    private String instructions;

    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    @Column(nullable = false, name = "start_date")
    private Date startDate;

    @Column(nullable=false,length=5,name = "marks")
    private int marksOfEachQuestion ;

    @Column(nullable=false,length=5,name = "time")
    private int examTime;

    @Column(nullable = false, name = "exam_code")
    private String examCode = "?";

    public String getExamCode(){
        return examCode;
    }

    public String getExamCode(String title, Long id){
        String[] titleList=title.split(" ");
        StringBuilder Code=new StringBuilder();
        for (String t:titleList) {
            char c=t.charAt(0);
            if(Character.isLetter(c) || Character.isDigit(c)){
                Code.append(c);
            }
        }
        String[] letters={"EXAM","XAM","MOK","LT","MDS","FPA","KU","POU","KIH","RTU"};
        if(Code.toString().equals("")){
            int index= (int) (id % 10);
            Code.append(letters[index]);
        }
        return Code.toString().toUpperCase()+"-"+id.toString();
    }

    public void setExamCode(String examCode) {this.examCode = examCode;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getMarksOfEachQuestion() {
        return marksOfEachQuestion;
    }

    public void setMarksOfEachQuestion(int marksOfEachQuestion) {
        this.marksOfEachQuestion = marksOfEachQuestion;
    }

    public int getExamTime() {
        return examTime;
    }

    public void setExamTime(int timeOfEachQuestion) {
        this.examTime = timeOfEachQuestion;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isOver() {
        long time=this.getExamTime()*60*1000;
        long currentTime=new Date().getTime();
        long examTime =this.getStartDate().getTime();
        return examTime+time <= currentTime;
    }
}
