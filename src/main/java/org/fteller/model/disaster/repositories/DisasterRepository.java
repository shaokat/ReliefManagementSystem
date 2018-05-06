package org.fteller.model.disaster.repositories;

import org.fteller.model.disaster.Disaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by abdullah on 4/11/18.
 */
public interface DisasterRepository extends JpaRepository<Disaster,Integer> {

}
