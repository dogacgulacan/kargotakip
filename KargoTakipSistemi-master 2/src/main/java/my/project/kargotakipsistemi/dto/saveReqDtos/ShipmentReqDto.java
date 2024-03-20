package my.project.kargotakipsistemi.dto.saveReqDtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import my.project.kargotakipsistemi.domain.Shipment;

import java.io.Serializable;

/**
 * DTO for {@link Shipment}
 */
@Getter
@Setter
public class ShipmentReqDto implements Serializable {
    Integer fromLocationId;
    Integer toLocationId;

    @JsonIgnore
    Integer customerId;
}