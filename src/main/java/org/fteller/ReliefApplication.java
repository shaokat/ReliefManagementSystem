package org.fteller;

import org.fteller.model.areas.District;
import org.fteller.model.areas.Division;
import org.fteller.model.areas.UnionParisad;
import org.fteller.model.areas.repositories.UnionRepository;
import org.fteller.model.areas.Upazilla;
import org.fteller.model.areas.repositories.UpazillaRepository;
import org.fteller.model.relief.*;
import org.fteller.model.relief.repositories.OrganizationRepository;
import org.fteller.model.relief.repositories.ReliefTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class ReliefApplication implements CommandLineRunner {


    @Autowired
    private UnionRepository unionRepository;
    @Autowired
    private UpazillaRepository upazillaRepository;
    @Autowired
    private ReliefTypeRepository reliefTypeRepository;
    @Autowired
    private OrganizationRepository organizationRepository;


	public static void main(String[] args) {

        SpringApplication.run(ReliefApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
        initalizeDBwithArea();
//        initializeDBWithrelieftype();
//        checkRelief();
        Organization redCrescent = new Organization();
        redCrescent.setName("International Federation of Red Crescent");
        redCrescent.setNameAcronym("IFRC");
        redCrescent.setOrgLevel(OrganizationLevel.INTERNATIONAL);
        redCrescent.setReliefRecords(new HashSet<ReliefRecords>());

        ReliefType money = new MoneyRelief(30000,60);
        ReliefRecords record = new ReliefRecords();
        record.setTimestamp(LocalDateTime.now());
        record.setOrganization(redCrescent);
        record.setType(money);
        record.setPlace(unionRepository.findOne(1));

        redCrescent.addReliefRecords(record);
        organizationRepository.save(redCrescent);


    }

    private void checkRelief() {
        ItemRelief rel = (ItemRelief) reliefTypeRepository.findOne(1);
        System.out.println(rel);
    }

    private void initializeDBWithrelieftype(){
        ReliefType moneyRel = new MoneyRelief(30000,60);
        moneyRel.setName("money");
        moneyRel.setDescription("distributed among the cidr victims");
        ReliefType itemRel = new ItemRelief(2000);
        itemRel.setName("biscuits and fresh water");
        itemRel.setDescription("distributed among flood victims");
        reliefTypeRepository.save(itemRel);
        reliefTypeRepository.save(moneyRel);
    }

    private void initalizeDBwithArea() {
	    //create union parisad
        UnionParisad chakar = new UnionParisad();
        UnionParisad laksmibazar = new UnionParisad();

        //create upazilla
        Upazilla banaripara = new Upazilla();
        banaripara.setName("banaripara");
        banaripara.setUnionParisads(new HashSet<>());

        //create district
        District barisal =  new District();
        barisal.setName("barisal sadar");
        barisal.setUpazillas(new HashSet<>());

        //create division
        Division barisalD = new Division();
        barisalD.setName("barisal");
        barisalD.setDistricts(new HashSet<>());

        barisal.setDivision(barisalD);
        barisal.addUpazillas(banaripara);

        chakar.setName("chakhar");
        chakar.setUpazilla(banaripara);
        laksmibazar.setName("laksmibazar");
        laksmibazar.setUpazilla(banaripara);

        banaripara.setDistrict(barisal);


        banaripara.addUnions(chakar,laksmibazar);

        unionRepository.save(chakar);
        unionRepository.save(laksmibazar);
      //  upazillaRepository.save(banaripara);
    }


}
