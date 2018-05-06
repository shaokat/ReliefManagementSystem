package org.fteller.model.areas.services;

import javassist.NotFoundException;
import org.fteller.model.areas.District;
import org.fteller.model.areas.Division;
import org.fteller.model.areas.UnionParisad;
import org.fteller.model.areas.Upazilla;
import org.fteller.model.areas.repositories.DistrictRepository;
import org.fteller.model.areas.repositories.DivisionRepository;
import org.fteller.model.areas.repositories.UnionRepository;
import org.fteller.model.areas.repositories.UpazillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Created by Abdullah Al Amin on 9/29/2017.
 */
@Component
public class AreaService {

    @Autowired
    private UnionRepository unionRepository;
    @Autowired
    private UpazillaRepository upazillaRepository;
    @Autowired
    private DivisionRepository divisionRepository;
    @Autowired
    private DistrictRepository districtRepository;

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

    @Transactional
    public boolean createUpazilla(String name, District district){
        List<Upazilla> existing = getUpazillas();
        boolean exists = existing.stream().anyMatch(upz->upz.getName().toLowerCase().equals(name.toLowerCase()));
        if(!exists) {
            Upazilla upazilla = new Upazilla();
            upazilla.setName(name);
            //upazilla.setUnionParisads(new HashSet<>());
            upazilla.setDistrict(district);
            saveUpazilla(upazilla);
            return true;
        }else
            return false;
    }

    public void saveUpazilla(Upazilla upazilla) {
        upazillaRepository.save(upazilla);
    }

    public List<Upazilla> getUpazillas() {
        return upazillaRepository.findAll();
    }

    @Transactional
    public boolean createDistrict(String name, Division division){
        List<District> existing = getDistricts();
        boolean exists = existing.stream().anyMatch(district->district.getName().toLowerCase().equals(name.toLowerCase()));
        if(!exists) {
            District district = new District();
            district.setName(name);
           // district.setUpazillas(new HashSet<>());
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

    public Division findDivisionById(int id) throws NotFoundException {
        Optional<Division> divisionOptional = divisionRepository.findById(id);
        if(!divisionOptional.isPresent()){
            throw new NotFoundException("Division for id: "+id+" is not Found");

        }
        else {

            return divisionOptional.get();
        }
    }

    public List<Division> getDivisions() {
        return divisionRepository.findAll();
    }
    public List<District> getDsitrictsByDivisionId(Division id) throws NotFoundException {

        List<District> district =  districtRepository.findByDivision(id);
        if (district != null && !district.isEmpty())
        {
            return district;
        }
        else {
            throw new NotFoundException("No District Available for Division id: "+id);
        }
    }

    public List<Upazilla> getUpazillasByDistrictId(District id) throws NotFoundException {

        List<Upazilla> upazillas =  upazillaRepository.findByDistrict(id);
        if (upazillas != null && !upazillas.isEmpty())
        {
            return upazillas;
        }
        else {
            throw new NotFoundException("No Upazilla Available for District id: "+id);
        }
    }
    public List<UnionParisad> getUnionparisadByUpazilla(Upazilla id) throws NotFoundException {

        List<UnionParisad> upazillas =  unionRepository.findByUpazilla(id);
        if (upazillas != null && !upazillas.isEmpty())
        {
            return upazillas;
        }
        else {
            throw new NotFoundException("No Upazilla Available for District id: "+id);
        }
    }



}
