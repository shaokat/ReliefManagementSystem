package org.fteller.model.areas;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.fteller.model.relief.ReliefRecords;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upazilla_id")
    @JsonIgnore
    private @Getter@Setter Upazilla upazilla;

    @OneToMany(mappedBy = "place",cascade = CascadeType.MERGE)
    @JsonIgnore
    private @Getter@Setter
    Set<ReliefRecords> reliefRecords;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnionParisad that = (UnionParisad) o;

        if (getId() != that.getId()) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
