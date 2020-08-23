package lk.suwodya.system.repository;

import lk.suwodya.system.entity.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
}
