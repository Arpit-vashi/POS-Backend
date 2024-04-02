package org.pgs.postp.repository;

import org.pgs.postp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    // You can add custom query methods if needed

    boolean existsByEmail(String email);

    boolean existsByPhone(BigInteger phone);

}
