package com.exam.portal.organiser.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exam.portal.organiser.Model.Organiser;

@Repository
public interface OrganiserRepository extends JpaRepository<Organiser, Long> {

    @Query("SELECT u FROM Organiser u WHERE u.email = ?1")
    Organiser findByEmail(String email);

}
