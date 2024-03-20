package my.project.kargotakipsistemi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Employee extends User {
    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;
    @Basic
    @Column(name = "phone_number", nullable = true, length = 20)
    private String phoneNumber;
    @Basic
    @Column(name = "warehouse_id", nullable = true, insertable = false, updatable = false)
    private Integer warehouseId;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "warehouse_id")
    private Warehouse warehouseByWarehouseId;

    public Employee(String name, String password, String email, String phoneNumber) {
        super(name, password);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (!Objects.equals(name, employee.name)) return false;
        if (!Objects.equals(email, employee.email)) return false;
        if (!Objects.equals(phoneNumber, employee.phoneNumber))
            return false;
        return Objects.equals(warehouseId, employee.warehouseId);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (warehouseId != null ? warehouseId.hashCode() : 0);
        return result;
    }
}
