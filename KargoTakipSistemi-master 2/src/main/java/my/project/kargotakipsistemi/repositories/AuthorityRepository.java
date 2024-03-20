package my.project.kargotakipsistemi.repositories;

import my.project.kargotakipsistemi.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}