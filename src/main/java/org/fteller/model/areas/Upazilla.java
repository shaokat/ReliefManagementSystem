package org.fteller.model.areas;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
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

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "upazilla",cascade = CascadeType.ALL)
    @JsonIgnore
    private @Getter
    List<UnionParisad> unionParisads  = new ArrayList<>();
    public void setUnionParisads(List<UnionParisad> unionParisad){
        if(unionParisads != null) {
            this.unionParisads.clear();
            this.unionParisads.addAll(unionParisad);
        }
    }
    @ManyToOne(targetEntity = District.class)
    @JoinColumn(name = "district_id")
    @JsonIgnore
    private @Getter@Setter District district;

    public void addUnions(@NonNull UnionParisad... unionParisad){
        unionParisads.addAll(Arrays.asList(unionParisad));
    }

}
