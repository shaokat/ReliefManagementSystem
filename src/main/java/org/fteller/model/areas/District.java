package org.fteller.model.areas;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Abdullah Al Amin on 9/18/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "district")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter@Setter int id;
    @Size(min = 3, message = "District Name should have atleast 3 character")
    private @Getter@Setter String name;

    //this is to map the one to many relationship between district and upazillas
    @OneToMany(mappedBy = "district",orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonIgnore
    private @Getter@Setter
    Set<Upazilla> upazillas;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "division_id")
    @JsonIgnore
    private @Getter@Setter Division division;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        District district = (District) o;

        if (getId() != district.getId()) return false;
        return getName() != null ? getName().equals(district.getName()) : district.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    public void addUpazillas(@NonNull Upazilla... upazillas){
        this.getUpazillas().addAll(Arrays.asList(upazillas));
    }
}
