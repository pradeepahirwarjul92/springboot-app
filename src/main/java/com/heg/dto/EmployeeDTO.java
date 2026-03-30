package com.heg.dto;

public class EmployeeDTO {

	 	private String name;
	    private int department;
	    

	   

	    public EmployeeDTO(String name,int department){
	        this.name=name;
	        this.department=department;
	       
	    }
	    
	
	    
	    public String getName(){
	        return name;
	    }

	    public int getDepartment(){
	        return department;
	    }


	    
	

}
