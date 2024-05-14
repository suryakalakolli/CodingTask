package com.curd.service;

import java.util.Date;
import java.util.List;

import com.curd.model.Employee;
import com.curd.util.EmployeeTax;

public interface IEmployeeService {
	
 Integer saveEmployeeWithPhoneNumbers(Employee emp, List<String> multiplePhoneNumbers);
 List<EmployeeTax> calculateTaxDeductionsForCurrentFinancialYear(Date financialYearStart, Date financialYearEnd) ;
     
}
