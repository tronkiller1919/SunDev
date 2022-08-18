package com.returnorder.managment.componentprocessingservice.repository;

import com.returnorder.managment.componentprocessingservice.entity.ProcessRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRequestRepository  extends JpaRepository<ProcessRequest,Long> {

}
