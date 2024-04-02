package org.pgs.postp.service;

import org.pgs.postp.dto.InvoiceDTO;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceService {
    List<InvoiceDTO> getAllInvoices();

    InvoiceDTO getInvoiceById(Long id);

    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);

    InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO);

    void deleteInvoice(Long id);

    @Query("SELECT COUNT(u) FROM InvoiceModel u")
    long getInvoiceCount();

    // Method to calculate the sum of totalMRP from all invoices
    Long getTotalMRP();

    Long getTotalTax();

    Long getTotalDiscount();

    Long getTotalPrice();

    int getTotalInvoicesCreatedThisWeek();

    //particular week
    int getTotalInvoicesCreatedInWeek(LocalDateTime startDate, LocalDateTime endDate);

    // For a particular month
    int getTotalInvoicesCreatedInMonth(LocalDateTime startDate, LocalDateTime endDate);

    Long getTotalMRPForWeek(LocalDateTime startDate, LocalDateTime endDate);

    Long getTotalTaxForWeek(LocalDateTime startDate, LocalDateTime endDate);

    Long getTotalDiscountForWeek(LocalDateTime startDate, LocalDateTime endDate);

    Long getTotalPriceForWeek(LocalDateTime startDate, LocalDateTime endDate);

    Long getTotalMRPForMonth(int year, int month);

    // Get total tax for invoices created in a particular month
    Long getTotalTaxForMonth(int year, int month);

    // Get total discount for invoices created in a particular month
    Long getTotalDiscountForMonth(int year, int month);

    // Get total price for invoices created in a particular month
    Long getTotalPriceForMonth(int year, int month);
}
