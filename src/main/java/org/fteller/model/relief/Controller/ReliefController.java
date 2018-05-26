package org.fteller.model.relief.Controller;

import org.fteller.model.areas.UnionParisad;
import org.fteller.model.areas.repositories.UnionRepository;
import org.fteller.model.disaster.Disaster;
import org.fteller.model.disaster.repositories.DisasterRepository;
import org.fteller.model.relief.*;
import org.fteller.model.relief.repositories.OrganizationRepository;
import org.fteller.model.relief.repositories.ReliefTypeRepository;
import org.fteller.model.relief.service.ReliefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/reliefs")
public class ReliefController {
    @Autowired
    ReliefService reliefService;

    @Autowired
    UnionRepository unionRepository;
    @Autowired
    DisasterRepository disasterRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    ReliefTypeRepository reliefTypeRepository;


    @PostMapping(path="/save/{uniId}/{distId}/{orgId}/{date}")
    public void saveReliefRecords(@PathVariable int uniId,
                                  @PathVariable int distId,
                                  @PathVariable int orgId,
                                  @PathVariable String date,
                                  @RequestBody ReliefType type){
        UnionParisad unionParisad = unionRepository.getOne(uniId);
        Disaster disaster = disasterRepository.getOne(distId);
        Organization organization = organizationRepository.getOne(orgId);
        ReliefRecords reliefRecords = new ReliefRecords();
        reliefRecords.setDisaster(disaster);
        reliefRecords.setPlace(unionParisad);
        reliefRecords.setOrganization(organization);
        reliefRecords.setType(type);
        reliefRecords.setTimestamp(makeDate(date));
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


    private LocalDate makeDate(@PathVariable String time)  {
        LocalDate date = LocalDate.parse(time);
        System.out.println(date.toString());
        return date;

    }
}
