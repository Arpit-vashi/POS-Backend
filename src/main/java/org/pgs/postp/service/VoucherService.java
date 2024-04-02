package org.pgs.postp.service;

import org.pgs.postp.dto.VoucherDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoucherService {
    List<VoucherDTO> getAllVouchers();

    VoucherDTO getVoucherById(Long id);

    VoucherDTO createVoucher(VoucherDTO voucherDTO);

    VoucherDTO updateVoucher(Long id, VoucherDTO voucherDTO); // Updated method signature

    void deleteVoucher(Long id);

    @Query("SELECT COUNT(u) FROM VoucherModel u")
    long getVoucherCount();

}
