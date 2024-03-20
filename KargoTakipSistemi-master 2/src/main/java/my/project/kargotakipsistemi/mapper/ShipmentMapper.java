package my.project.kargotakipsistemi.mapper;

import my.project.kargotakipsistemi.domain.Package;
import my.project.kargotakipsistemi.domain.Shipment;
import my.project.kargotakipsistemi.dto.getRespDtos.ShipmentRespDto;
import my.project.kargotakipsistemi.dto.saveReqDtos.PackageReqDto;
import my.project.kargotakipsistemi.dto.saveReqDtos.ShipmentReqDto;
import my.project.kargotakipsistemi.repositories.CustomerRepository;
import my.project.kargotakipsistemi.repositories.LocationRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShipmentMapper {

    Shipment shipmentReqDtoToShipment(ShipmentReqDto shipmentReqDto, LocationRepository locationRepository, CustomerRepository customerRepository);


    @Mapping(target = "locationByFromLocationIdCity", source = "locationByFromLocationId.city")
    @Mapping(target = "locationByFromLocationIdCountry", source = "locationByFromLocationId.country")
    @Mapping(target = "locationByToLocationIdCity", source = "locationByToLocationId.city")
    @Mapping(target = "locationByToLocationIdCountry", source = "locationByToLocationId.country")
    @Mapping(target = "status", source = "packagestatus.description")
    ShipmentRespDto shipmentToShipmentRespDto(Shipment shipment);


    List<ShipmentRespDto> shipmentToShipmentRespDto(List<Shipment> shipment);

    @Mapping(target = "warehouseByFromWarehouseIdLocationByLocationIdCity", source = "warehouseByFromWarehouseId.locationByLocationId.city")
    @Mapping(target = "warehouseByFromWarehouseIdLocationByLocationIdCountry", source = "warehouseByFromWarehouseId.locationByLocationId.country")
    @Mapping(target = "warehouseByToWarehouseIdLocationByLocationIdCity", source = "warehouseByToWarehouseId.locationByLocationId.city")
    @Mapping(target = "warehouseByToWarehouseIdLocationByLocationIdCountry", source = "warehouseByToWarehouseId.locationByLocationId.country")
    ShipmentRespDto.PackageDto packageToPackageDto(Package pack);

    Package packageReqDtoToPackage(PackageReqDto packageReqDto);

    List<Package> packageReqDtoToPackage(List<PackageReqDto> packageReqDto);

    @BeforeMapping
    default void setLocations(ShipmentReqDto shipmentReqDto, @MappingTarget Shipment shipment, LocationRepository locationRepository, CustomerRepository customerRepository) {
        shipment.setCustomerByCustomerId(customerRepository.findById(shipmentReqDto.getCustomerId()).orElseThrow());
        shipment.setLocationByFromLocationId(locationRepository.findById(shipmentReqDto.getFromLocationId()).orElseThrow());
        shipment.setLocationByToLocationId(locationRepository.findById(shipmentReqDto.getToLocationId()).orElseThrow());
    }

}
