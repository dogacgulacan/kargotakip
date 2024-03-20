package my.project.kargotakipsistemi.dto.getRespDtos;

import lombok.Getter;
import lombok.Setter;
import my.project.kargotakipsistemi.domain.Package;
import my.project.kargotakipsistemi.domain.Shipment;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Collection;

/**
 * DTO for {@link Shipment}
 */
@Getter
@Setter
public class ShipmentRespDto implements Serializable {
    int shipmentId;
    String trackingNumber;
    String status;
    Date estimatedDelivery;
    Date deliveredAt;
    Collection<PackageDto> packagesByShipmentId;
    String locationByFromLocationIdCity;
    String locationByFromLocationIdCountry;
    String locationByToLocationIdCity;
    String locationByToLocationIdCountry;

    /**
     * DTO for {@link Package}
     */
    @Getter
    @Setter
    public static class PackageDto implements Serializable {
        int packageId;
        String content;
        BigInteger weight;
        BigInteger value;
        String warehouseByFromWarehouseIdLocationByLocationIdCity;
        String warehouseByFromWarehouseIdLocationByLocationIdCountry;
        String warehouseByToWarehouseIdLocationByLocationIdCity;
        String warehouseByToWarehouseIdLocationByLocationIdCountry;
    }
}