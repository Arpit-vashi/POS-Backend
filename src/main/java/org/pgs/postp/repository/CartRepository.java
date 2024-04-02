package org.pgs.postp.repository;

import org.pgs.postp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // Define additional methods if needed
}
