package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import net.guides.springboot2.springboot2jpacrudexample.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

    @GetMapping("/employees/{firstName}")
    public ResponseEntity<Employee> getEmployeeByFirstName(@PathVariable(value = "firstName") String firstName)
        throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(firstName)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + firstName));
        return ResponseEntity.ok().body(employee);
    }
	/**
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    } 
    */
    
    @GetMapping("/employees/create/{firstName}/{lastName}/{emailId}")
    public ResponseEntity<Employee> createEmplyee(@PathVariable(value = "firstName") String firstName,
    		@PathVariable(value = "lastName") String lastName, @PathVariable(value = "emailId") String emailId){
    		Employee employee = new Employee();
	        employee.setEmailId(emailId);
	        employee.setLastName(lastName);
	        employee.setFirstName(firstName);            
            final Employee savedEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok().body(savedEmployee);
        }
    
    
    
    @PutMapping("/employees/{firstName}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "firstName") String firstName,
         @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
         Employee employee = employeeRepository.findById(firstName)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + firstName));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    
    @DeleteMapping("/employees/{firstName}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "firstName") String firstName)
        throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(firstName)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + firstName));

       employeeRepository.delete(employee);
       Map<String, Boolean> response = new HashMap<>();
       response.put("deleted", Boolean.TRUE);
       return response;
    }
    
	
}
