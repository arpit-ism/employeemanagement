package hello.controller;

import hello.dto.EmployeeDto;
import hello.services.EmployeeCrud;
import hello.services.impl.EmployeeCrudImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import hello.repository.EmployeeRepo;
import hello.model.Employee;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class EmployeeController {
    @Autowired
    private EmployeeCrud empCrud;

    @RequestMapping(method = RequestMethod.POST,path = "/employee")
    public @ResponseBody EmployeeDto addEmployee(@RequestBody EmployeeDto empDto){
        return empCrud.addEmployee(empDto);
    }

    @RequestMapping(method = RequestMethod.GET,path = "/employees")
    public @ResponseBody
    List<EmployeeDto> getAllEmployeesC(){
        return empCrud.getEmployees();
    }



    @RequestMapping(method = RequestMethod.GET,path = "/employee")
    public @ResponseBody EmployeeDto getAllEmployees(@RequestParam Integer empId){
        return empCrud.getEmployeeById(empId);
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "/employee")
    public @ResponseBody EmployeeDto deleteEmployee(@RequestParam Integer empId){
        return empCrud.delEmployeeById(empId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/updation")
    public @ResponseBody EmployeeDto updateEmployee(@RequestParam Integer empId,@RequestParam String firstName,@RequestParam Integer salary){
        return empCrud.updateEmployeeForFirstNameSalary(empId,firstName,salary);
    }

}
