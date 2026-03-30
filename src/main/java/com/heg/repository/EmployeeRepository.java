package com.heg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.heg.dto.EmployeeDTO;
import com.heg.entity.Employee;



public interface EmployeeRepository  extends JpaRepository<Employee, Long>{
	
	<T> List<T> findByDepartment(int department,Class<T> type);
	
	//JPQL DTO Projection
	@Query("SELECT new com.heg.dto.EmployeeDTO(e.name,e.department) from Employee e ")
	List<EmployeeDTO> getEmployees();
	
	//Native Query 
	
	@Query(value = "SELECT name,dept_id FROM employee_details", nativeQuery = true)
	List<Object[]> getEmployeeNative();
	
}
