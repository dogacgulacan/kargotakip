package my.project.kargotakipsistemi.repositories;

import my.project.kargotakipsistemi.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}