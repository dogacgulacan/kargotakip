package my.project.kargotakipsistemi.repositories;

import my.project.kargotakipsistemi.domain.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrierRepository extends JpaRepository<Carrier, Integer> {
}