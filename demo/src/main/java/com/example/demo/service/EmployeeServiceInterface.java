package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Employee;

public interface EmployeeServiceInterface {

	public Employee addEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public Employee findEmployee(Long id);

	public void deleteEmpById(Long id);

	public boolean isEmployeeExist(Long id);

}
