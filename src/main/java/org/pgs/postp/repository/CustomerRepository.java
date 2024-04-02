package org.pgs.postp.repository;

import org.pgs.postp.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
    // You can add custom query methods if needed

    // Check if email exists
    boolean existsByEmail(String email);

    // Check if phone number exists
    boolean existsByPhone(BigInteger phone);

    // Check if email exists for other customers (excluding the customer with the given ID)
    boolean existsByEmailAndIdNot(String email, Long id);

    // Check if phone number exists for other customers (excluding the customer with the given ID)
    boolean existsByPhoneAndIdNot(BigInteger phone, Long id);

}
