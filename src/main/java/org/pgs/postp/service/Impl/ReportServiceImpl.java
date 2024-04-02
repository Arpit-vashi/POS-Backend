package org.pgs.postp.service.Impl;

import org.pgs.postp.dto.ReportDTO;
import org.pgs.postp.mapper.ReportMapper;
import org.pgs.postp.model.ReportModel;
import org.pgs.postp.repository.InvoiceRepository;
import org.pgs.postp.repository.ReportRepository;
import org.pgs.postp.repository.VoucherRepository;
import org.pgs.postp.service.InvoiceService;
import org.pgs.postp.service.ReportService;
import org.pgs.postp.service.UserService;
import org.pgs.postp.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    private final ReportMapper reportMapper;

    private final UserService userService;

    private final VoucherService voucherService;

    private final InvoiceService invoiceService;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, ReportMapper reportMapper, UserService userService, VoucherService voucherService, InvoiceService invoiceService) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
        this.userService = userService;
        this.voucherService = voucherService;
        this.invoiceService = invoiceService;
    }

    @Override
    public List<ReportDTO> getAllUsers() {
        List<ReportModel> reportModels = reportRepository.findAll();
        return reportModels.stream()
                .map(reportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public long getUserCount() {
        return userService.getUserCount();
    }

    @Override
    public long getVoucherCount() {
        return voucherService.getVoucherCount();
    }

    @Override
    public long getInvoiceCount() {
        return invoiceService.getInvoiceCount();
    }
    @Override
    public long getTotalMRP() {
        return invoiceService.getInvoiceCount();
    }
    @Override
    public long getTotalTax() {
        return invoiceService.getTotalTax();
    }

    @Override
    public long getTotalDiscount() {
        return invoiceService.getTotalDiscount();
    }

    @Override
    public long getTotalPrice() {
        return invoiceService.getTotalPrice();
    }

    @Override
    public int getTotalInvoicesCreatedThisWeek() {
        return invoiceService.getTotalInvoicesCreatedThisWeek();
    }

}
