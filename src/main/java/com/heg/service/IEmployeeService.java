package com.heg.service;

import java.util.List;

import com.heg.dto.EmployeeDTO;
import com.heg.projection.EmployeeView;

public interface IEmployeeService {
	
	<T> List<T> getEmployeesByDept(int dept,Class<T> type);
	
	List<EmployeeDTO> getEmployees();

	List<EmployeeDTO> getEmployeeNative();
}
