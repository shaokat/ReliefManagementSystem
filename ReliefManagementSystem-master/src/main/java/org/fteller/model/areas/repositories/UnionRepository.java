package org.fteller.model.areas.repositories;

import org.fteller.model.areas.UnionParisad;
import org.fteller.model.areas.Upazilla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Abdullah Al Amin on 9/18/2017.
 */
public interface UnionRepository extends JpaRepository<UnionParisad,Integer> {
    UnionParisad findByName(String name);
    List<UnionParisad> findByUpazilla(Upazilla id);
}
