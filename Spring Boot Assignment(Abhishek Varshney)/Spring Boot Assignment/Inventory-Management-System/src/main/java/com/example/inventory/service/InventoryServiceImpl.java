package com.example.inventory.service;

import com.example.inventory.Exception.EntityNotFoundException;
import com.example.inventory.model.Device;
import com.example.inventory.model.ShelfPositionVO;
import com.example.inventory.model.ShelfVO;
import com.example.inventory.repository.DeviceRepository;
import com.example.inventory.repository.ShelfPositionRepository;
import com.example.inventory.repository.ShelfRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    private ShelfRepository shelfRepo;
    @Autowired
    private ShelfPositionRepository shelfPosRepo;
    @Autowired
    private DeviceRepository deviceRepo;

    public ShelfVO saveShelf(ShelfVO shelf) {
        logger.info("Saving new shelf: {}", shelf);
        ShelfVO savedShelf = shelfRepo.save(shelf);
        logger.info("Shelf saved successfully with ID: {}", savedShelf.getId());
        return savedShelf;
    }

    public ShelfPositionVO saveShelfPosition(ShelfPositionVO shelfPos) {
        logger.info("Saving new shelf position: {}", shelfPos);
        ShelfPositionVO savedShelfPos = shelfPosRepo.save(shelfPos);
        logger.info("Shelf position saved successfully with ID: {}", savedShelfPos.getId());
        return savedShelfPos;
    }

    public List<ShelfVO> getShelf() {
        logger.info("Fetching all shelves from database");
        List<ShelfVO> shelves = (List<ShelfVO>) shelfRepo.findAll();
        logger.info("Total shelves fetched: {}", shelves.size());
        return shelves;
    }

    public List<ShelfPositionVO> getShelfPosition() {
        logger.info("Fetching all shelf positions from database");
        List<ShelfPositionVO> shelfPositions = (List<ShelfPositionVO>) shelfPosRepo.findAll();
        logger.info("Total shelf positions fetched: {}", shelfPositions.size());
        return shelfPositions;
    }

    public ShelfVO getShelfById(Long id) {
        logger.info("Fetching shelf with ID: {}", id);
        Optional<ShelfVO> shelf = shelfRepo.findById(id);
        if (shelf.isPresent()) {
            logger.info("Shelf found: {}", shelf.get());
            return shelf.get();
        } else {
            logger.warn("Shelf with ID {} not found", id);
            throw new EntityNotFoundException("Shelf with ID " + id + " not found");
        }
    }

    public ShelfPositionVO getShelfPositionById(Long id) {
        logger.info("Fetching shelf position with ID: {}", id);
        Optional<ShelfPositionVO> shelfPos = shelfPosRepo.findById(id);
        if (shelfPos.isPresent()) {
            logger.info("Shelf position found: {}", shelfPos.get());
            return shelfPos.get();
        } else {
            logger.warn("Shelf position with ID {} not found", id);
            throw new EntityNotFoundException("Shelf position with ID " + id + " not found");
        }
    }

    @Override
    public void addShelfPositionToDevice(Long deviceId, Long shelfPosId) {
        logger.info("Adding shelf position ID {} to device ID {}", shelfPosId, deviceId);

        Device device = deviceRepo.findById(deviceId)
                .orElseThrow(() -> {
                    logger.error("Device with ID {} not found", deviceId);
                    return new EntityNotFoundException("Device with ID " + deviceId + " not found");
                });

        ShelfPositionVO shelfPos = shelfPosRepo.findById(shelfPosId)
                .orElseThrow(() -> {
                    logger.error("Shelf position with ID {} not found", shelfPosId);
                    return new EntityNotFoundException("Shelf Position with ID " + shelfPosId + " not found");
                });

        logger.info("Before adding shelf position: {}", device.getShelfPosition());
        device.getShelfPosition().add(shelfPos);
        shelfPosRepo.save(shelfPos);
        deviceRepo.save(device);
        logger.info("Shelf position added successfully to device: {}", device);
    }

    @Override
    public void addShelfToShelfPosition(Long shelfPosId, Long shelfId) {
        logger.info("Adding shelf ID {} to shelf position ID {}", shelfId, shelfPosId);

        ShelfVO shelf = shelfRepo.findById(shelfId)
                .orElseThrow(() -> {
                    logger.error("Shelf with ID {} not found", shelfId);
                    return new EntityNotFoundException("Shelf with ID " + shelfId + " not found");
                });

        ShelfPositionVO shelfPos = shelfPosRepo.findById(shelfPosId)
                .orElseThrow(() -> {
                    logger.error("Shelf position with ID {} not found", shelfPosId);
                    return new EntityNotFoundException("Shelf Position with ID " + shelfPosId + " not found");
                });

        if (shelfPos.getShelf() != null) {
            logger.warn("ShelfPosition with ID {} is already assigned to another shelf", shelfPosId);
            throw new IllegalStateException("ShelfPosition with ID " + shelfPosId + " is already assigned to another Shelf");
        }

        logger.info("Assigning shelf position ID {} to shelf ID {}", shelfPosId, shelfId);
        shelf.setShelfPositionId(shelfPosId);
        shelfPos.setShelf(shelf);

        shelfRepo.save(shelf);
        shelfPosRepo.save(shelfPos);
        logger.info("Shelf assigned successfully to shelf position");
    }
}
