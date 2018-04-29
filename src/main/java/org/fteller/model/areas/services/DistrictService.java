package org.fteller.model.areas.services;

import javassist.NotFoundException;
import org.fteller.model.areas.District;
import org.fteller.model.areas.Division;
import org.fteller.model.areas.repositories.DistrictRepository;
import org.fteller.model.areas.repositories.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    @Transactional
    public boolean createDistrict(String name, Division division){
        List<District> existing = getDistricts();
        boolean exists = existing.stream().anyMatch(district->district.getName().toLowerCase().equals(name.toLowerCase()));
        if(!exists) {
            District district = new District();
            district.setName(name);
            //district.setUpazillas(new HashSet<>());
            district.setDivision(division);
            saveDistrict(district);
            return true;
        }else
            return false;
    }
    public void saveDistrict(District district) {
        districtRepository.save(district);
    }

    public List<District> getDistricts() {
        return districtRepository.findAll();
    }



    public List<District> getDsitrictsByDivisionId(Division id)  {

        List<District> district =  districtRepository.findByDivision(id);
        return district;

    }

    public District getDistrictById(int id) {
        District district = districtRepository.findOne(id);
        return  district;
    }

    public void upadateDistrict(District district){
        districtRepository.save(district);
    }
    public District deleteDistrict(int divisionId){
        District district = districtRepository.findOne(divisionId);
        districtRepository.delete(district);
        return district;
    }


}
