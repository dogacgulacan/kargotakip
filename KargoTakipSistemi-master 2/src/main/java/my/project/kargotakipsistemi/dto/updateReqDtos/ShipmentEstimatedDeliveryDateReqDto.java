package my.project.kargotakipsistemi.dto.updateReqDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ShipmentEstimatedDeliveryDateReqDto {

    @JsonProperty("shipment_id")
    int shipmentId;

    @JsonProperty("estimated_delivery")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date estimatedDelivery;

    @JsonProperty("delivered_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date deliveredDate;

    @JsonProperty("from_warehouse_id")
    int fromWarehouseId;

    @JsonProperty("to_warehouse_id")
    int toWarehouseId;

}
