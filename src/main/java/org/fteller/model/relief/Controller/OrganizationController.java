package org.fteller.model.relief.Controller;

import org.fteller.model.relief.Organization;
import org.fteller.model.relief.OrganizationLevel;
import org.fteller.model.relief.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "organization")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;
    @GetMapping(value = "/all")
    @ResponseBody
    public List<Organization> getAllOrganizations (){
        return organizationService.getOrganization();
    }


    @GetMapping("/one/{id}")
    public Organization getOneOrganization(@PathVariable int id){
        return organizationService.getOneOrganization(id);
    }

    @PostMapping(path = "/add")
    public void addOrganization(@RequestBody Organization organization){
        organizationService.createOrganization(organization);
    }

    @PatchMapping(path = "/update")
    public void updateOrganization(@RequestBody Organization organization){
        organizationService.updateOrganization(organization);
    }

    @GetMapping("/types")
    public List<OrganizationLevel> getOrganizationTypes(){
        return Arrays.asList(OrganizationLevel.values());
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteOrganization(@PathVariable int id){
        organizationService.deleteOrganization(id);
    }
}
