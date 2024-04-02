package org.pgs.postp.service;

import org.pgs.postp.dto.ReportDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {

    List<ReportDTO>  getAllUsers();

    long getUserCount();

    long getVoucherCount();

    long getInvoiceCount();

    long getTotalMRP();

    long getTotalTax();

    long getTotalDiscount();

    long getTotalPrice();

    int getTotalInvoicesCreatedThisWeek();
}
