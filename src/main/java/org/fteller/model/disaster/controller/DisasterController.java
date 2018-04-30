package org.fteller.model.disaster.controller;

import org.fteller.Exception.NotFoundException;
import org.fteller.model.disaster.Disaster;
import org.fteller.model.disaster.DisasterType;
import org.fteller.model.disaster.service.DisasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Abdullah Al Amin on 4/11/2018.
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/disaster")
public class DisasterController {

    @Autowired
    private DisasterService service;

    @PostMapping(path = "/save/{time}")
    public ResponseEntity<Object> createDisasterRecord(
            @Valid @RequestBody Disaster disasterRecord,
            @PathVariable String time){

        if(disasterRecord != null){
            disasterRecord.setDateOfOccurance(makeDate(time));
            Disaster record = service.saveDisasterRecord(disasterRecord);
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(disasterRecord.getId()).toUri();
        return  ResponseEntity.created(location).build();
    }

    private Date makeDate(@PathVariable String time)  {
        String format = "yyyy-MM-dd";
        DateFormat parser = new SimpleDateFormat(format);
        try {
            Date date = parser.parse(time);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(path = "/all")
    public List<Disaster> getAllDisasterRecords(){
        return service.getDisasterRecords();
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteDisasterRecord(@PathVariable int id){
        Disaster deleted = service.deleteDisasterRecord(id);
        if (deleted == null)
            throw new NotFoundException("disaster record with the id: "+id+" not found");
    }

    @GetMapping(path = "/one/{id}")
    public Disaster getDisasterRecordById(@PathVariable int id){
        Disaster record = service.getDisasterById(id);
        if (record == null)
            throw new NotFoundException("disaster record with the id: "+id+" not found");
        else
            return record;
    }

    @PatchMapping(path = "/update")
    public void updateDisasterRecord(@RequestBody Disaster disasterRecord){
        service.updateDisasterRecord(disasterRecord);
    }

    @GetMapping("/types")
    public List<DisasterType> getDisasterTypes(){
        return Arrays.asList(DisasterType.values());
    }

}
