package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.Employee;
import com.example.demo.repos.EmployeeCrudRepo;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeTests {
	
	@Autowired
	private EmployeeCrudRepo employeeCrudRepo;
	
	
	@Test
	@Rollback(false)
	@Order(1)
	public void addEmployee() {
		Employee employee=new Employee("Tom");
		Employee addedEmployee=employeeCrudRepo.save(employee);
		assertNotNull(addedEmployee);
	}
	
	@Test
	@Order(2)
	public void getEmpById() {
		Long id=(long) 1;
		Employee employee=employeeCrudRepo.getById(id);
		assertEquals(employee.getId(), id);		
	}
	
	@Test
	@Order(3)
	public void getAllEmployees() {
		List<Employee> employees= employeeCrudRepo.findAll();
		for(Employee employee:employees) {
			System.out.println(employee);
		}
		assertTrue(employees.size()>0);
	}
	
	@Test
	@Rollback(false)
	@Order(4)
	public void updateEmployee() {
		String name="Eve";
		Employee employee=new Employee(name);
		employee.setId((long) 1);
		employeeCrudRepo.save(employee);
		Employee updatedEmployee=employeeCrudRepo.getById((long) 1);
		assertSame(employee.getName(), updatedEmployee.getName());
		
	}
	
	
	@Test
	@Order(5)
	@Rollback(false)
	public void deleteEmpById() {
		Long id=(long) 1;
		boolean isExistBeforeDelete=employeeCrudRepo.findById(id).isPresent();
		employeeCrudRepo.deleteById(id);
		boolean notExistAfterDelete=employeeCrudRepo.findById(id).isPresent();
		assertTrue(isExistBeforeDelete);
		assertFalse(notExistAfterDelete);
	}

}
