package org.pgs.postp.service.Impl;

import org.pgs.postp.dto.SupplierDTO;
import org.pgs.postp.mapper.SupplierMapper;
import org.pgs.postp.model.CustomerModel;
import org.pgs.postp.model.SupplierModel;
import org.pgs.postp.repository.SupplierRepository;
import org.pgs.postp.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        List<SupplierModel> suppliers = supplierRepository.findAll();
        return suppliers.stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDTO getSupplierById(Long id) {
        SupplierModel supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        return supplierMapper.toDTO(supplier);
    }

    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {

        // Check if the supplier email already exists
        if (supplierDTO.getSupplierEmail() != null && supplierRepository.existsBySupplierEmail(supplierDTO.getSupplierEmail())) {
            throw new RuntimeException("Supplier email already exists: " + supplierDTO.getSupplierEmail());
        }

        // Check if the supplier phone number already exists
        if (supplierDTO.getSupplierPhone() != null && supplierRepository.existsBySupplierPhone(supplierDTO.getSupplierPhone())) {
            throw new RuntimeException(" Supplier phone number already exists: " + supplierDTO.getSupplierPhone());
        }

        // Check if the contact person email already exists
        if (supplierDTO.getContactPersonEmail() != null && supplierRepository.existsByContactPersonEmail(supplierDTO.getContactPersonEmail())) {
            throw new RuntimeException("Contact person email already exists: " + supplierDTO.getContactPersonEmail());
        }

        // Check if the contact person phone number already exists
        if (supplierDTO.getContactPersonPhone() != null && supplierRepository.existsByContactPersonPhone(supplierDTO.getContactPersonPhone())) {
            throw new RuntimeException("Contact person phone number already exists: " + supplierDTO.getContactPersonPhone());
        }

        SupplierModel supplier = supplierMapper.toEntity(supplierDTO);
        SupplierModel savedSupplier = supplierRepository.save(supplier);
        return supplierMapper.toDTO(savedSupplier);
    }

    @Override
    public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
        SupplierModel existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));


        if(supplierDTO.getSupplierAgency()!=null){ // Updated field name
            existingSupplier.setSupplierAgency(supplierDTO.getSupplierAgency()); // Updated field name
        }
        if(supplierDTO.getContactPerson()!=null){ // Updated field name
            existingSupplier.setContactPerson(supplierDTO.getContactPerson()); // Updated field name
        }
        if(supplierDTO.getSupplierEmail()!=null){ // Updated field name
            existingSupplier.setSupplierEmail(supplierDTO.getSupplierEmail()); // Updated field name
        }
        if(supplierDTO.getSupplierPhone()!=null){ // Updated field name
            existingSupplier.setSupplierPhone(supplierDTO.getSupplierPhone()); // Updated field name
        }
        if(supplierDTO.getContactPersonEmail()!=null){ // Updated field name
            existingSupplier.setContactPersonEmail(supplierDTO.getContactPersonEmail()); // Updated field name
        }
        if(supplierDTO.getContactPersonPhone()!=null){ // Updated field name
            existingSupplier.setContactPersonPhone(supplierDTO.getContactPersonPhone()); // Updated field name
        }

        if(supplierDTO.getAddress()!=null){ // Updated field address
            existingSupplier.setAddress(supplierDTO.getAddress()); // Updated field address
        }

        // Update properties here
        SupplierModel updatedSupplier = supplierRepository.save(existingSupplier);
        return supplierMapper.toDTO(updatedSupplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new RuntimeException("Supplier not found with id: " + id);
        }
        supplierRepository.deleteById(id);
    }

    @Override
    public void processCSV(MultipartFile file) throws IOException {
        // Check CSV file headers
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
//        String headerLine = br.readLine();
//        if (headerLine == null || !headerLine.equals("SupplierAgency, ContactPerson, SupplierEmail, SupplierPhone, ContactPersonEmail, ContactPersonPhone, Address")) {
//            throw new IllegalArgumentException("Invalid CSV file format or headers");
//        }
        // Skip the header line
        br.readLine();

        String line;

        List<SupplierModel> suppliersToAdd = new ArrayList<>();


        List<String> existingSupplierEmails = supplierRepository.findAll().stream()
                .map(SupplierModel::getSupplierEmail)
                .collect(Collectors.toList());
        List<BigInteger> existingSupplierPhones = supplierRepository.findAll().stream()
                .map(SupplierModel::getSupplierPhone)
                .collect(Collectors.toList());
        List<String> existingContactPersonEmails = supplierRepository.findAll().stream()
                .map(SupplierModel::getContactPersonEmail)
                //.filter(Objects::nonNull) // Filter out null values
                .collect(Collectors.toList());
        List<BigInteger> existingContactPersonPhones = supplierRepository.findAll().stream()
                .map(SupplierModel::getContactPersonPhone)
               // .filter(Objects::nonNull) // Filter out null values
                .collect(Collectors.toList());


        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String supplierAgency = data[0].trim();
            String contactPerson = data[1].trim();
            String supplierEmail = data[2].trim();
            BigInteger supplierPhone = new BigInteger(data[3].trim());
            String contactPersonEmail = data[4].trim();
            BigInteger contactPersonPhone = new BigInteger(data[5].trim());
            String address = data[6].trim();

            if (existingSupplierEmails.contains(supplierEmail) || existingSupplierPhones.contains(supplierPhone) ||
                    existingContactPersonEmails.contains(contactPersonEmail) || existingContactPersonPhones.contains(contactPersonPhone)) {
                System.out.println("Duplicate email or phone found in CSV, skipping record: " + supplierEmail + " / " + supplierPhone + contactPersonEmail + " / " + contactPersonPhone);
                continue;
            }
//            if (existingSupplierPhones.contains(supplierPhone)) {
//                throw new RuntimeException("Duplicate supplier phone number found in CSV: " + supplierPhone);
//            }
//            if (existingContactPersonEmails.contains(contactPersonEmail)) {
//                throw new RuntimeException("Duplicate contact person email found in CSV: " + contactPersonEmail);
//            }
//            if (existingContactPersonPhones.contains(contactPersonPhone)) {
//                throw new RuntimeException("Duplicate contact person phone number found in CSV: " + contactPersonPhone);
//            }


//            if (contactPersonEmail != null) {
//                existingContactPersonEmails.add(contactPersonEmail);
//            }
//            if (contactPersonPhone != null) {
//                existingContactPersonPhones.add(contactPersonPhone);
//            }
            SupplierModel supplierModel = new SupplierModel();
            supplierModel.setSupplierAgency(supplierAgency);
            supplierModel.setContactPerson(contactPerson);
            supplierModel.setSupplierEmail(supplierEmail);
            supplierModel.setSupplierPhone(supplierPhone);
            supplierModel.setContactPersonEmail(contactPersonEmail);
            supplierModel.setContactPersonPhone(contactPersonPhone);
            supplierModel.setAddress(address);

            suppliersToAdd.add(supplierModel);
            existingSupplierEmails.add(supplierEmail);
            existingSupplierPhones.add(supplierPhone);
            existingContactPersonEmails.add(contactPersonEmail);
            existingContactPersonPhones.add(contactPersonPhone);

        }

        // Save only the non-duplicate customer models
        supplierRepository.saveAll(suppliersToAdd);
        br.close();
    }


}