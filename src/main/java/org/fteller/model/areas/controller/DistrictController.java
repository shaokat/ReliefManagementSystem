package org.fteller.model.areas.controller;


import org.fteller.Exception.NotFoundException;
import org.fteller.model.areas.District;
import org.fteller.model.areas.Division;
import org.fteller.model.areas.services.DistrictService;
import org.fteller.model.areas.services.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "areas")
public class DistrictController
{
    @Autowired
    DistrictService districtService;
    @Autowired
    DivisionService divisionService;

    @GetMapping(path = "/division/{id}/districts")
    public List<District> getAllDistrictsByDivision(@PathVariable Division id)  {
        List<District> districts = districtService.getDsitrictsByDivisionId(id);
        if(districts!=null && !districts.isEmpty()){
            return districts;

        }
        else {
            throw new NotFoundException("No District Available for Division id: "+id);
        }
    }
    @GetMapping(path = "/district/{id}")
    public District getDistrict(@PathVariable int id){
        District district =  districtService.getDistrictById(id);
        if(district == null){
            throw new NotFoundException("District record with the id: "+id+" not found");
        }
        else {
            Division division = district.getDivision();
            district.setDivision(division);
            return district;
        }
    }

    @PostMapping(path = "/division/{id}/district")
    public void createDistrict(@Valid @PathVariable int id, @RequestBody District district) throws NotFoundException {

        Division division = divisionService.findDivisionById(id);
        if(division!=null){
            districtService.createDistrict(district.getName(),division);

        }
        else {
            throw new NotFoundException(" Division for  id: "+id+" Not Found");
        }

    }
    @PatchMapping(path = "/division/{id}/district/update")
    public void upadateDivision(@RequestBody District district,@PathVariable int id ){
        Division division = divisionService.getDivision(id);
        if(division != null) {
            district.setDivision(division);
            districtService.upadateDistrict(district);
        }
        else {
            throw new NotFoundException(" Division for  id: "+id+" Not Found");

        }
    }

    @DeleteMapping(path = "/district/delete/{id}")
    public void deleteDistrictRecord(@PathVariable int id){
        District deleted = districtService.deleteDistrict(id);
        if (deleted == null)
            throw new NotFoundException("District record with the id: "+id+" not found");
    }
}
