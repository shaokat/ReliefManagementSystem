package org.fteller.model.relief;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by Abdullah Al Amin on 9/26/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter@Setter int id;
    private @Getter@Setter String name;
    private @Getter@Setter String description;
    private @Getter@Setter String  nameAcronym;
    private @Getter@Setter OrganizationLevel orgLevel;

    @OneToMany(mappedBy = "organization", orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonIgnore
    private @Getter
    List<ReliefRecords> reliefRecords = new ArrayList<>();
    public void setReliefRecords(List<ReliefRecords> reliefRecord){
        if(reliefRecords!=null) {
            this.reliefRecords.clear();
            this.reliefRecords.addAll(reliefRecord);
        }
    }

    public void addReliefRecords(@NonNull ReliefRecords... records){
        reliefRecords.addAll(Arrays.asList(records));
    }
}
