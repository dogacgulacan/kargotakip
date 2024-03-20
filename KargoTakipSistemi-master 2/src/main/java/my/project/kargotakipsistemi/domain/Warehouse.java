package my.project.kargotakipsistemi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Warehouse {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "warehouse_id", nullable = false)
    private int warehouseId;

    @Basic
    @Column(name = "location_id", nullable = true, insertable = false, updatable = false)
    private Integer locationId;

    @Column(name = "name", nullable = true, length = 50)
    private String name;

    @OneToMany(mappedBy = "warehouseByWarehouseId")
    private Collection<Employee> employeesByWarehouseId;

    @OneToMany(mappedBy = "warehouseByFromWarehouseId")
    private Collection<Package> packagesByWarehouseId;

    @OneToMany(mappedBy = "warehouseByToWarehouseId")
    private Collection<Package> packagesByWarehouseId_0;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location locationByLocationId;

    public Warehouse(Location locationByLocationId, String name) {
        this.locationByLocationId = locationByLocationId;
        this.name = name;
    }

    public Warehouse(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Warehouse warehouse = (Warehouse) o;

        if (warehouseId != warehouse.warehouseId) return false;
        return Objects.equals(locationId, warehouse.locationId);
    }

    @Override
    public int hashCode() {
        int result = warehouseId;
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        return result;
    }
}
