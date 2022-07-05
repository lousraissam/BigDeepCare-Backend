package esi_cardio.com.auth_cardio.repository;

import esi_cardio.com.auth_cardio.entity.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDAO, Long> {
    UserDAO findUserDAOByUsername(String username);



    @Query("SELECT m FROM UserDAO m WHERE m.role = 'ROLE_MEDECIN'")
    List<UserDAO> getAllMedecins();

}
