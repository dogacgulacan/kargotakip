package my.project.kargotakipsistemi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Location {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "location_id", nullable = false)
    private int locationId;
    @Basic
    @Column(name = "city", nullable = true, length = 50)
    private String city;
    @Basic
    @Column(name = "country", nullable = true, length = 50)
    private String country;
    @OneToMany(mappedBy = "locationByFromLocationId")
    private Collection<Shipment> shipmentsByLocationId;
    @OneToMany(mappedBy = "locationByToLocationId")
    private Collection<Shipment> shipmentsByLocationId_0;
    @OneToMany(mappedBy = "locationByLocationId")
    private Collection<Warehouse> warehousesByLocationId = new HashSet<>();

    public Location(String city, String country) {
        this.city = city;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (locationId != location.locationId) return false;
        if (!Objects.equals(city, location.city)) return false;
        return Objects.equals(country, location.country);
    }

    @Override
    public int hashCode() {
        int result = locationId;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return city + " from " + country;
    }
}
