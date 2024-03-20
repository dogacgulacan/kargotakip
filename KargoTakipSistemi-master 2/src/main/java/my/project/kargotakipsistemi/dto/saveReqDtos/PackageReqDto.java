package my.project.kargotakipsistemi.dto.saveReqDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * DTO for {@link my.project.kargotakipsistemi.domain.Package}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageReqDto implements Serializable {
    private String content;
    private BigInteger weight;
    private BigInteger value;
    private Integer fromWarehouseId;
    private Integer toWarehouseId;

}