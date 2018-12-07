package hello.services.impl;
import hello.dto.EmployeeDto;
import hello.model.Employee;
import hello.repository.EmployeeRepo;
import hello.services.EmployeeCrud;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeCrudImpl implements EmployeeCrud{

    @Autowired
    public EmployeeRepo employeeRepo;

    @Override
    @Transactional
    public EmployeeDto addEmployee(EmployeeDto empDto){
        Employee emp = new Employee();
        BeanUtils.copyProperties(empDto, emp);
        employeeRepo.save(emp);
        return empDto;
    }

    @Override
    @Transactional
    public List<EmployeeDto> getEmployees(){
        List<EmployeeDto> allEmpDto = new ArrayList<>();//How to declare iterable
        List<Employee> allEmp = employeeRepo.findAll();
        for (int i = 0; i < allEmp.size(); i++) {
            EmployeeDto empDto = new EmployeeDto();
            BeanUtils.copyProperties(allEmp.get(i), empDto);
            allEmpDto.add(empDto);
        }
        return allEmpDto;
    }

    @Override
    @Transactional
    public EmployeeDto delEmployeeById(Integer empId){
        Optional<Employee> employeeOptional = employeeRepo.findById(empId);
        try {
            if (employeeOptional.isPresent()){
                Employee emp = employeeOptional.get();
                employeeRepo.delete(emp);
                EmployeeDto empDto = new EmployeeDto();
                BeanUtils.copyProperties(emp,empDto);
                return empDto;

            }
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
        return null;
    }

    @Override
    @Transactional
    public EmployeeDto getEmployeeById(Integer empID){
        EmployeeDto empDto = new EmployeeDto();
        Employee emp;
        Optional<Employee> employeeOptional = employeeRepo.findById(empID);
        if (employeeOptional.isPresent()){
            emp = employeeOptional.get();
            BeanUtils.copyProperties(emp,empDto);
            return empDto;
        }
        return null;
    }

    //delete the original entry and save it again with changed values is the method I could find best?

    @Override
    @Transactional
    public EmployeeDto updateEmployeeForFirstNameSalary(Integer empId,String firstName,Integer salary){
        Employee emp;
        EmployeeDto empDto = new EmployeeDto();
        Optional<Employee> employeeOptional = employeeRepo.findById(empId);
        if(employeeOptional.isPresent()){
            emp = employeeOptional.get();
            emp.setFirstName(firstName);
            emp.setSalary(salary);
            employeeRepo.save(emp);
            BeanUtils.copyProperties(emp, empDto);
            return empDto;
        }
        else {
            return null;
        }
    }

}
