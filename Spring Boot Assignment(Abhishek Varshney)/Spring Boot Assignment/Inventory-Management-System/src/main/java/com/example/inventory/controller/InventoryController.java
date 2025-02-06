package com.example.inventory.controller;

import com.example.inventory.model.*;
import com.example.inventory.service.DeviceService;
import com.example.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService service;
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/shelf")
    private List<ShelfVO> getShelf(){
        return service.getShelf();
    }

    @GetMapping("/shelfPosition")
    private List<ShelfPositionVO> getShelfPosition(){
        return service.getShelfPosition();
    }

    @GetMapping("/shelf/{id}")
    public ShelfVO getShelfById(@PathVariable Long id) {
        return service.getShelfById(id);
    }

    @GetMapping("/shelfPosition/{id}")
    public ShelfPositionVO getShelfPositionById(@PathVariable Long id) {
        return service.getShelfPositionById(id);
    }

    @PostMapping("/shelf")
    private ShelfVO saveShelf(@RequestBody ShelfVO shelf){
        return service.saveShelf(shelf);
    }

    @PostMapping("/shelfPosition")
    private ShelfPositionVO saveShelfPosition(@RequestBody ShelfPositionVO shelfPos){
        return service.saveShelfPosition(shelfPos);
    }

    @PostMapping("/relationship/device/shelfPosition")
    private void addShelfPositionToDevice(@RequestBody DeviceToShelfPosition req){
        service.addShelfPositionToDevice(req.getDeviceId() , req.getShelfPositionId());
    }

    @PostMapping("/relationship/shelfPosition/shelf")
    private void addShelfToShelfPosition(@RequestBody ShelfPositionToShelf req){
        service.addShelfToShelfPosition(req.getShelfPositionId() , req.getShelfId());
    }

}
