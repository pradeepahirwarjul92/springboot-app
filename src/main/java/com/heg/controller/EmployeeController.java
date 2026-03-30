package com.heg.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heg.dto.EmployeeDTO;
import com.heg.entity.Employee;
import com.heg.projection.EmployeeView;
import com.heg.repository.EmployeeRepository;
import com.heg.service.EmployeeService;

@RestController
@RequestMapping("/api2/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/department/{dept}")
	public ResponseEntity<List<?>> getByDept(
			@PathVariable int dept){
		return ResponseEntity.ok(service.getEmployeesByDept(dept,EmployeeView.class));
	}
	
	@GetMapping("/jpql")
	public ResponseEntity<List<EmployeeDTO>> getEmployees(){
		return ResponseEntity.ok(service.getEmployees());
	}
	
	@GetMapping("/native")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeNative(){
		return ResponseEntity.ok(service.getEmployeeNative());
	}
	

}
