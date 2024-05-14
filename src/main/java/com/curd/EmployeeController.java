package com.curd;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curd.model.Employee;
import com.curd.service.IEmployeeService;
import com.curd.util.EmployeeTax;


@RestController
@RequestMapping("/emp")
public class EmployeeController {
@Autowired
private IEmployeeService empservice;

//save 
@PostMapping("/save")
public ResponseEntity<String> saveEmployee(@Valid @RequestBody Employee emp,@RequestParam List<String> phoneNumbers ){
	Integer id = empservice.saveEmployeeWithPhoneNumbers(emp, phoneNumbers);
	return new ResponseEntity<String>("Employee '"+id+"' saved",HttpStatus.OK);
}

@GetMapping("/tax")
public ResponseEntity<List<EmployeeTax>> getTaxForCurrentFinancialYear(

        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date financialYearStart,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date financialYearEnd) {
	List<EmployeeTax> taxEmpDet = empservice.calculateTaxDeductionsForCurrentFinancialYear(financialYearStart, financialYearEnd);
//	 Date financialYearStart = calculateFinancialYearStart();
//     Date financialYearEnd = calculateFinancialYearEnd();
	return  new ResponseEntity<List<EmployeeTax>>( taxEmpDet,HttpStatus.OK);
	
	
	
	
	
	
}

//	
//}



}
