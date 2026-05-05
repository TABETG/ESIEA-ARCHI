package edu.esiea.campus_library_flow.infrastructure.repositories;

import edu.esiea.campus_library_flow.infrastructure.persistence.entities.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanJpaEntityRepository extends JpaRepository<LoanEntity, Long> {

    @Query("SELECT l FROM LoanEntity l WHERE l.studentId = :studentId")
    List<LoanEntity> getByStudentId(@Param("studentId") Long studentI);
}
