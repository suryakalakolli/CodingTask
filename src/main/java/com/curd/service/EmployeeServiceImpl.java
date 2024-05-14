package com.curd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curd.model.Employee;
import com.curd.model.MultiplePhoneNumber;
import com.curd.repo.EmployeeRepo;
import com.curd.util.EmployeeTax;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
  @Autowired
  private EmployeeRepo repo;
 
 
       @Override
	 public Integer saveEmployeeWithPhoneNumbers(Employee emp, List<String> multiplePhoneNumbers) {
	        List<MultiplePhoneNumber> phoneNumberEntities = new ArrayList<>();
	        for (String phoneNumber : multiplePhoneNumbers) {
	        	MultiplePhoneNumber multiplePhoneNumberEntity = new MultiplePhoneNumber();
	        	multiplePhoneNumberEntity.setPhoneNumber(phoneNumber);
	        	multiplePhoneNumberEntity.setEmployee(emp);
	            phoneNumberEntities.add(multiplePhoneNumberEntity);
	        }
	        emp.setPhoneNumbers(phoneNumberEntities);
	        Integer id	= repo.save(emp).getEId();
			return id;
	    }
	@Override
	 public List<EmployeeTax> calculateTaxDeductionsForCurrentFinancialYear(Date financialYearStart, Date financialYearEnd) {
	        List<Employee> employees = repo.findByDateOfJoiningBetween(financialYearStart, financialYearEnd);
	        
	        List<EmployeeTax> taxInfo = new ArrayList<>();
	        for (Employee employee : employees) {
	        	EmployeeTax empTax = new EmployeeTax();
	                 empTax.setFirstName(employee.getFirstName());
	                empTax.setLastName(employee.getLastName());
	                empTax.setEmployeeCode(null);
	                empTax.setTaxAmount(calculateTax(employee.getESal()));
	                empTax.setYearlySalary(calculateTotalSalary(employee.getDateOfJoining(), employee.getESal(), 12,Double.valueOf(employee.getESal()).intValue()) / 30 );
	               
	        	taxInfo.add(empTax);
            }
            return taxInfo;
                    
	}
	        
	 public double calculateTax(double yearlySalary) {
		 double taxAmount = 0.0;
			if (yearlySalary <= 250000) {
				return taxAmount;
	        } else if (yearlySalary > 250000 && yearlySalary <= 500000) {
	            taxAmount = (yearlySalary - 250000) * 0.05;
	        } else if (yearlySalary > 500000 && yearlySalary <= 1000000) {
	            taxAmount = 12500 + (yearlySalary - 500000) * 0.10;
	        } else {
	            taxAmount = 62500 + (yearlySalary - 1000000) * 0.20;
	        }
	        if (yearlySalary > 2500000) {
	            taxAmount += (yearlySalary - 2500000) * 0.02;
	        }

	        return taxAmount;
	    }
	 public double calculateTotalSalary(Date dateOfJoining, double salary, int numberOfMonths, int daysLossOfPay) {
	        int months = numberOfMonths;
	        if (dateOfJoining != null) {
	            months -= (dateOfJoining.getMonth() + 1);
	        }
	        double totalSalary = salary * months;
	        totalSalary -= (daysLossOfPay * (salary / 30));

	        return totalSalary;
	    }

    
}




