package org.fteller.model.relief.repositories;

import org.fteller.model.relief.ReliefRecords;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Abdullah Al Amin on 9/29/2017.
 */
public interface ReliefRecordRepository extends JpaRepository<ReliefRecords,Integer> {
}
