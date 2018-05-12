package org.fteller.model.relief.Controller;

import org.fteller.model.relief.ReliefRecords;
import org.fteller.model.relief.service.ReliefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/reliefs")
public class ReliefController {
    @Autowired
    ReliefService reliefService;

    @PostMapping(path="/save")
    public void saveReliefRecords( @RequestBody ReliefRecords reliefRecords){
        reliefService.saveReliefRecord(reliefRecords);
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public List<ReliefRecords> getReliefRecords() {
        List<ReliefRecords> reliefRecords =  reliefService.getReliefRecords();

        if (reliefRecords!=null && !reliefRecords.isEmpty()){
            return reliefRecords;
        }
        else {
            throw new org.fteller.Exception.NotFoundException("No reliefRecords Available");
        }

    }

    @PatchMapping(path="/update")
    public void updateReliefRecords( @RequestBody ReliefRecords reliefRecords){
        reliefService.saveReliefRecord(reliefRecords);
    }



}
