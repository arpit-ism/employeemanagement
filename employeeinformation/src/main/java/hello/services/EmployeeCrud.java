package hello.services;

import hello.dto.EmployeeDto;
import hello.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeCrud {
    EmployeeDto addEmployee(EmployeeDto empDto);
    List<EmployeeDto> getEmployees();
    EmployeeDto delEmployeeById(Integer empId);
    EmployeeDto getEmployeeById(Integer empId);
    EmployeeDto updateEmployeeForFirstNameSalary(Integer empId,String firstName,Integer salary);
    //deleteEmployee(Integer emp_id);
    //getEmployee()
}
