package org.pgs.postp.service.Impl;

import org.pgs.postp.dto.CustomerDTO;
import org.pgs.postp.mapper.CustomerMapper;
import org.pgs.postp.model.CustomerModel;
import org.pgs.postp.model.ProductModel;
import org.pgs.postp.repository.CustomerRepository;
import org.pgs.postp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerModel> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        CustomerModel customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        if (customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new RuntimeException("Email already exists: " + customerDTO.getEmail());
        }
        if (customerRepository.existsByPhone(customerDTO.getPhone())) {
            throw new RuntimeException("Phone number already exists: " + customerDTO.getPhone());
        }

        CustomerModel customer = customerMapper.toEntity(customerDTO);
        CustomerModel savedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        CustomerModel existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));


        if (customerRepository.existsByEmailAndIdNot(customerDTO.getEmail(), id)) {
            throw new RuntimeException("Email already exists: " + customerDTO.getEmail());
        }
        if (customerRepository.existsByPhoneAndIdNot(customerDTO.getPhone(), id)) {
            throw new RuntimeException("Phone number already exists: " + customerDTO.getPhone());
        }


        if(customerDTO.getName()!=null){
            existingCustomer.setName(customerDTO.getName());
        }
        if(customerDTO.getEmail()!=null){
            existingCustomer.setEmail(customerDTO.getEmail());
        }
        if(customerDTO.getPhone()!=null){
            existingCustomer.setPhone(customerDTO.getPhone());
        }
        if(customerDTO.getAddress()!=null){
            existingCustomer.setAddress(customerDTO.getAddress());
        }
//        existingCustomer.setName(customerDTO.getName());
//        existingCustomer.setEmail(customerDTO.getEmail());
//        existingCustomer.setPhone(customerDTO.getPhone());
        CustomerModel updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
    @Override
    public void processCSV(MultipartFile file) throws IOException {
        // Check CSV file headers
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
//        String headerLine = br.readLine();
//        if (headerLine == null || !headerLine.equals("Name, Email, Phone, Address")) {
//            throw new IllegalArgumentException("Invalid CSV file format or headers");
//        }

        // Skip the header line
        br.readLine();


        String line;

        List<CustomerModel> customersToAdd = new ArrayList<>();

        List<String> existingEmails = customerRepository.findAll().stream()
                .map(CustomerModel::getEmail)
                .collect(Collectors.toList());
        List<BigInteger> existingPhones = customerRepository.findAll().stream()
                .map(CustomerModel::getPhone)
                .collect(Collectors.toList());


        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String name = data[0].trim();
            String email = data[1].trim();
            BigInteger phone = new BigInteger(data[2].trim());
            String address = data[3].trim();

//            if (customerRepository.existsByEmail(email) || customerRepository.existsByPhone(phone)) {
//                // Skip saving if email or phone already exists
//                continue;
//            }

            if (existingEmails.contains(email) || existingPhones.contains(phone)) {
                System.out.println("Duplicate email or phone found in CSV, skipping record: " + email + " / " + phone);
                continue;
//                throw new RuntimeException("Duplicate email found in CSV: " + email);
            }
//            if (existingPhones.contains(phone)) {
//                throw new RuntimeException("Duplicate phone number found in CSV: " + phone);
//            }


            CustomerModel customerModel = new CustomerModel();
            customerModel.setName(name);
            customerModel.setEmail(email);
            customerModel.setPhone(phone);
            customerModel.setAddress(address);

            customersToAdd.add(customerModel);
            existingEmails.add(email);
            existingPhones.add(phone);
        }

        // Save only the non-duplicate customer models
        customerRepository.saveAll(customersToAdd);
        br.close();
    }
}
