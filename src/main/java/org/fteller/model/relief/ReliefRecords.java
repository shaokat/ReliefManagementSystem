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
    private  @Setter@Getter LocalDate timestamp ;

    @ManyToOne(targetEntity = UnionParisad.class)
    @JoinColumn(name = "union_parisad_id")

    private @Getter@Setter UnionParisad place;

    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "organization_id")
    private @Getter@Setter Organization organization;

    @OneToOne(cascade = CascadeType.ALL)

    @JoinColumn(name = "relief_type_id")
    private @Getter@Setter ReliefType type;

    @ManyToOne
    @JoinColumn(name = "disaster_id")

    @Getter@Setter private Disaster disaster;


}
