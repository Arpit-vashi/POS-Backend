package org.pgs.postp.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.pgs.postp.dto.ProductDTO;
import org.pgs.postp.model.ProductModel;
import org.pgs.postp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getProductsBySupplierId(Long supplierId);
    List<ProductDTO> getProductsByBarcodeNumber(String barcodeNumber);

    ProductDTO getProductById(Long id);
    ProductDTO getProductByBarcodeNumber(String barcodeNumber);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void processCSV(MultipartFile file) throws IOException,WriterException;
    void deleteProduct(Long id);

}

