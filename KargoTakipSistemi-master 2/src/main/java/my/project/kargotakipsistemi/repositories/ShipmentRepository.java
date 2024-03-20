package my.project.kargotakipsistemi.repositories;

import my.project.kargotakipsistemi.domain.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
    Optional<Shipment> findByTrackingNumber(String trackingNumber);

    @Query("select s from Shipment s where s.packagestatus.statusId = ?1")
    List<Shipment> findByPackagestatus_StatusId(int statusId);

    @Query("select s from Shipment s where s.packagestatusStatusId <> ?1")
    List<Shipment> findByPackagestatusStatusIdNot(Integer packagestatusStatusId);

}