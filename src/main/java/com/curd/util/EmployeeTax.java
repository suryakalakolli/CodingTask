package com.curd.util;

import lombok.Data;

@Data
public class EmployeeTax {
	    private String employeeCode;
	    private String firstName;
	    private String lastName;
	    private double yearlySalary;
	    private double taxAmount;
	    private double cessAmount;
}
