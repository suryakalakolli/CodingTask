package com.curd.model;

import javax.validation.constraints.NotEmpty;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class MultiplePhoneNumber {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotEmpty(message = "phoneNumber should be enter")
    private String phoneNumber;
    
    // Many phone numbers can belong to one user
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

}
