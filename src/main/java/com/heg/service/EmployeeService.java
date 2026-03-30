package com.heg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heg.dto.EmployeeDTO;
import com.heg.repository.EmployeeRepository;
import java.math.BigInteger;



@Service
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	private EmployeeRepository repository;

	public <T> List<T> getEmployeesByDept(int dept, Class<T> type){
		return repository.findByDepartment(dept,type);
	}

	@Override
	public List<EmployeeDTO> getEmployees() {
		// TODO Auto-generated method stub
		return repository.getEmployees();
	}

	@Override
	public List<EmployeeDTO> getEmployeeNative() {
		List<Object[]> rows = repository.getEmployeeNative();
		List<EmployeeDTO> list= new ArrayList<>();
		
		for(Object[] row : rows) {
			String name =(String) row[0];
			int dept = ((BigInteger) row[1]).intValue();
			
			list.add(new EmployeeDTO(name, dept));
		}
		
		
		return list;
	}
	
	
	
	

	

}
