package org.fteller.model.areas.services;


import org.fteller.model.areas.Division;
import org.fteller.model.areas.repositories.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class DivisionService {
    @Autowired
    private DivisionRepository divisionRepository;
    @Transactional
    public boolean createDivison(String name){
        List<Division> existing = getDivisions();
        boolean exists = existing.stream().anyMatch(unionp->unionp.getName().toLowerCase().equals(name.toLowerCase()));
        if(!exists) {
            Division division = new Division();
            division.setName(name);
            //division.setDistricts(new HashSet<>());
            saveDivision(division);
            return true;
        }else
            return false;
    }

    public void saveDivision(Division division) {
        divisionRepository.save(division);
    }
    public List<Division> getDivisions() {
        return divisionRepository.findAll();
    }
    public Division findDivisionById(int id)  {
        Optional<Division> divisionOptional = divisionRepository.findById(id);
            return divisionOptional.get();
        }

    public Division deleteDivision(int divisionId){
        Division division = divisionRepository.findOne(divisionId);
        divisionRepository.delete(division);
        return division;
    }

    public void updateDivisions(Division divisionRecord){
        divisionRepository.save(divisionRecord);
    }

}

