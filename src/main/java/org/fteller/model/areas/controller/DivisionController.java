package org.fteller.model.areas.controller;

import org.fteller.Exception.NotFoundException;
import org.fteller.model.areas.Division;
import org.fteller.model.areas.services.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "areas")
public class DivisionController {
    @Autowired
    DivisionService divisionService;

    @GetMapping(path = "/divisions")
    public List<Division> allDivisions(){
        return divisionService.getDivisions();
    }

    @PostMapping(path="/division")
    public void saveDivison(@Valid @RequestBody Division division){
        divisionService.createDivison(division.getName());
    }
    @DeleteMapping(path = "/division/delete/{id}")
    public void deleteDisasterRecord(@PathVariable int id){
        Division deleted = divisionService.deleteDivision(id);
        if (deleted == null)
            throw new NotFoundException("Division record with the id: "+id+" not found");
    }

//    @GetMapping(path ="/division/{id}",produces = "application/json")
//    @ResponseBody
//    public Division getDivision(@PathVariable int id){
//        District district =  districtRepository.findOne(id);
//        System.out.println(district.getDivision().toString());
//        Division division = district.getDivision();
//        return division;
//
//    }

    @PatchMapping(path = "/division/update")
    public void upadateDivision(@RequestBody Division division ){
        divisionService.updateDivisions(division);
    }
}
