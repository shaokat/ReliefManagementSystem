package org.fteller.model.relief;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Abdullah Al Amin on 9/26/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("I")
@ToString
public class ItemRelief extends ReliefType {
    private @Getter@Setter int amountInUnit;
}
