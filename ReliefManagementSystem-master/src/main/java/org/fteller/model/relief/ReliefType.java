package org.fteller.model.relief;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Abdullah Al Amin on 9/26/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class ReliefType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter@Setter int id;
    private @Getter@Setter String name;
    private @Getter@Setter String description;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "type")
    private @Getter@Setter ReliefRecords itemForRecord;
}
