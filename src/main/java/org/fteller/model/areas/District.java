package org.fteller.model.areas;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

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
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "district",cascade = CascadeType.ALL)
    @JsonIgnore
    private @Getter
    List<Upazilla> upazillas = new ArrayList<>();

    public void setUpazillas(List<Upazilla> upazilla){
        if(upazillas!=null) {
            this.upazillas.clear();
            this.upazillas.addAll(upazilla);
        }
    }
    @ManyToOne(targetEntity = Division.class)
    @JoinColumn(name = "division_id")

    private @Getter@Setter Division division;


    public void addUpazillas(@NonNull Upazilla... upazillas){
        this.getUpazillas().addAll(Arrays.asList(upazillas));
    }
}
