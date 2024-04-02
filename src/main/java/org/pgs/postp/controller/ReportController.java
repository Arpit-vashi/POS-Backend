package org.pgs.postp.controller;

import org.pgs.postp.dto.ReportDTO;
import org.pgs.postp.dto.UserDTO;
import org.pgs.postp.service.Impl.ReportServiceImpl;
import org.pgs.postp.service.InvoiceService;
import org.pgs.postp.service.ReportService;
import org.pgs.postp.service.UserService;
import org.pgs.postp.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/reports")
@CrossOrigin( origins = "http://Localhost:4200")
public class ReportController {

    private final ReportService reportService;

    private final ReportServiceImpl reportServiceImpl;

    private final UserService userService;

    private final VoucherService voucherService;

    private final InvoiceService invoiceService;

    @Autowired
    public ReportController(ReportService reportService, ReportServiceImpl reportServiceImpl, UserService userService, VoucherService voucherService, InvoiceService invoiceService) {
        this.reportService = reportService;
        this.reportServiceImpl = reportServiceImpl;
        this.userService = userService;
        this.voucherService = voucherService;
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOs = userService.getAllUsers();
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping("/userCount")
    public ResponseEntity<Long> getUserCount() {
        long userCount = userService.getUserCount();
        return new ResponseEntity<>(userCount, HttpStatus.OK);
    }

    @GetMapping("/voucherCount")
    public ResponseEntity<Long> getVoucherCount() {
        long voucherCount = voucherService.getVoucherCount();
        return new ResponseEntity<>(voucherCount, HttpStatus.OK);
    }

    @GetMapping("/invoiceCount")
    public ResponseEntity<Long> getInvoiceCount() {
        long invoiceCount = invoiceService.getInvoiceCount();
        return new ResponseEntity<>(invoiceCount, HttpStatus.OK);
    }

    @GetMapping("/totalMRP")
    public ResponseEntity<Long> getTotalMRP() {
        long totalMRP = invoiceService.getTotalMRP();
        return new ResponseEntity<>(totalMRP, HttpStatus.OK);
    }

    @GetMapping("/totalTax")
    public ResponseEntity<Long> getTotalTax() {
        long totalTax = invoiceService.getTotalTax();
        return new ResponseEntity<>(totalTax, HttpStatus.OK);
    }

    @GetMapping("/totalDiscount")
    public ResponseEntity<Long> getTotalDiscount() {
        long totalDiscount = invoiceService.getTotalDiscount();
        return new ResponseEntity<>(totalDiscount, HttpStatus.OK);
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<Long> getTotalPrice() {
        long totalPrice = invoiceService.getTotalPrice();
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    //this API for only this week
    @GetMapping("/totalInvoicesCreatedThisWeek")
    public ResponseEntity<Long> getTotalInvoicesCreatedThisWeek() {
        long totalInvoicesCreatedThisWeek = invoiceService.getTotalInvoicesCreatedThisWeek();
        return new ResponseEntity<>(totalInvoicesCreatedThisWeek, HttpStatus.OK);
    }

    // you can write as: http://localhost:8080/reports/invoices/total?startDate=2024-03-22T12:30:45&endDate=2024-03-28T12:30:45
    //means add--    ?startDate=2024-03-22T12:30:45&endDate=2024-03-28T12:30:45
    //this API for particular week(StartDate to EndDate)
    @GetMapping("/invoices/total/week")
    public ResponseEntity<Object> getTotalInvoicesForWeek(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        // Call service method to get total invoices for the week
        int totalInvoicesForWeek = invoiceService.getTotalInvoicesCreatedInWeek(startDate, endDate);

        // Check if totalInvoicesForWeek is zero, indicating no records found
        if (totalInvoicesForWeek == 0) {
            // Return a custom error message with status code 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No entry found for the specified week");
        }

        // Return the total invoices if records are found
        return ResponseEntity.ok(totalInvoicesForWeek);
    }


    // you can write as: http://localhost:8080/reports/invoices/total/month?year=2024&month=3
    //means add--   ?year=2024&month=3
    // API for getting total invoices created within a particular month
    @GetMapping("/invoices/total/month")
    public ResponseEntity<Object> getTotalInvoicesForMonth(
            @RequestParam("year") int year,
            @RequestParam("month") int month) {
        // Create start and end date for the given month
        LocalDateTime startDate = YearMonth.of(year, month).atDay(1).atStartOfDay();
        LocalDateTime endDate = YearMonth.of(year, month).atEndOfMonth().atTime(23, 59, 59);

        // Call service method to get total invoices for the month
        int totalInvoicesForMonth = invoiceService.getTotalInvoicesCreatedInMonth(startDate, endDate);

        // Check if totalInvoicesForMonth is zero, indicating no records found
        if (totalInvoicesForMonth == 0) {
            // Return a custom error message with status code 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No entry found for the specified month");
        }

        // Return the total invoices if records are found
        return ResponseEntity.ok(totalInvoicesForMonth);
    }


    @GetMapping("/totalMRPForWeek")
    public ResponseEntity<Object> getTotalMRPForWeek(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        Long totalMRPForWeek = invoiceService.getTotalMRPForWeek(startDate, endDate);
        // Check if totalMRP or Month is null or zero, indicating no records found
        if (totalMRPForWeek == null || totalMRPForWeek == 0) {
            // Return a custom error message with status code 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No entry found for the specified week");
        }

        // Return the total MRP if records are found
        return ResponseEntity.ok(totalMRPForWeek);
    }

    @GetMapping("/totalTaxForWeek")
    public ResponseEntity<Object> getTotalTaxForWeek(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        Long totalTaxForWeek = invoiceService.getTotalTaxForWeek(startDate, endDate);
        // Check if totalTax or Month is null or zero, indicating no records found
        if (totalTaxForWeek == null || totalTaxForWeek == 0) {
            // Return a custom error message with status code 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No entry found for the specified week");
        }

        // Return the totalTax if records are found
        return ResponseEntity.ok(totalTaxForWeek);
    }


    @GetMapping("/totalDiscountForWeek")
    public ResponseEntity<Object> getTotalDiscountForWeek(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        Long totalDiscountForWeek = invoiceService.getTotalDiscountForWeek(startDate, endDate);
        // Check if totalDiscount or Month is null or zero, indicating no records found
        if (totalDiscountForWeek == null || totalDiscountForWeek == 0) {
            // Return a custom error message with status code 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No entry found for the specified week");
        }

        // Return the totalDiscount if records are found
        return ResponseEntity.ok(totalDiscountForWeek);
    }

    @GetMapping("/totalPriceForWeek")
    public ResponseEntity<Object> getTotalPriceForWeek(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        Long totalPriceForWeek = invoiceService.getTotalPriceForWeek(startDate, endDate);
        // Check if totalPrice or Month is null or zero, indicating no records found
        if (totalPriceForWeek == null || totalPriceForWeek == 0) {
            // Return a custom error message with status code 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No entry found for the specified week");
        }

        // Return the totalPrice if records are found
        return ResponseEntity.ok(totalPriceForWeek);
    }

    @GetMapping("/totalMRPForMonth")
    public ResponseEntity<Object> getTotalMRPForMonth(
            @RequestParam("year") int year,
            @RequestParam("month") int month) {
        // Create start and end date for the given month
        LocalDateTime startDate = YearMonth.of(year, month).atDay(1).atStartOfDay();
        LocalDateTime endDate = YearMonth.of(year, month).atEndOfMonth().atTime(23, 59, 59);

        // Call service method to get total MRP for the month
        Long totalMRPForMonth = invoiceService.getTotalMRPForMonth(year, month);

        // Check if totalMRPForMonth is null or zero, indicating no records found
        if (totalMRPForMonth == null || totalMRPForMonth == 0) {
            // Return a custom error message with status code 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No entry found for the specified month");
        }

        // Return the total MRP if records are found
        return ResponseEntity.ok(totalMRPForMonth);
    }

    @GetMapping("/totalTaxForMonth")
    public ResponseEntity<Object> getTotalTaxForMonth(
            @RequestParam("year") int year,
            @RequestParam("month") int month) {
        // Create start and end date for the given month
        LocalDateTime startDate = YearMonth.of(year, month).atDay(1).atStartOfDay();
        LocalDateTime endDate = YearMonth.of(year, month).atEndOfMonth().atTime(23, 59, 59);

        // Call service method to get total tax for the month
        Long totalTaxForMonth = invoiceService.getTotalTaxForMonth(year, month);

        // Check if totalTaxForMonth is null or zero, indicating no records found
        if (totalTaxForMonth == null || totalTaxForMonth == 0) {
            // Return a custom error message with status code 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No entry found for the specified month");
        }

        // Return the total tax if records are found
        return ResponseEntity.ok(totalTaxForMonth);
    }


    @GetMapping("/totalDiscountForMonth")
    public ResponseEntity<Object> getTotalDiscountForMonth(
            @RequestParam("year") int year,
            @RequestParam("month") int month) {
        // Create start and end date for the given month
        LocalDateTime startDate = YearMonth.of(year, month).atDay(1).atStartOfDay();
        LocalDateTime endDate = YearMonth.of(year, month).atEndOfMonth().atTime(23, 59, 59);

        // Call service method to get total discount for the month
        Long totalDiscountForMonth = invoiceService.getTotalDiscountForMonth(year, month);
        // Check if totalDiscountForMonth is null, indicating no records found
        if (totalDiscountForMonth == null || totalDiscountForMonth == 0) {
            // Return a custom error message with status code 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No entry found for the specified month");
        }

        // Return the total discount if records are found
        return ResponseEntity.ok(totalDiscountForMonth);
    }

    @GetMapping("/totalPriceForMonth")
    public ResponseEntity<Object> getTotalPriceForMonth(
            @RequestParam("year") int year,
            @RequestParam("month") int month) {
        // Create start and end date for the given month
        LocalDateTime startDate = YearMonth.of(year, month).atDay(1).atStartOfDay();
        LocalDateTime endDate = YearMonth.of(year, month).atEndOfMonth().atTime(23, 59, 59);

        // Call service method to get total price for the month
        Long totalPriceForMonth = invoiceService.getTotalPriceForMonth(year, month);
        // Check if totalPriceForMonth is null, indicating no records found
        if (totalPriceForMonth == null || totalPriceForMonth == 0) {
            // Return a custom error message with status code 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No entry found for the specified month");
        }
        // Return the total price if records are found
        return ResponseEntity.ok(totalPriceForMonth);
    }
}
