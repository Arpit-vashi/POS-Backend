package org.pgs.postp.service.Impl;

import org.pgs.postp.dto.InvoiceDTO;
import org.pgs.postp.mapper.InvoiceMapper;
import org.pgs.postp.model.InvoiceModel;
import org.pgs.postp.repository.InvoiceRepository;
import org.pgs.postp.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        List<InvoiceModel> invoices = invoiceRepository.findAll();
        return invoices.stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getInvoiceById(Long id) {
        InvoiceModel invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        InvoiceModel invoice = invoiceMapper.toEntity(invoiceDTO);
        calculateTotalPrice(invoice);
        InvoiceModel savedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDTO(savedInvoice);
    }

    @Override
    public InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO) {
        InvoiceModel existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));

        // Update properties
        existingInvoice.setDateTime(invoiceDTO.getDateTime());
        existingInvoice.setProducts(invoiceDTO.getProducts());
        existingInvoice.setPaymentMethod(invoiceDTO.getPaymentMethod());
        existingInvoice.setBarcodeNumbers(invoiceDTO.getBarcodeNumbers());
        existingInvoice.setCustomerName(invoiceDTO.getCustomerName());
        existingInvoice.setCustomerPhone(invoiceDTO.getCustomerPhone());
        existingInvoice.setVoucher(invoiceDTO.getVoucher());
        existingInvoice.setTotalMRP(invoiceDTO.getTotalMRP());
        existingInvoice.setTotalTax(invoiceDTO.getTotalTax());
        existingInvoice.setTotalDiscount(invoiceDTO.getTotalDiscount());
        existingInvoice.setTotalPrice(invoiceDTO.getTotalPrice());
        existingInvoice.setStatus(invoiceDTO.getStatus());
        existingInvoice.setCartData(invoiceDTO.getCartData());

        // Calculate total price
        calculateTotalPrice(existingInvoice);

        InvoiceModel updatedInvoice = invoiceRepository.save(existingInvoice);
        return invoiceMapper.toDTO(updatedInvoice);
    }

    private void calculateTotalPrice(InvoiceModel invoice) {
        long totalMRP = invoice.getTotalMRP() != null ? invoice.getTotalMRP() : 0;
        long totalTax = invoice.getTotalTax() != null ? invoice.getTotalTax() : 0;
        long totalDiscount = invoice.getTotalDiscount() != null ? invoice.getTotalDiscount() : 0;

        // Calculate total price
        long totalPrice = totalMRP + totalTax - totalDiscount;
        invoice.setTotalPrice(totalPrice);
    }

    @Override
    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new RuntimeException("Invoice not found with id: " + id);
        }
        invoiceRepository.deleteById(id);
    }

    @Override
    public long getInvoiceCount() {
        return invoiceRepository.count();
    }

    @Override
    public Long getTotalMRP() {
        return invoiceRepository.getTotalMRP();
    }

    @Override
    public Long getTotalTax() {
        return invoiceRepository.getTotalTax();
    }

    @Override
    public Long getTotalDiscount() {
        return invoiceRepository.getTotalDiscount();
    }

    @Override
    public Long getTotalPrice() {
        return invoiceRepository.getTotalPrice();
    }

    @Override
    public int getTotalInvoicesCreatedThisWeek() {
        LocalDate startDate = LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDate endDate = startDate.plusDays(6);
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
        return invoiceRepository.countByCreatedAtBetween(startDateTime, endDateTime);
    }

    @Override
    public int getTotalInvoicesCreatedInWeek(LocalDateTime startDate, LocalDateTime endDate) {
        return invoiceRepository.countByCreatedAtBetween(startDate, endDate);
    }

    @Override
    public int getTotalInvoicesCreatedInMonth(LocalDateTime startDate, LocalDateTime endDate) {
        return invoiceRepository.countByCreatedAtBetween(startDate, endDate);
    }

    @Override
    public Long getTotalMRPForWeek(LocalDateTime startDate, LocalDateTime endDate) {
        Long totalMRP = invoiceRepository.getTotalMRPForWeek(startDate, endDate);
        return totalMRP != null ? totalMRP : 0;
    }

    @Override
    public Long getTotalTaxForWeek(LocalDateTime startDate, LocalDateTime endDate) {
        Long totalTax = invoiceRepository.getTotalTaxForWeek(startDate, endDate);
        return totalTax != null ? totalTax : 0;
    }

    @Override
    public Long getTotalDiscountForWeek(LocalDateTime startDate, LocalDateTime endDate) {
        Long totalDiscount = invoiceRepository.getTotalDiscountForWeek(startDate, endDate);
        return totalDiscount != null ? totalDiscount : 0;
    }

    @Override
    public Long getTotalPriceForWeek(LocalDateTime startDate, LocalDateTime endDate) {
        Long totalPrice = invoiceRepository.getTotalPriceForWeek(startDate, endDate);
        return totalPrice != null ? totalPrice : 0;
    }

    @Override
    public Long getTotalMRPForMonth(int year, int month) {
        LocalDateTime startDate = YearMonth.of(year, month).atDay(1).atStartOfDay();
        LocalDateTime endDate = YearMonth.of(year, month).atEndOfMonth().atTime(23, 59, 59);
        Long totalMRP = invoiceRepository.getTotalMRPForMonth(startDate, endDate);
        return totalMRP != null ? totalMRP : 0;
    }

    @Override
    public Long getTotalTaxForMonth(int year, int month) {
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(year, month, startDate.toLocalDate().lengthOfMonth(), 23, 59, 59);
        Long totalTax = invoiceRepository.getTotalTaxForMonth(startDate, endDate);
        return totalTax != null ? totalTax : 0;
    }

    @Override
    public Long getTotalDiscountForMonth(int year, int month) {
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(year, month, startDate.toLocalDate().lengthOfMonth(), 23, 59, 59);
        Long totalDiscount = invoiceRepository.getTotalDiscountForMonth(startDate, endDate);
        return totalDiscount != null ? totalDiscount : 0;
    }

    @Override
    public Long getTotalPriceForMonth(int year, int month) {
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(year, month, startDate.toLocalDate().lengthOfMonth(), 23, 59, 59);
        Long totalPrice = invoiceRepository.getTotalPriceForMonth(startDate, endDate);
        return totalPrice != null ? totalPrice : 0;
    }
}
