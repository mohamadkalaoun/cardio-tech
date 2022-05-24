package com.external.api.cardio.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.external.api.cardio.modals.Person;


public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("select p from Person p " +
            "where lower(p.Fname) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(p.Lname) like lower(concat('%', :searchTerm, '%'))")
      List<Person> search(@Param("searchTerm") String searchTerm);
    
    Person findById(Long id);   

}