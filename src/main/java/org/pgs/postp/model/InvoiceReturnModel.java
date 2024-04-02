package org.pgs.postp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "InvoiceReturns")
public class InvoiceReturnModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReturnID")
    private Long returnID;

    @ManyToOne
    @JoinColumn(name = "InvoiceID", nullable = false)
    private InvoiceModel invoice;

    // Fields from InvoiceModel
    @Column(name = "DateTime", nullable = false)
    private LocalDateTime invoiceDateTime;

    @ElementCollection
    @CollectionTable(name = "Invoice_Products", joinColumns = @JoinColumn(name = "ReturnID"))
    @Column(name = "Product")
    private List<String> invoiceProducts = new ArrayList<>();

    @Column(name = "PaymentMethod")
    private String invoicePaymentMethod;

    @ElementCollection
    @CollectionTable(name = "Invoice_BarcodeNumbers", joinColumns = @JoinColumn(name = "ReturnID"))
    @Column(name = "BarcodeNumber")
    private List<String> invoiceBarcodeNumbers = new ArrayList<>();

    @Column(name = "CustomerName")
    private String invoiceCustomerName;

    @Column(name = "CustomerPhone")
    private BigInteger invoiceCustomerPhone;

    @Column(name = "Voucher")
    private String invoiceVoucher;

    @Column(name = "TotalMRP")
    private Long invoiceTotalMRP;

    @Column(name = "InvoiceTotalTax")
    private Long invoiceTotalTax;

    @Column(name = "TotalDiscount")
    private Long invoiceTotalDiscount;

    @Column(name = "TotalPrice")
    private Long invoiceTotalPrice;

    @Column(name = "Status", nullable = false)
    private String invoiceStatus;


    @Column(name = "ReturnDate", nullable = false)
    private LocalDateTime returnDate;

    @Column(name = "ReturnReason")
    private String returnReason;


    // Constructors
    public InvoiceReturnModel() {
    }

    public InvoiceReturnModel(InvoiceModel invoice, LocalDateTime invoiceDateTime,  List<String> invoiceProducts, String invoicePaymentMethod,
                              List<String> invoiceBarcodeNumbers,
                              String invoiceCustomerName, BigInteger invoiceCustomerPhone,
                              String invoiceVoucher, Long invoiceTotalMRP, Long invoiceTotalTax, Long invoiceTotalDiscount,
                              Long invoiceTotalPrice, String invoiceStatus,  LocalDateTime returnDate, String returnReason) {
        this.invoice = invoice;
        this.invoiceDateTime = invoice.getDateTime();
        this.invoiceProducts = invoice.getProducts();
        this.invoicePaymentMethod = invoice.getPaymentMethod();
        this.invoiceBarcodeNumbers = invoice.getBarcodeNumbers();
        this.invoiceCustomerName = invoice.getCustomerName();
        this.invoiceCustomerPhone = invoice.getCustomerPhone();
        this.invoiceVoucher  = invoice.getVoucher();
        this.invoiceTotalMRP = invoice.getTotalMRP();
        this.invoiceTotalTax = invoice.getTotalTax();
        this.invoiceTotalDiscount = invoice.getTotalDiscount();
        this.invoiceTotalPrice = invoice.getTotalPrice();
        this.invoiceStatus = invoice.getStatus();
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

    public InvoiceModel getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceModel invoice) {
        this.invoice = invoice;
    }

    // Getters and setters for fields from InvoiceModel

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

    public LocalDateTime getReturnDate() {return returnDate;}

    public void setReturnDate(LocalDateTime returnDate) {this.returnDate = returnDate;}

    public String getReturnReason() {return returnReason;}

    public void setReturnReason(String returnReason) {this.returnReason = returnReason;}

}
