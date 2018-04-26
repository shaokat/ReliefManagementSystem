package org.fteller.model.relief;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fteller.model.areas.UnionParisad;
import org.fteller.model.disaster.Disaster;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Abdullah Al Amin on 9/26/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReliefRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter@Setter int id;
    private  @Setter LocalDateTime timestamp ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "union_parisad_id")
    @JsonIgnore
    private @Getter@Setter UnionParisad place;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    @JoinColumn(name = "organization_id")
    private @Getter@Setter Organization organization;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "relief_type_id")
    private @Getter@Setter ReliefType type;

    @ManyToOne
    @JoinColumn(name = "disaster_id")
    @JsonIgnore
    @Getter@Setter private Disaster disaster;


    public LocalDate getDate(){
       return timestamp.toLocalDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReliefRecords that = (ReliefRecords) o;

        if (getId() != that.getId()) return false;
        return timestamp != null ? timestamp.equals(that.timestamp) : that.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }
}
