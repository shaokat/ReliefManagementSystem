package org.fteller.model.areas;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by Abdullah Al Amin on 9/18/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "upazilla")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Upazilla {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter@Setter int id;
    @Size(min = 3, message = "Upazilla Name should have atleast 3 character")
    private @Getter@Setter String name;

    @OneToMany(mappedBy = "upazilla",orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonIgnore
    private @Getter@Setter
    Set<UnionParisad> unionParisads;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id")
    @JsonIgnore
    private @Getter@Setter District district;

    public void addUnions(@NonNull UnionParisad... unionParisad){
        unionParisads.addAll(Arrays.asList(unionParisad));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Upazilla upazilla = (Upazilla) o;

        if (getId() != upazilla.getId()) return false;
        return getName() != null ? getName().equals(upazilla.getName()) : upazilla.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
