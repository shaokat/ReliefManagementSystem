package org.fteller.model.relief.Controller;

import org.fteller.model.relief.Organization;
import org.fteller.model.relief.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "organization")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;
    @GetMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Organization> getAllOrganization (){
        return organizationService.getOrganization();
    }


    @PostMapping(path = "/add",consumes = "application/json")
    public void addOrgation(@RequestBody Organization organization){
        organizationService.createOrganization(organization);
    }
}
