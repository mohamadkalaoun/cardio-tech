package com.external.api.cardio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.external.api.cardio.modals.Study;



public interface StudyRepository extends JpaRepository<Study, Integer> {
    @Query("select p from Study p " +
            "where lower(p.deviceSerialNumber) like lower(concat('%', :searchTerm, '%')) " +
			"or lower(p.patientName) like lower(concat('%', :searchTerm, '%'))"+
            "or lower(p.deviceName) like lower(concat('%', :searchTerm, '%'))")
      List<Study> search(@Param("searchTerm") String searchTerm);
    
//    @Query("select p from Study p " + "where p.id = 'givenId'")
    Study findById(int id);//(@Param("givenId") int id);   

}

