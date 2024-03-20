package my.project.kargotakipsistemi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Shipment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id", nullable = false)
    @Id
    private int shipmentId;

    @Basic
    @Column(name = "tracking_number", nullable = false, length = 50)
    private String trackingNumber;

    @Basic
    @Column(name = "estimated_delivery", nullable = true)
    private Date estimatedDelivery;

    @Basic
    @Column(name = "delivered_at", nullable = true)
    private Date deliveredAt;

    @Basic
    @Column(name = "customer_id", nullable = true, insertable = false, updatable = false)
    private Integer customerId;

    @Basic
    @Column(name = "from_location_id", nullable = true, insertable = false, updatable = false)
    private Integer fromLocationId;

    @Basic
    @Column(name = "to_location_id", nullable = true, insertable = false, updatable = false)
    private Integer toLocationId;

    @Setter
    @OneToMany(mappedBy = "shipmentByShipmentId", cascade = CascadeType.ALL)
    private Collection<Package> packagesByShipmentId;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")

    private Customer customerByCustomerId;
    @ManyToOne
    @JoinColumn(name = "from_location_id", referencedColumnName = "location_id")
    private Location locationByFromLocationId;

    @ManyToOne
    @JoinColumn(name = "to_location_id", referencedColumnName = "location_id")
    private Location locationByToLocationId;

    @Column(name = "packagestatus_status_id", nullable = false, insertable = false, updatable = false)
    private Integer packagestatusStatusId;

    @ManyToOne
    @JoinColumn(name = "packagestatus_status_id")
    private Packagestatus packagestatus;

    public Collection<Package> getPackagesByShipmentId() {
        if (packagesByShipmentId == null) {
            this.setPackagesByShipmentId(new java.util.LinkedList<>());
        }
        return packagesByShipmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shipment shipment = (Shipment) o;

        if (!Objects.equals(shipmentId, shipment.shipmentId)) return false;
        if (!Objects.equals(trackingNumber, shipment.trackingNumber))
            return false;
        if (!Objects.equals(estimatedDelivery, shipment.estimatedDelivery))
            return false;
        if (!Objects.equals(deliveredAt, shipment.deliveredAt))
            return false;
        if (!Objects.equals(customerId, shipment.customerId)) return false;
        if (!Objects.equals(fromLocationId, shipment.fromLocationId))
            return false;
        return Objects.equals(toLocationId, shipment.toLocationId);
    }

    @Override
    public int hashCode() {
        int result = shipmentId;
        result = 31 * result + (trackingNumber != null ? trackingNumber.hashCode() : 0);
        result = 31 * result + (estimatedDelivery != null ? estimatedDelivery.hashCode() : 0);
        result = 31 * result + (deliveredAt != null ? deliveredAt.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (fromLocationId != null ? fromLocationId.hashCode() : 0);
        result = 31 * result + (toLocationId != null ? toLocationId.hashCode() : 0);
        return result;
    }
}
