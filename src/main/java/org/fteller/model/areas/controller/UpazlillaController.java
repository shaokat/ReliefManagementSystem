package org.fteller.model.areas.controller;
import org.fteller.Exception.NotFoundException;
import org.fteller.model.areas.District;
import org.fteller.model.areas.Division;
import org.fteller.model.areas.Upazilla;
import org.fteller.model.areas.services.DistrictService;
import org.fteller.model.areas.services.UpazillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "areas")
public class UpazlillaController {

    @Autowired
    UpazillaService upazillaService;
    @Autowired
    DistrictService districtService;

    @PostMapping(path = "/district/{id}/upazilla")
    public void addUpazilla(@Valid @PathVariable int id, @RequestBody Upazilla upazilla) {

        District district = districtService.getDistrictById(id);
        if(district!=null){
            upazillaService.createUpazilla(upazilla.getName(),district);
        }

        else {
                throw new NotFoundException("No Upazilla Available for District id: " + id);

        }

    }

    @GetMapping(value = "/district/{id}/upazillas")
    @ResponseBody
    public List<Upazilla> getAllUP(@PathVariable District id) {
        List<Upazilla> upazillas =  upazillaService.getUpazillasByDistrictId(id);

        if (upazillas!=null && !upazillas.isEmpty()){
            return upazillas;
        }
        else {
            throw new org.fteller.Exception.NotFoundException("No Upazilla Available for District id: " + id);
        }

    }
    @GetMapping(path = "/upazillas")
    public List<Upazilla> getUpzillas(){
        return upazillaService.getUpazillas();
    }
    @GetMapping(path = "/upazilla/{id}")
    public Upazilla getUpazilla(@PathVariable int id){
        Upazilla upazilla = upazillaService.getUpazillaById(id);
        if(upazilla == null){
            throw new NotFoundException("Upazilla record with the id: "+id+" not found");
        }
        else {
            return upazilla;
        }
    }
    @PatchMapping(path = "/district/{id}/upazilla/update")
    public void upadateDivision(@RequestBody Upazilla upazilla,@PathVariable int id ){
        District district = districtService.getDistrictById(id);
        if(district != null) {
            upazilla.setDistrict(district);
            upazillaService.upadateUpazilla(upazilla);
        }
        else {
            throw new NotFoundException(" District for  id: "+id+" Not Found");

        }
    }

    @DeleteMapping(path = "/upazilla/delete/{id}")
    public void deleteUpazillaRecord(@PathVariable int id){
        Upazilla deleted = upazillaService.deleteUpazilla(id);
        if (deleted == null)
            throw new NotFoundException("Upazilla record with the id: "+id+" not found");
    }

}
