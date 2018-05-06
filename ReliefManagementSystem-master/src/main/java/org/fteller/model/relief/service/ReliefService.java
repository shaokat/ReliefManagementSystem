package org.fteller.model.relief.service;

import org.fteller.model.relief.ReliefRecords;
import org.fteller.model.relief.ReliefType;
import org.fteller.model.relief.repositories.ReliefRecordRepository;
import org.fteller.model.relief.repositories.ReliefTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReliefService {
    @Autowired
    public ReliefRecordRepository reliefRecordRepository;

    @Autowired
    public ReliefTypeRepository reliefTypeRepository;

    public boolean saveReliefRecord(ReliefRecords reliefRecords){
        if(reliefRecords != null){
            reliefRecordRepository.save(reliefRecords);
            return true;
        }
        else
            return false;

    }
    public List<ReliefRecords> getReliefRecords(){
        return reliefRecordRepository.findAll();
    }


    public boolean saveReliefType(ReliefType reliefType){
        if(reliefType !=null){
            reliefTypeRepository.save(reliefType);
        }
        return false;
    }
    public List<ReliefType> getReliefTypes(){
        return reliefTypeRepository.findAll();
    }
}
