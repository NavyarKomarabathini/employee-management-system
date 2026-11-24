package com.example.employee.service;

import com.example.employee.dto.EmployeeRequest;
import com.example.employee.entity.Employee;
import com.example.employee.entity.Department;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.DepartmentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository) {

        this.employeeRepository = employeeRepository;

        this.departmentRepository = departmentRepository;
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employee not found"
                    )
                );
    }

    public Employee createEmployee(EmployeeRequest request) {

        Department department = departmentRepository
                .findById(request.departmentId())
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Department not found"
                    )
                );

        Employee employee = new Employee();

        employee.setName(request.name());
        employee.setEmail(request.email());
        employee.setSalary(request.salary());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(
            Long id, EmployeeRequest request) {

        Employee employee = getEmployeeById(id);

        Department department = departmentRepository
                .findById(request.departmentId())
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Department not found"
                    )
                );

        employee.setName(request.name());
        employee.setEmail(request.email());
        employee.setSalary(request.salary());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {

        Employee employee = getEmployeeById(id);

        employeeRepository.delete(employee);
    }

    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByDepartment(
            Long departmentId) {

        if (!departmentRepository.existsById(departmentId)) {

            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Department not found"
            );
        }

        return employeeRepository
                .findByDepartment_Id(departmentId);
    }
}