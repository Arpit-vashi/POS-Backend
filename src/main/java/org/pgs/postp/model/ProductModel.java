package org.pgs.postp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Products")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Long productId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Column(name = "Tax", nullable = false)
    private BigDecimal tax;

    @Column(name = "Total", nullable = false)
    private BigDecimal total;

    @Column(name = "StockQuantity", nullable = false)
    private BigDecimal stockQuantity;

    @Column(name = "PurchasePrice", nullable = false)
    private BigDecimal purchasePrice;

    @Column(name = "BarcodeNumber", unique = true, nullable = false)
    private String barcodeNumber;

    @Lob
    @Column(name = "BarcodeImage")
    private byte[] barcodeImage;

    @ManyToMany
    @JoinTable(
            name = "Product_Supplier",
            joinColumns = @JoinColumn(name = "ProductID"),
            inverseJoinColumns = @JoinColumn(name = "SupplierID")
    )

    private List<SupplierModel> suppliers;

    // Constructors
    public ProductModel() {
    }

    public ProductModel(String name, String description, BigDecimal price, BigDecimal tax, BigDecimal total, BigDecimal stockQuantity, BigDecimal purchasePrice, String barcodeNumber, byte[] barcodeImage, List<SupplierModel> suppliers) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.tax = tax;
        this.total = total;
        this.stockQuantity = stockQuantity;
        this.purchasePrice = purchasePrice;
        this.barcodeNumber = barcodeNumber;
        this.barcodeImage = barcodeImage;
        this.suppliers = suppliers;
    }

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(BigDecimal stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    public byte[] getBarcodeImage() {
        return barcodeImage;
    }

    public void setBarcodeImage(byte[] barcodeImage) {
        this.barcodeImage = barcodeImage;
    }

    public List<SupplierModel> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierModel> suppliers) {
        this.suppliers = suppliers;
    }

}
