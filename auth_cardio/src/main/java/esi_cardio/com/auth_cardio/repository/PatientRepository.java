package esi_cardio.com.auth_cardio.repository;


import esi_cardio.com.auth_cardio.entity.Patient;
import esi_cardio.com.auth_cardio.entity.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Collection<Patient> findPatientByParent(UserDAO parent);

}
