package com.heg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heg.entity.Employee;
import com.heg.repository.EmployeeNativeRepository;
@Service
public class NativeEmployeeService {
	
	@Autowired
	private EmployeeNativeRepository repo;
	
	public List<Employee> getAllEmployees(){
		return repo.getAllEmployees();
	}
	
	
	public List<Employee> getEmployees(){
		return repo.getAllEmployeeProcedure();
	}

}
