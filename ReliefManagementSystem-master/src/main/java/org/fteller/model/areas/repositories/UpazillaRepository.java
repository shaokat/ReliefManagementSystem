package org.fteller.model.areas.repositories;

import org.fteller.model.areas.District;
import org.fteller.model.areas.Upazilla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Abdullah Al Amin on 9/18/2017.
 */
public interface UpazillaRepository extends JpaRepository<Upazilla,Integer>{
    Upazilla findByName(String name);
    List<Upazilla> findByDistrict(District id);
}
