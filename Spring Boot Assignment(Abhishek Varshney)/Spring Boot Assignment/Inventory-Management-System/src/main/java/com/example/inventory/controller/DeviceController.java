package com.example.inventory.controller;

import com.example.inventory.model.Device;
import com.example.inventory.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device")
public class DeviceController {
    @Autowired
    private DeviceService inventoryService;


    @GetMapping
    private List<Device> getAllDevice(){
        return inventoryService.getDevice();
    }

    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable Long id) {
        return inventoryService.getDeviceById(id);
    }

    @PostMapping
    private Device saveDevice(@RequestBody Device device){
        return inventoryService.saveDevice(device);
    }

    @PatchMapping("/{id}")
    private Device updateDevice(@PathVariable Long id , @RequestBody Device device){
        return inventoryService.updateDevice(id , device);
    }

    @DeleteMapping("/{id}")
    private Device deleteDevice(@PathVariable Long id){
        return inventoryService.deleteDevice(id);
    }
}
