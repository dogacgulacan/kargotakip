package my.project.kargotakipsistemi.services;

import my.project.kargotakipsistemi.dto.getRespDtos.ShipmentRespDto;
import my.project.kargotakipsistemi.dto.saveReqDtos.PackageReqDto;
import my.project.kargotakipsistemi.dto.saveReqDtos.ShipmentReqDto;
import my.project.kargotakipsistemi.dto.updateReqDtos.ShipmentUpdateReqDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.util.List;

public interface ShipmentService {

    String saveShipment(ShipmentReqDto shipmentReqDto);

    String updateShipment(ShipmentUpdateReqDto shipmentUpdateReqDto);

    ShipmentRespDto getShipment(String trackingNumber);

    List<ShipmentRespDto> getShipmentByPackageStatusId(Integer packageStatusId);

    List<ShipmentRespDto> getNotCompletedShipmentsByPackageStatusId(Integer packageStatusId);

    ShipmentRespDto getShipmentById(Integer integer);

    String savePackagesToShipment(String trackingId, @RequestBody PackageReqDto[] packageReqDtos);

    void saveShipmentEstimatedDeliveryDate(int shipmentId, Date estimatedDeliveryDate, int fromWarehouseId, int toWarehouseId, Date deliveredDate);
}
