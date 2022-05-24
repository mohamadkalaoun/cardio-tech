package com.external.api.cardio.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.external.api.cardio.modals.Device;


public interface DeviceRepository extends JpaRepository<Device, Integer> {
    @Query("select p from Device p " +
            "where lower(p.serialNumber) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(p.deviceName) like lower(concat('%', :searchTerm, '%'))")
      List<Device> search(@Param("searchTerm") String searchTerm);
    
    Device findById(Long id);   

}