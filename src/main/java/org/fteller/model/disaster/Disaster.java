package org.fteller.model.disaster;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.fteller.model.relief.ReliefRecords;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by abdullah on 4/11/18.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "disaster")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Disaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter@Setter private int id;

    @Size(min = 3)
    @Getter@Setter private String name;

    @Getter@Setter private DisasterType type;

    @Past
    @Getter@Setter private Date dateOfOccurance;
    @Getter@Setter private String description;


    @OneToMany(mappedBy = "disaster",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ReliefRecords> recordsSet;



}
