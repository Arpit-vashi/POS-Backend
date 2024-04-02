package org.pgs.postp.repository;

import org.pgs.postp.model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel, Long> {
    // You can add custom query methods if needed

    @Query("SELECT SUM(i.totalMRP) FROM InvoiceModel i")
    Long getTotalMRP();

    @Query("SELECT SUM(i.totalTax) FROM InvoiceModel i")
    Long getTotalTax();

    @Query("SELECT SUM(i.totalDiscount) FROM InvoiceModel i")
    Long getTotalDiscount();

    @Query("SELECT SUM(i.totalPrice) FROM InvoiceModel i")
    Long getTotalPrice();

    //for week
    @Query("SELECT COUNT(i) FROM InvoiceModel i WHERE i.dateTime BETWEEN ?1 AND ?2")
    int countByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT COALESCE(SUM(i.totalMRP), 0) FROM InvoiceModel i WHERE i.dateTime BETWEEN :startDate AND :endDate")
    Long getTotalMRPForWeek(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COALESCE(SUM(i.totalTax), 0) FROM InvoiceModel i WHERE i.dateTime BETWEEN :startDate AND :endDate")
    Long getTotalTaxForWeek(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COALESCE(SUM(i.totalDiscount), 0) FROM InvoiceModel i WHERE i.dateTime BETWEEN :startDate AND :endDate")
    Long getTotalDiscountForWeek(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COALESCE(SUM(i.totalPrice), 0) FROM InvoiceModel i WHERE i.dateTime BETWEEN :startDate AND :endDate")
    Long getTotalPriceForWeek(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COALESCE(SUM(i.totalMRP), 0) FROM InvoiceModel i WHERE i.dateTime BETWEEN :startDate AND :endDate")
    Long getTotalMRPForMonth(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COALESCE(SUM(i.totalTax), 0) FROM InvoiceModel i WHERE i.dateTime BETWEEN :startDate AND :endDate")
    Long getTotalTaxForMonth(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COALESCE(SUM(i.totalDiscount), 0) FROM InvoiceModel i WHERE i.dateTime BETWEEN :startDate AND :endDate")
    Long getTotalDiscountForMonth(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COALESCE(SUM(i.totalPrice), 0) FROM InvoiceModel i WHERE i.dateTime BETWEEN :startDate AND :endDate")
    Long getTotalPriceForMonth(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
