package edu.cornell.cals.biomat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.cornell.cals.biomat.dao.Measurements;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {
}
