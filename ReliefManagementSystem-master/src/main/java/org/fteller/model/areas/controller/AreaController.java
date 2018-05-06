package org.fteller.model.areas.controller;

import javassist.NotFoundException;
import org.fteller.model.areas.District;
import org.fteller.model.areas.Division;
import org.fteller.model.areas.UnionParisad;
import org.fteller.model.areas.Upazilla;
import org.fteller.model.areas.repositories.DistrictRepository;
import org.fteller.model.areas.repositories.DivisionRepository;
import org.fteller.model.areas.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by Abdullah Al Amin on 4/1/2018.
 */
@RestController
@RequestMapping(path = "area")
public class AreaController {

    @Autowired
    AreaService areaService;
    @Autowired
    DivisionRepository divisionRepository;
    @Autowired
    DistrictRepository districtRepository;

    @GetMapping(path = "/alld",produces = "application/json")
    public List<Division> allDivisions(){
        return areaService.getDivisions();
    }

    @GetMapping(path = "/allDistrict/{id}",produces = "application/json")
    public List<District> getAllDistrictByDivision(@PathVariable Division id) throws NotFoundException {
        return areaService.getDsitrictsByDivisionId(id);
    }
    @GetMapping(path ="/division/{id}",produces = "application/json")
    @ResponseBody
    public Division getDivision(@PathVariable int id){
        District district =  districtRepository.findOne(id);
        System.out.println(district.getDivision().toString());
        Division division = district.getDivision();
        return division;

    }

    @RequestMapping(value = "/allUpazilla/{id}", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Upazilla> getAllUP(@PathVariable District id) throws NotFoundException {
        return areaService.getUpazillasByDistrictId(id);
    }

    @RequestMapping(value = "/allUnionParisad/{id}", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UnionParisad> getAllUnion(@PathVariable Upazilla id) throws NotFoundException {
        return areaService.getUnionparisadByUpazilla(id);
    }

//    @RequestMapping(value = "/allOrganization", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<Organization> getAllOrganization (){
//        return organizationRepository.findAll();
//    }

    @PostMapping(path="/saved" , consumes = "application/json")
    public void saveDivison(@Valid @RequestBody Division division){
        divisionRepository.save(division);
    }

    @PostMapping(path = "/savedist/{id}",consumes = "application/json")
    public void createDistrict(@PathVariable int id,@RequestBody District district) throws NotFoundException {

        Division division = areaService.findDivisionById(id);
        if(division!=null){
            System.out.println("Df");
            areaService.createDistrict(district.getName(),division);
        }

    }
}
