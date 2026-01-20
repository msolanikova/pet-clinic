package sk.promark.petclinic.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @Column(name = "uuid", length = 36, nullable = false, updatable = false)
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "visit_date")
    private Date visitDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Pet pet;

    private String description;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Visit visit)) return false;
        return Objects.equals(getUuid(), visit.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUuid());
    }
}
