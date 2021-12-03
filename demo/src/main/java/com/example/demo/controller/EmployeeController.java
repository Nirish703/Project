package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.custom.exception.EmployeeNotFoundException;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeServiceInterface;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceInterface employeeServiceInterface;
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllEmployees(){
		List<Employee> employeeList=employeeServiceInterface.getAllEmployees();
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	
	@PostMapping("/emp")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee employee){
		Employee employeeSaved=employeeServiceInterface.addEmployee(employee);
		return new ResponseEntity<>("Employee saved with Id: "+employeeSaved.getId(),HttpStatus.CREATED);
	}
	
	@GetMapping("/emp/{id}")
	public ResponseEntity<Object> getEmpById(@PathVariable("id") Long id){
		Employee employeeretrieved=employeeServiceInterface.findEmployee(id);
		return new ResponseEntity<>(employeeretrieved,HttpStatus.OK);	
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee){
		Employee employeeSaved=employeeServiceInterface.addEmployee(employee);
		return new ResponseEntity<>(employeeSaved,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/emp/{id}")
	public ResponseEntity<Object> deleteEmpById(@PathVariable("id") Long id){
		boolean isEmployeeExist=employeeServiceInterface.isEmployeeExist(id);
		if(isEmployeeExist) {
			employeeServiceInterface.deleteEmpById(id);
			return new ResponseEntity<>("Employee deleted successfully",HttpStatus.ACCEPTED);
		}
		else {
			throw new EmployeeNotFoundException();
		}
		
	}
	

}
