package com.example.test.repository;

import com.example.test.model.domain.Lord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LordRepository extends CrudRepository<Lord, Long> {
    @Query(nativeQuery = true, value = """
                SELECT LORDS.ID, LORDS.AGE, LORDS.NAME
                FROM LORDS LEFT OUTER JOIN PLANETS ON LORDS.ID = PLANETS.LORD_ID
                WHERE LORD_ID IS NULL
        """)
    List<Lord> getLoungers();

    @Query(nativeQuery = true, value = """
                SELECT TOP 10 *
                FROM LORDS
                ORDER BY AGE
        """)
    List<Lord> getTop10YoungestLord();
}
