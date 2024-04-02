package org.pgs.postp.service.Impl;

import org.pgs.postp.dto.InventoryDTO;
import org.pgs.postp.mapper.InventoryMapper;
import org.pgs.postp.model.InventoryModel;
import org.pgs.postp.model.ProductModel;
import org.pgs.postp.repository.InventoryRepository;
import org.pgs.postp.repository.ProductRepository;
import org.pgs.postp.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    private final ProductRepository productRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<InventoryDTO> getAllInventories() {
        List<InventoryModel> inventories = inventoryRepository.findAll();
        return inventories.stream()
                .map(inventoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryDTO getInventoryById(Long id) {
        InventoryModel inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));
        return inventoryMapper.toDTO(inventory);
    }

//    @Override
//    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
//        InventoryModel inventory = inventoryMapper.toEntity(inventoryDTO);
//        InventoryModel savedInventory = inventoryRepository.save(inventory);
//        return inventoryMapper.toDTO(savedInventory);
//    }

    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
        if (inventoryDTO.getProductId() == null) {
            throw new IllegalArgumentException("Product ID must be provided");
        }

        // Fetch the product from the database
        ProductModel product = productRepository.findById(inventoryDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + inventoryDTO.getProductId()));

        // Create the InventoryModel entity and set the product
        InventoryModel inventory = new InventoryModel(
                product,
                inventoryDTO.getQuantity());

        // Save the inventory to the database
        InventoryModel savedInventory = inventoryRepository.save(inventory);
        return inventoryMapper.toDTO(savedInventory);
    }

    @Override
    public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) {
        InventoryModel existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));

        if(inventoryDTO.getQuantity()!=null){
            existingInventory.setQuantity(inventoryDTO.getQuantity());
        }

        // Update properties here
        InventoryModel updatedInventory = inventoryRepository.save(existingInventory);
        return inventoryMapper.toDTO(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) {
        if (!inventoryRepository.existsById(id)) {
            throw new RuntimeException("Inventory not found with id: " + id);
        }
        inventoryRepository.deleteById(id);
    }
}
