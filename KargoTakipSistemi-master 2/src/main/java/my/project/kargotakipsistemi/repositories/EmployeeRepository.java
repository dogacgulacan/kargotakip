package my.project.kargotakipsistemi.repositories;

import my.project.kargotakipsistemi.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}