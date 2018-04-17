package org.fteller.model.relief.service;

import org.fteller.model.relief.Organization;
import org.fteller.model.relief.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    @Autowired
    public OrganizationRepository organizationRepository;

    //creating a new organization
    public Organization createOrganization(Organization organization){
        Organization saved = null;
        if(organizationRepository !=null){
             saved = organizationRepository.save(organization);
        }
        return saved;
    }

    //getting all the organization records
    public List<Organization> getOrganization(){
        return organizationRepository.findAll();
    }

    //delete an organization record
    public Organization deleteOrganization(int id) {
        Organization deleted= organizationRepository.findOne(id);

        if(deleted !=null){
            organizationRepository.delete(deleted);
        }
        return deleted;
    }

    public void updateOrganization(Organization organization){
        organizationRepository.save(organization);

    }

    public Organization getOneOrganization(int id){
      return organizationRepository.findOne(id) ;
    }

}
