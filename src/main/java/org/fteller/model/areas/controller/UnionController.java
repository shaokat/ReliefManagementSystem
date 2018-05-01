package org.fteller.model.areas.controller;
import org.fteller.Exception.NotFoundException;
import org.fteller.model.areas.UnionParisad;
import org.fteller.model.areas.Upazilla;
import org.fteller.model.areas.services.UnionParisadService;
import org.fteller.model.areas.services.UpazillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "areas")
public class UnionController {

    @Autowired
    UnionParisadService unionParisadService;
    @Autowired
    UpazillaService upazillaService;

    @GetMapping(value = "/upazilla/{id}/unions")
    @ResponseBody
    public List<UnionParisad> getAllUnion(@PathVariable Upazilla id) {
        List<UnionParisad> unionParisads = unionParisadService.getUnionparisadsByUpazilla(id);
        if (unionParisads!=null && !unionParisads.isEmpty() ){
            return unionParisads;
        }

        else {
            throw new NotFoundException("No UnionParisad Available for Upazilla id: "+id);
        }
    }
    @PostMapping(path = "/upazilla/{id}/union")
    public void addUpazilla(@Valid @PathVariable int id, @RequestBody UnionParisad unionParisad) {

        Upazilla upazilla = unionParisadService.getUpazilla(id);
        if(upazilla!=null) {
            unionParisadService.createUnion(unionParisad.getName(), upazilla);
        }
         else {
            throw new NotFoundException("Upzilla for id: "+id+" not Found");
        }
    }
    @GetMapping(path = "/union/{id}")
    public UnionParisad getUnion(@PathVariable int id){
        UnionParisad unionParisad = unionParisadService.getUnionParisadByID(id);
        if(unionParisad == null){
            throw new NotFoundException("Union record with the id: "+id+" not found");
        }
        else {
            return unionParisad;
        }
    }
    @PatchMapping(path = "/upazilla/{id}/union/update")
    public void upadateDivision(@RequestBody UnionParisad unionParisad,@PathVariable int id ){
        Upazilla upazilla = upazillaService.getUpazillaById(id);
        if(upazilla != null) {
            unionParisad.setUpazilla(upazilla);
            unionParisadService.updateUnion(unionParisad);
        }
        else {
            throw new NotFoundException(" Upazilla for  id: "+id+" Not Found");

        }
    }
    @DeleteMapping(path = "/union/delete/{id}")
    public void deleteUnionRecord(@PathVariable int id){
        UnionParisad deleted = unionParisadService.deleteUnion(id);
        if (deleted == null)
            throw new NotFoundException("Union record with the id: "+id+" not found");
    }
}
