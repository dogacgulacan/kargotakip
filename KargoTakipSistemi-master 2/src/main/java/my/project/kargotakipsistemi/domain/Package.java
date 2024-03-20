package my.project.kargotakipsistemi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Package {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "package_id", nullable = false)
    private int packageId;
    @Basic
    @Column(name = "content", nullable = true, length = -1)
    private String content;
    @Basic
    @Column(name = "weight", nullable = true, precision = 0)
    private BigInteger weight;
    @Basic
    @Column(name = "value", nullable = true, precision = 0)
    private BigInteger value;
    @Basic
    @Column(name = "shipment_id", nullable = true, insertable = false, updatable = false)
    private Integer shipmentId;
    @Basic
    @Column(name = "from_warehouse_id", nullable = true, insertable = false, updatable = false)
    private Integer fromWarehouseId;
    @Basic
    @Column(name = "to_warehouse_id", nullable = true, insertable = false, updatable = false)
    private Integer toWarehouseId;
    @ManyToOne
    @JoinColumn(name = "shipment_id", referencedColumnName = "shipment_id")
    private Shipment shipmentByShipmentId;
    @ManyToOne
    @JoinColumn(name = "from_warehouse_id", referencedColumnName = "warehouse_id")
    private Warehouse warehouseByFromWarehouseId;
    @ManyToOne
    @JoinColumn(name = "to_warehouse_id", referencedColumnName = "warehouse_id")
    private Warehouse warehouseByToWarehouseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Package aPackage = (Package) o;

        if (packageId != aPackage.packageId) return false;
        if (!Objects.equals(content, aPackage.content)) return false;
        if (!Objects.equals(weight, aPackage.weight)) return false;
        if (!Objects.equals(value, aPackage.value)) return false;
        if (!Objects.equals(shipmentId, aPackage.shipmentId)) return false;
        if (!Objects.equals(fromWarehouseId, aPackage.fromWarehouseId))
            return false;
        return Objects.equals(toWarehouseId, aPackage.toWarehouseId);
    }

    @Override
    public int hashCode() {
        int result = packageId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (shipmentId != null ? shipmentId.hashCode() : 0);
        result = 31 * result + (fromWarehouseId != null ? fromWarehouseId.hashCode() : 0);
        result = 31 * result + (toWarehouseId != null ? toWarehouseId.hashCode() : 0);
        return result;
    }
}
