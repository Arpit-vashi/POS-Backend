package org.pgs.postp.service.Impl;


import org.pgs.postp.dto.InvoiceReturnDTO;
import org.pgs.postp.mapper.InvoiceReturnMapper;
import org.pgs.postp.model.InvoiceModel;
import org.pgs.postp.model.InvoiceReturnModel;
import org.pgs.postp.repository.InvoiceReturnRepository;
import org.pgs.postp.service.InvoiceReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceReturnServiceImpl implements InvoiceReturnService {

    private final InvoiceReturnRepository invoiceReturnRepository;
    private final InvoiceReturnMapper invoiceReturnMapper;

    @Autowired
    public InvoiceReturnServiceImpl(InvoiceReturnRepository invoiceReturnRepository, InvoiceReturnMapper invoiceReturnMapper) {
        this.invoiceReturnRepository = invoiceReturnRepository;
        this.invoiceReturnMapper = invoiceReturnMapper;
    }

    @Override
    public List<InvoiceReturnDTO> getAllInvoiceReturns() {
        List<InvoiceReturnModel> invoiceReturns = invoiceReturnRepository.findAll();
        return invoiceReturns.stream()
                .map(invoiceReturnMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceReturnDTO getInvoiceReturnById(Long id) {
        InvoiceReturnModel invoiceReturn = invoiceReturnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice return not found with id: " + id));
        return invoiceReturnMapper.toDTO(invoiceReturn);
    }

    @Override
    public InvoiceReturnDTO createInvoiceReturn(InvoiceReturnDTO invoiceReturnDTO) {
        InvoiceReturnModel invoiceReturn = invoiceReturnMapper.toEntity(invoiceReturnDTO);
        InvoiceReturnModel savedInvoiceReturn = invoiceReturnRepository.save(invoiceReturn);
        // Create a new instance of InvoiceReturnModel
        InvoiceReturnModel invoiceReturnModel = new InvoiceReturnModel();

        // Populate InvoiceReturnModel with data from InvoiceReturnDTO
        if (invoiceReturnDTO.getInvoiceDateTime() != null) {
            invoiceReturnModel.setInvoiceDateTime(invoiceReturnDTO.getInvoiceDateTime());
        } else {
            // Set invoiceDateTime to the current date and time
            invoiceReturnModel.setInvoiceDateTime(LocalDateTime.now());
        }
        // Populate other properties similarly...

        // Save the InvoiceReturnModel instance
        invoiceReturnRepository.save(invoiceReturnModel);
        return invoiceReturnMapper.toDTO(savedInvoiceReturn);
    }

    @Override
    public InvoiceReturnDTO updateInvoiceReturn(Long id, InvoiceReturnDTO invoiceReturnDTO) {
        InvoiceReturnModel existingInvoiceReturn = invoiceReturnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice return not found with id: " + id));
        // Update properties here

        if (invoiceReturnDTO.getReturnDate() != null) {
            existingInvoiceReturn.setReturnDate(invoiceReturnDTO.getReturnDate());
        }

        if (invoiceReturnDTO.getReturnReason() != null) {
            existingInvoiceReturn.setReturnReason(invoiceReturnDTO.getReturnReason());
        }

        // Update properties related to the associated invoice
        InvoiceModel invoice = existingInvoiceReturn.getInvoice();
        if (invoiceReturnDTO.getInvoiceID() != null) {
            invoice.setInvoiceID(invoiceReturnDTO.getInvoiceID());
        }
        if (invoiceReturnDTO.getInvoiceDateTime() != null) {
            invoice.setDateTime(invoiceReturnDTO.getInvoiceDateTime());
        }

        if (invoiceReturnDTO.getInvoiceProducts() != null) {
            invoice.setProducts(invoiceReturnDTO.getInvoiceProducts());
        }

        if (invoiceReturnDTO.getInvoicePaymentMethod() != null) {
            invoice.setPaymentMethod(invoiceReturnDTO.getInvoicePaymentMethod());
        }

        if (invoiceReturnDTO.getInvoiceBarcodeNumbers() != null) {
            invoice.setBarcodeNumbers(invoiceReturnDTO.getInvoiceBarcodeNumbers());
        }

        if (invoiceReturnDTO.getInvoiceCustomerName() != null) {
            invoice.setCustomerName(invoiceReturnDTO.getInvoiceCustomerName());
        }

        if (invoiceReturnDTO.getInvoiceCustomerPhone() != null) {
            invoice.setCustomerPhone(invoiceReturnDTO.getInvoiceCustomerPhone());
        }

        if (invoiceReturnDTO.getInvoiceVoucher() != null) {
            invoice.setVoucher(invoiceReturnDTO.getInvoiceVoucher());
        }

        if (invoiceReturnDTO.getInvoiceTotalMRP() != null) {
            invoice.setTotalMRP(invoiceReturnDTO.getInvoiceTotalMRP());
        }

        if (invoiceReturnDTO.getInvoiceTotalTax() != null) {
            invoice.setTotalTax(invoiceReturnDTO.getInvoiceTotalTax());
        }

        if (invoiceReturnDTO.getInvoiceTotalDiscount() != null) {
            invoice.setTotalDiscount(invoiceReturnDTO.getInvoiceTotalDiscount());
        }

        if (invoiceReturnDTO.getInvoiceTotalPrice() != null) {
            invoice.setTotalPrice(invoiceReturnDTO.getInvoiceTotalPrice());
        }

        if (invoiceReturnDTO.getInvoiceStatus() != null) {
            invoice.setStatus(invoiceReturnDTO.getInvoiceStatus());
        }

        InvoiceReturnModel updatedInvoiceReturn = invoiceReturnRepository.save(existingInvoiceReturn);
        return invoiceReturnMapper.toDTO(updatedInvoiceReturn);
    }

    @Override
    public void deleteInvoiceReturn(Long id) {
        if (!invoiceReturnRepository.existsById(id)) {
            throw new RuntimeException("Invoice return not found with id: " + id);
        }
        invoiceReturnRepository.deleteById(id);
    }
}
