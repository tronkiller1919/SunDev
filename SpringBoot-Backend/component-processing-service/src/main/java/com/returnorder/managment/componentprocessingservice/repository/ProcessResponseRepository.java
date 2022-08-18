package com.returnorder.managment.componentprocessingservice.repository;

import com.returnorder.managment.componentprocessingservice.entity.ProcessResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessResponseRepository extends JpaRepository<ProcessResponse,Long> {

    boolean existsProcessResponseByResponseId(String id);
}
