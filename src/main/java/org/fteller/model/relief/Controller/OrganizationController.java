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
    @GetMapping(value = "/all")
    @ResponseBody
    public List<Organization> getAllOrganizations (){
        return organizationService.getOrganization();
    }


    @PostMapping(path = "/add")
    public void addOrganization(@RequestBody Organization organization){
        organizationService.createOrganization(organization);
    }

    @PatchMapping(path = "/update")
    public void updateOrganization(@RequestBody Organization organization){
        organizationService.updateOrganization(organization);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteOrganization(@PathVariable int id){
        organizationService.deleteOrganization(id);
    }
}
