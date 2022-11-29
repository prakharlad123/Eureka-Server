package in.ac.iiitb.UserLoginService.Repository;

import in.ac.iiitb.UserLoginService.Model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

public interface examRepository extends JpaRepository<Exam, Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM exams ORDER BY ID DESC LIMIT 1")
    Exam fetchLast();

    @Override
    Optional<Exam> findById(Long aLong);
}
