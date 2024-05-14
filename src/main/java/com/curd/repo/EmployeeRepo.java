package com.curd.repo;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curd.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Integer>{

	 List<Employee> findByDateOfJoiningBetween(Date startDate, Date endDate);

	

}
