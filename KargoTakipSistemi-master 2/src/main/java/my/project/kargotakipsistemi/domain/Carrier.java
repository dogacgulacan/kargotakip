package my.project.kargotakipsistemi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Carrier {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "carrier_id", nullable = false)
    private int carrierId;
    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;
    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;
    @Basic
    @Column(name = "phone_number", nullable = true, length = 20)
    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carrier carrier = (Carrier) o;

        if (carrierId != carrier.carrierId) return false;
        if (!Objects.equals(name, carrier.name)) return false;
        if (!Objects.equals(email, carrier.email)) return false;
        return Objects.equals(phoneNumber, carrier.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = carrierId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
}
