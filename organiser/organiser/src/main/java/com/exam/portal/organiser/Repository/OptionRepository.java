package com.exam.portal.organiser.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exam.portal.organiser.Model.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

}

