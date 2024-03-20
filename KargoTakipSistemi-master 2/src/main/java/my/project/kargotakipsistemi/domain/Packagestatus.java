package my.project.kargotakipsistemi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Packagestatus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    @Id
    private int statusId;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    @OneToMany(mappedBy = "packagestatus", orphanRemoval = true)
    private List<Shipment> shipments = new ArrayList<>();

    public Packagestatus(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Packagestatus that = (Packagestatus) o;

        if (statusId != that.statusId) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = statusId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
