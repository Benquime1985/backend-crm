package com.optica.backend_crm.repositories;

import com.optica.backend_crm.models.SaleNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface SaleNoteRepository extends JpaRepository<SaleNote, Integer> {

    List<SaleNote> findByPatient_IdPatient(Integer id);

    @Query("SELECT s.status, COUNT(s) FROM SaleNote s GROUP BY s.status")
    List<Object[]> countByStatusRaw();

    default Map<String, Long> getCountByStatusMap() {
        return countByStatusRaw().stream()
                .collect(Collectors.toMap(
                        obj -> obj[0].toString(),
                        obj -> (Long) obj[1]
                ));
    }

}
