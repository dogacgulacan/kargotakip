package my.project.kargotakipsistemi.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {
    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;
    @Basic
    @Column(name = "phone_number", nullable = true, length = 20)
    private String phoneNumber;
    @OneToMany(mappedBy = "customerByCustomerId")
    private Collection<Shipment> shipmentsByCustomerId;

    public Customer(String name, String password, String email, String phoneNumber) {
        super(name, password);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (!Objects.equals(name, customer.name)) return false;
        if (!Objects.equals(email, customer.email)) return false;
        return Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
}
