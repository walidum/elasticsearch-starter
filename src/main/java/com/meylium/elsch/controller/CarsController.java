package com.meylium.elsch.controller;

import com.meylium.elsch.model.Car;
import com.meylium.elsch.repo.elastic.CarRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "cars")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CarsController {
    private final CarRepo carRepo;

    public CarsController(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    @GetMapping(path = "all")
    public ResponseEntity<Iterable<Car>> all() {
        return ResponseEntity.ok(carRepo.findAll());
    }

    @DeleteMapping(path = "all")
    public ResponseEntity deleteAll() {
        this.carRepo.deleteAll();
        return ResponseEntity.ok("all cars deleted !");
    }
}
