package com.ada.repository;


import com.ada.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    Optional<Student> findByEmailAndPassword(String email, String password);

    Optional<Student>  findByEmail(String email);

    Optional<Student> findByFirstName(String firstName);

    @Transactional
    @Modifying
    @Query(value = "update student set is_token_validated = ?1",nativeQuery = true)
    void updateIsTokenValidated(Boolean isTokenValidated);
}
