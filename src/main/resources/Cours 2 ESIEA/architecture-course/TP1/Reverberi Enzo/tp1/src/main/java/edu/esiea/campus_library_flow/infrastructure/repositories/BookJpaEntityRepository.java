package edu.esiea.campus_library_flow.infrastructure.repositories;

import edu.esiea.campus_library_flow.infrastructure.persistence.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJpaEntityRepository extends JpaRepository<BookEntity, Long> {

}
