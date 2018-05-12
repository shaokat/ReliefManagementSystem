package org.fteller.model.areas;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.fteller.model.areas.District;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Created by Abdullah Al Amin on 9/18/2017.
 */
@ApiModel(description = "All details about Division.")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "division")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter@Setter int id;
    @Size(min = 3, message = "Division Name should have atleast 3 character")
    @ApiModelProperty(notes = "Name should be at least 3 characters")
    private @Getter@Setter String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "division",cascade = CascadeType.ALL)
    @JsonIgnore
    private @Getter
    List<District> districts = new ArrayList<>();

    public void setDistricts(List<District> district){
        if (districts != null) {
            this.districts.clear();
            this.districts.addAll(district);
        }
    }

    public void addDistricts(@NonNull District... districts){
        this.districts.addAll(Arrays.asList(districts));
    }

    @Override
    public String toString() {
        return "Division{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", districts=" + districts +
                '}';
    }
}
