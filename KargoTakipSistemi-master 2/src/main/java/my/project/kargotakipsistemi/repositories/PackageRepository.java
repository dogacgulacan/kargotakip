package my.project.kargotakipsistemi.repositories;

import my.project.kargotakipsistemi.domain.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Integer> {
}