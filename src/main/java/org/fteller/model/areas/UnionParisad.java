package org.fteller.model.areas;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.fteller.model.relief.ReliefRecords;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Abdullah Al Amin on 9/18/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "union_parisad")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UnionParisad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  @Getter@Setter int id;
    @Size(min = 3, message = "UnionParisad Name should have atleast 3 character")
    private  @Getter@Setter String name;

    @ManyToOne(targetEntity = Upazilla.class)
    @JoinColumn(name = "upazilla_id")
    private @Getter@Setter Upazilla upazilla;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "place",cascade = CascadeType.ALL)
    @JsonIgnore
    private @Getter
    List<ReliefRecords> reliefRecords = new ArrayList<>();
    public void setReliefRecords(List<ReliefRecords> reliefRecord){
        if(reliefRecords!=null) {
            this.reliefRecords.clear();
            this.reliefRecords.addAll(reliefRecord);
        }
    }

}
