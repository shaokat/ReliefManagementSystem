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
@DiscriminatorValue("M")
@ToString
public class MoneyRelief extends ReliefType {
    private @Getter@Setter int amountInTaka;
    private @Getter@Setter int noOfPeopleHelped;

    public int averageMoneyDistributed(){
        return this.amountInTaka/this.noOfPeopleHelped;
    }

}
