package hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}

