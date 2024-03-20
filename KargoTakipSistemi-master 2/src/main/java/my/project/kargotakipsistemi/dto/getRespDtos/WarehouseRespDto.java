package my.project.kargotakipsistemi.dto.getRespDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link my.project.kargotakipsistemi.domain.Warehouse}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseRespDto implements Serializable {
    int warehouseId;
    String name;
    Integer locationId;
    String locationByLocationIdCity;
    String locationByLocationIdCountry;

    @Override
    public String toString() {
        return "Warehouse from " + name + ", " + locationByLocationIdCity + ", " + locationByLocationIdCountry;
    }
}