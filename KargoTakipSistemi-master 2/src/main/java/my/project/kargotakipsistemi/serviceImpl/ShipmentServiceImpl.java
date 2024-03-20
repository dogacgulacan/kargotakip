package my.project.kargotakipsistemi.serviceImpl;

import lombok.RequiredArgsConstructor;
import my.project.kargotakipsistemi.dto.getRespDtos.ShipmentRespDto;
import my.project.kargotakipsistemi.dto.saveReqDtos.PackageReqDto;
import my.project.kargotakipsistemi.dto.saveReqDtos.ShipmentReqDto;
import my.project.kargotakipsistemi.dto.updateReqDtos.ShipmentUpdateReqDto;
import my.project.kargotakipsistemi.mapper.ShipmentMapper;
import my.project.kargotakipsistemi.repositories.*;
import my.project.kargotakipsistemi.services.ShipmentService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * ShipmentServiceImpl is a service class that implements the ShipmentService interface.
 * It provides methods to save and retrieve shipments.
 */
@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentMapper shipmentMapper;
    private final LocationRepository locationRepository;
    private final CustomerRepository customerRepository;
    private final PackagestatusRepository packagestatusRepository;
    private final UserRepository userRepository;
    private final WarehouseRepository warehouseRepository;
    private final PackageRepository packageRepository;

    /**
     * This method saves the shipment to the database.
     * It first retrieves the authenticated user's ID and sets it as the customer ID of the shipment.
     * Then it maps the ShipmentReqDto to a Shipment entity and generates a random tracking number for the shipment.
     * It sets the package status to "not assigned" and saves the shipment to the database.
     * Finally, it returns the tracking number of the saved shipment.
     *
     * @param shipmentReqDto the shipment request DTO
     * @return the tracking number of the saved shipment
     */
    @Override
    public String saveShipment(ShipmentReqDto shipmentReqDto) {

        var name = SecurityContextHolder.getContext().getAuthentication().getName();
        var customer = userRepository.findByName(name).orElseThrow();
        shipmentReqDto.setCustomerId(customer.getId());

        var shipment = shipmentMapper.shipmentReqDtoToShipment(shipmentReqDto, locationRepository, customerRepository);
        var randomTrackingNumbers = RandomStringUtils.randomAlphanumeric(36);
        var packagestatus = packagestatusRepository.findById(1).orElseThrow(); //"not assigned" status

        shipment.setPackagestatus(packagestatus);
        shipment.setTrackingNumber(randomTrackingNumbers);

        return shipmentRepository.save(shipment).getTrackingNumber();
    }

    /*
     * This method updates the shipment in the database.
     * It first retrieves the shipment from the database using its ID.
     * Then it maps the ShipmentUpdateReqDto to the retrieved Shipment entity and saves it to the database.
     * Finally, it returns the tracking number of the updated shipment.
     * This is used for employee to update the shipment.
     * */
    @Override
    public String updateShipment(ShipmentUpdateReqDto shipmentUpdateReqDto) {
        var shipment = shipmentRepository.findById(shipmentUpdateReqDto.getId()).orElseThrow();

        shipment.setDeliveredAt(shipmentUpdateReqDto.getDeliveredAt());
        shipment.setEstimatedDelivery(shipmentUpdateReqDto.getEstimatedDelivery());

        return shipmentRepository.save(shipment).getTrackingNumber();
    }

    /**
     * This method retrieves a shipment from the database using a tracking number.
     * It maps the retrieved Shipment entity to a ShipmentRespDto and returns it.
     *
     * @param trackingNumber the tracking number of the shipment
     * @return the shipment response DTO
     */
    @Override
    public ShipmentRespDto getShipment(String trackingNumber) {
        var shipment = shipmentRepository.findByTrackingNumber(trackingNumber).orElseThrow();

        return shipmentMapper.shipmentToShipmentRespDto(shipment);
    }

    /**
     * This method retrieves all shipments with a specific package status from the database.
     * It maps the retrieved Shipment entities to ShipmentRespDtos and returns them as a list.
     *
     * @param packageStatusId the ID of the package status
     * @return a list of shipment response DTOs
     */
    @Override
    public List<ShipmentRespDto> getShipmentByPackageStatusId(Integer packageStatusId) {

        var notAssignedShipments = shipmentRepository.findByPackagestatus_StatusId(packageStatusId);

        return shipmentMapper.shipmentToShipmentRespDto(notAssignedShipments);
    }

    @Override
    public List<ShipmentRespDto> getNotCompletedShipmentsByPackageStatusId(Integer packageStatusId) {

        var notAssignedShipments = shipmentRepository.findByPackagestatusStatusIdNot(packageStatusId);

        return shipmentMapper.shipmentToShipmentRespDto(notAssignedShipments);
    }

    /**
     * This method retrieves a shipment from the database using its ID.
     * It maps the retrieved Shipment entity to a ShipmentRespDto and returns it.
     *
     * @param integer the ID of the shipment
     * @return the shipment response DTO
     */
    @Override
    public ShipmentRespDto getShipmentById(Integer integer) {
        var shipment = this.shipmentRepository.findById(integer).orElseThrow();

        return shipmentMapper.shipmentToShipmentRespDto(shipment);
    }

    @Override
    public String savePackagesToShipment(String trackingId, PackageReqDto[] packageReqDtos) {

        var shipment = shipmentRepository.findByTrackingNumber(trackingId).orElseThrow();
        var packages = shipmentMapper.packageReqDtoToPackage(Arrays.stream(packageReqDtos).filter(p -> !StringUtils.isEmpty(p.getContent()) && p.getValue() != null && p.getWeight() != null).toList());

        shipment.setPackagesByShipmentId(packages);
        packages.forEach(p -> p.setShipmentByShipmentId(shipment));

        shipmentRepository.save(shipment);
        return shipment.getTrackingNumber();
    }

    @Override
    public void saveShipmentEstimatedDeliveryDate(int shipmentId, Date estimatedDeliveryDate, int fromWarehouseId, int toWarehouseId, Date deliveredDate) {
        var shipment = shipmentRepository.findById(shipmentId).orElseThrow();

        if (deliveredDate != null) {
            shipment.setDeliveredAt(deliveredDate);
            var deliveredStatus = packagestatusRepository.findById(3).orElseThrow();
            shipment.setPackagestatus(deliveredStatus);
            shipmentRepository.save(shipment);
            return;
        }

        var fromWarehouse = warehouseRepository.findById(fromWarehouseId).orElseThrow();
        var toWarehouse = warehouseRepository.findById(toWarehouseId).orElseThrow();
        shipment.getPackagesByShipmentId().forEach(p -> {
            p.setWarehouseByFromWarehouseId(fromWarehouse);
            p.setWarehouseByToWarehouseId(toWarehouse);
        });
        var assignedStatus = packagestatusRepository.findById(2).orElseThrow();
        shipment.setPackagestatus(assignedStatus);
        packageRepository.saveAllAndFlush(shipment.getPackagesByShipmentId());
        shipment.setEstimatedDelivery(estimatedDeliveryDate);
        shipmentRepository.save(shipment);
    }
}