package org.fteller.model.areas.services;
import org.fteller.model.areas.District;
import org.fteller.model.areas.UnionParisad;
import org.fteller.model.areas.Upazilla;
import org.fteller.model.areas.repositories.UnionRepository;
import org.fteller.model.areas.repositories.UpazillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UnionParisadService {
    @Autowired
    UnionRepository unionRepository;

    @Autowired
    UpazillaRepository upazillaRepository;
    @Transactional
    public boolean createUnion(String name, Upazilla upazilla){
        List<UnionParisad> existing = getUnionParisads();
        boolean exists = existing.stream().anyMatch(unionp->unionp.getName().toLowerCase().equals(name.toLowerCase()));
        if(!exists) {
            UnionParisad union = new UnionParisad();
            union.setName(name);
            union.setUpazilla(upazilla);
            saveUnion(union);
            return true;
        }else
            return false;
    }

    public void saveUnion(UnionParisad union) {
        unionRepository.save(union);
    }

    public List<UnionParisad> getUnionParisads() {
        return unionRepository.findAll();
    }

    public List<UnionParisad> getUnionparisadsByUpazilla(Upazilla id)  {

        List<UnionParisad>  unionParisads =  unionRepository.findByUpazilla(id);
       return unionParisads;
    }
    public Upazilla getUpazilla(int id)  {
        Upazilla upazilla = upazillaRepository.findOne(id);
       return upazilla;
    }
    public void updateUnion(UnionParisad unionParisad){
        unionRepository.save(unionParisad);
    }
}
