package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.custom.exception.InputEmptyException;
import com.example.demo.entity.Employee;
import com.example.demo.repos.EmployeeCrudRepo;

@Service
public class EmployeeService implements EmployeeServiceInterface {

	@Autowired
	private EmployeeCrudRepo employeeCrudRepo;

	@Override
	public Employee addEmployee(Employee employee) {
		if(employee.getName().isEmpty()||employee.getName().length()==0) {
			throw new InputEmptyException("601","Input fields are empty");
		}
		Employee savedEmployee= employeeCrudRepo.save(employee);
		return savedEmployee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> empList=employeeCrudRepo.findAll();
		if(empList.isEmpty()) {
			throw new InputEmptyException("604","list is empty");
		}
		return empList;
	}

	@Override
	public Employee findEmployee(Long id) {
		
		return employeeCrudRepo.findById(id).get();
	}

	@Override
	public void deleteEmpById(Long id) {
	 employeeCrudRepo.deleteById(id);
		
	}

	@Override
	public boolean isEmployeeExist(Long id) {
		return employeeCrudRepo.existsById(id);
	}
}