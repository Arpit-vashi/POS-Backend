package org.pgs.postp.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public class InvoiceReturnDTO {
    private Long returnID;
    private Long invoiceId;

    private LocalDateTime invoiceDateTime;
    private List<String> invoiceProducts;
    private String invoicePaymentMethod;
    private List<String> invoiceBarcodeNumbers;
    private String invoiceCustomerName;
    private BigInteger invoiceCustomerPhone;
    private String invoiceVoucher;
    private Long invoiceTotalMRP;
    private Long invoiceTotalTax;
    private Long invoiceTotalDiscount;
    private Long invoiceTotalPrice;
    private String invoiceStatus;
    private LocalDateTime returnDate;
    private String returnReason;

    // Constructors
    public InvoiceReturnDTO() {
    }

    public InvoiceReturnDTO(Long returnID, Long invoiceId, LocalDateTime invoiceDateTime, List<String> invoiceProducts, String invoicePaymentMethod,
                            List<String> invoiceBarcodeNumbers,
                            String invoiceCustomerName, BigInteger invoiceCustomerPhone,
                            String invoiceVoucher, Long invoiceTotalMRP, Long invoiceTotalTax, Long invoiceTotalDiscount,
                            Long invoiceTotalPrice, String invoiceStatus, LocalDateTime returnDate, String returnReason) {
        this.returnID = returnID;
        this.invoiceId = invoiceId;
        this.invoiceDateTime = invoiceDateTime;
        this.invoiceProducts = invoiceProducts;
        this.invoicePaymentMethod = invoicePaymentMethod;
        this.invoiceBarcodeNumbers = invoiceBarcodeNumbers;
        this.invoiceCustomerName = invoiceCustomerName;
        this.invoiceCustomerPhone = invoiceCustomerPhone;
        this.invoiceVoucher = invoiceVoucher;
        this.invoiceTotalMRP = invoiceTotalMRP;
        this.invoiceTotalTax = invoiceTotalTax;
        this.invoiceTotalDiscount = invoiceTotalDiscount;
        this.invoiceTotalPrice = invoiceTotalPrice;
        this.invoiceStatus = invoiceStatus;
        this.returnDate = returnDate;
        this.returnReason = returnReason;
    }

    // Getters and Setters
    public Long getReturnID() {
        return returnID;
    }

    public void setReturnID(Long returnID) {
        this.returnID = returnID;
    }

    public Long getInvoiceID() {
        return invoiceId;
    }

    public void setInvoiceID(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDateTime getInvoiceDateTime() {
        return invoiceDateTime;
    }

    public void setInvoiceDateTime(LocalDateTime invoiceDateTime) {
        this.invoiceDateTime = invoiceDateTime;
    }

    public List<String> getInvoiceProducts() {
        return invoiceProducts;
    }

    public void setInvoiceProducts(List<String> invoiceProducts) {
        this.invoiceProducts = invoiceProducts;
    }

    public String getInvoicePaymentMethod() {
        return invoicePaymentMethod;
    }

    public void setInvoicePaymentMethod(String invoicePaymentMethod) {
        this.invoicePaymentMethod = invoicePaymentMethod;
    }

    public List<String> getInvoiceBarcodeNumbers() {
        return invoiceBarcodeNumbers;
    }

    public void setInvoiceBarcodeNumbers(List<String> invoiceBarcodeNumbers) {
        this.invoiceBarcodeNumbers = invoiceBarcodeNumbers;
    }

    public String getInvoiceCustomerName() {
        return invoiceCustomerName;
    }

    public void setInvoiceCustomerName(String invoiceCustomerName) {
        this.invoiceCustomerName = invoiceCustomerName;
    }

    public BigInteger getInvoiceCustomerPhone() {
        return invoiceCustomerPhone;
    }

    public void setInvoiceCustomerPhone(BigInteger invoiceCustomerPhone) {
        this.invoiceCustomerPhone = invoiceCustomerPhone;
    }

    public String getInvoiceVoucher() {
        return invoiceVoucher;
    }

    public void setInvoiceVoucher(String invoiceVoucher) {
        this.invoiceVoucher = invoiceVoucher;
    }

    public Long getInvoiceTotalMRP() {
        return invoiceTotalMRP;
    }

    public void setInvoiceTotalMRP(Long invoiceTotalMRP) {
        this.invoiceTotalMRP = invoiceTotalMRP;
    }

    public Long getInvoiceTotalTax() {
        return invoiceTotalTax;
    }

    public void setInvoiceTotalTax(Long invoiceTotalTax) {
        this.invoiceTotalTax = invoiceTotalTax;
    }

    public Long getInvoiceTotalDiscount() {
        return invoiceTotalDiscount;
    }

    public void setInvoiceTotalDiscount(Long invoiceTotalDiscount) {
        this.invoiceTotalDiscount = invoiceTotalDiscount;
    }

    public Long getInvoiceTotalPrice() {
        return invoiceTotalPrice;
    }

    public void setInvoiceTotalPrice(Long invoiceTotalPrice) {
        this.invoiceTotalPrice = invoiceTotalPrice;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

}
