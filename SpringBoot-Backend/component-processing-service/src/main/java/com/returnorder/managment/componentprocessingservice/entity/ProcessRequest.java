package com.returnorder.managment.componentprocessingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@Table(name = "process_request")
public class ProcessRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long requestProcessId;

    private String customerName;
    private String contactNumber;
    private String componentType;
    private String componentName;
    private Integer quantity;

    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "processRequest")
    private ProcessResponse processResponse;

}
