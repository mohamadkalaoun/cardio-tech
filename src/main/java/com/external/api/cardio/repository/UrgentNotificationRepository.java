package com.external.api.cardio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.external.api.cardio.modals.UrgentNotification;

public interface UrgentNotificationRepository extends JpaRepository<UrgentNotification, Integer> {
	@Query("select p from UrgentNotification p " + "where lower(p.msg) like lower(concat('%', :searchTerm, '%')) " + "or lower(p.patientName) like lower(concat('%', :searchTerm, '%'))")
	List<UrgentNotification> search(@Param("searchTerm") String searchTerm);
	
	UrgentNotification findById(Long id);
	
}