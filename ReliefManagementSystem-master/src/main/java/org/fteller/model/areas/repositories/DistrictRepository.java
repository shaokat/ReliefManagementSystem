package org.fteller.model.areas.repositories;

import org.fteller.model.areas.District;
import org.fteller.model.areas.Division;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Abdullah Al Amin on 9/19/2017.
 */
public interface DistrictRepository extends JpaRepository<District,Integer> {
    District findByName(String name);
    List<District> findByDivision(Division id);
}
