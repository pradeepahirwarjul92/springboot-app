package com.heg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.heg.entity.Employee;

public interface EmployeeNativeRepository extends JpaRepository<Employee, Long> {

	//READ ALL
	@Query(value = "select * from employee_details", nativeQuery = true)
	List<Employee> getAllEmployees();
	
	@Query(nativeQuery = true, value = "CALL get_all_employees()")
	List<Employee> getAllEmployeeProcedure();
	
}
