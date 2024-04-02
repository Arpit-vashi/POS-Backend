package org.pgs.postp.controller;

import org.pgs.postp.dto.InvoiceDTO;
import org.pgs.postp.dto.InvoiceReturnDTO;
import org.pgs.postp.model.InvoiceModel;
import org.pgs.postp.service.InvoiceReturnService;
import org.pgs.postp.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin( origins = "http://Localhost:4200")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceReturnService invoiceReturnService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, InvoiceReturnService invoiceReturnService) {
        this.invoiceService = invoiceService;
        this.invoiceReturnService = invoiceReturnService;
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO createdInvoice = invoiceService.createInvoice(invoiceDTO);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable("id") Long id) {
        InvoiceDTO invoiceDTO = invoiceService.getInvoiceById(id);
        return new ResponseEntity<>(invoiceDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        List<InvoiceDTO> invoiceDTOs = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoiceDTOs, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable("id") Long id, @RequestBody InvoiceDTO invoiceDTO) {
        invoiceDTO.setInvoiceID(id);
        InvoiceDTO updatedInvoice = invoiceService.updateInvoice(id, invoiceDTO);
        return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable("id") Long id) {
        invoiceService.deleteInvoice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<Void> returnInvoice(@PathVariable("id") Long id) {
        try {
            // Retrieve the invoice by ID
            InvoiceDTO invoiceDTO = invoiceService.getInvoiceById(id);

            // Create an InvoiceReturnModel instance
            InvoiceReturnDTO invoiceReturnDTO = new InvoiceReturnDTO();
            invoiceReturnDTO.setInvoiceID(id); // Set the ID of the returned invoice
            invoiceReturnDTO.setReturnDate(LocalDateTime.now()); // Set the return date

            // Save the InvoiceReturnModel instance
            invoiceReturnService.createInvoiceReturn(invoiceReturnDTO);

            // Delete the invoice from the Invoice page
            invoiceService.deleteInvoice(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
