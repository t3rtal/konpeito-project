package com.tertal.konpeito.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tertal.konpeito.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("""
    SELECT a
    FROM Application a
    WHERE
        (:status IS NULL or a.status = :status)
        AND
        (:position IS NULL or a.position = :position)
    """)
    Page<Application> findApplications(
            @Param(value = "position") String positon,
            @Param(value = "status") Application.Status status,
            Pageable pageable);

    Long  countByStatus(Application.Status status);

    Optional<Application> findByIdAndUserId(Long applicationId, Long userId);

}
