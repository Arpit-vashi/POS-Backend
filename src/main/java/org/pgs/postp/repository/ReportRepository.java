package org.pgs.postp.repository;

import org.pgs.postp.model.ReportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReportRepository extends JpaRepository<ReportModel, Long> {

    @Query("SELECT COUNT(u) FROM UserModel u")
    long getUserCount();

}
