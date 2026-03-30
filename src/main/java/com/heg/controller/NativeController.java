package com.heg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heg.entity.Employee;
import com.heg.service.NativeEmployeeService;

@RestController
@RequestMapping("/employee")
public class NativeController {
	@Autowired
	private NativeEmployeeService service;
	
	@GetMapping("/all")
	public List<Employee> getAllEmployees(){
		return service.getAllEmployees();
	}
	
	@GetMapping("/proc")
	public List<Employee> getEmployeeFromProc(){
		return service.getEmployees();
	}

}
