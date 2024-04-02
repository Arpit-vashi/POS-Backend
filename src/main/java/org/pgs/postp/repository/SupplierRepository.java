package org.pgs.postp.repository;

import org.pgs.postp.model.SupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierModel, Long> {
    // You can add custom query methods if needed

    boolean existsBySupplierEmail(String supplierEmail);

    boolean existsBySupplierPhone(BigInteger supplierPhone);

    boolean existsByContactPersonEmail(String contactPersonEmail);

    boolean existsByContactPersonPhone(BigInteger contactPersonPhone);

}
