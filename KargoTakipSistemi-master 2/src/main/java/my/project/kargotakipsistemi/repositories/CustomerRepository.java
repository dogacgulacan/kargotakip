package my.project.kargotakipsistemi.repositories;

import my.project.kargotakipsistemi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}