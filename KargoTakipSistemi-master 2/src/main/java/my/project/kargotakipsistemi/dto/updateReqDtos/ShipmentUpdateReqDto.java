package my.project.kargotakipsistemi.dto.updateReqDtos;

import lombok.Getter;
import lombok.Setter;
import my.project.kargotakipsistemi.domain.Shipment;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link Shipment}
 */
@Getter
@Setter
public class ShipmentUpdateReqDto implements Serializable {

    private Integer id;

    private Date estimatedDelivery;

    private Date deliveredAt;
}