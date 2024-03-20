package my.project.kargotakipsistemi.repositories;

import my.project.kargotakipsistemi.domain.Warehouse;
import my.project.kargotakipsistemi.dto.getRespDtos.WarehouseRespDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {


    @Query("""
            SELECT new my.project.kargotakipsistemi.dto.getRespDtos.WarehouseRespDto(
                    w.warehouseId,
                    w.name,
                    w.locationId,
                    l.city,
                    l.country
                )
                FROM Warehouse w
                LEFT JOIN Location l ON w.locationId = l.locationId
            """)
    List<WarehouseRespDto> findCustomAll();
}