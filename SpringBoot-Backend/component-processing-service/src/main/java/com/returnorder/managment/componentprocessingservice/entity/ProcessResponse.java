package com.returnorder.managment.componentprocessingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@Table(name = "process_response")
public class ProcessResponse {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid")
    private String responseId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name ="request_process_id",referencedColumnName="requestProcessId")
    private ProcessRequest processRequest;

    private double processingCharge;
    private double packagingAndDeliveryCharge;
    private LocalDate dateOfDelivery;

}
