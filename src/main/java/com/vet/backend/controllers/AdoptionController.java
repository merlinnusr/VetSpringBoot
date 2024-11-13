package com.vet.backend.controllers;

import com.vet.backend.dtos.AdoptionDto;
import com.vet.backend.models.Adoption;
import com.vet.backend.models.Pet;
import com.vet.backend.services.IAdoptionService;
import com.vet.backend.services.imp.AdoptionService;
import com.vet.backend.services.imp.PetService;
import com.vet.backend.services.imp.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/adopciones")
@Tag(name = "Crud de manejo de adopciones", description = "Manejo de adopciones")
public class AdoptionController {
    @Autowired
    private IAdoptionService adoptionService;
    @PostMapping("/crear")
    public Adoption create(@Valid @RequestBody AdoptionDto adoptionDto){
        return this.adoptionService.store(adoptionDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adoption> getAdoptionById(@PathVariable("id") Long id){
        Adoption adoption = this.adoptionService.findAdoptionById(id);
        return ResponseEntity.ok(adoption);
    }

    @GetMapping("/traer/todos")
    public List<Adoption> getAllAdoptions(){
        return this.adoptionService.getAdoptions();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteAdoptionById(@PathVariable("id") Long id){
        this.adoptionService.deleteAdoption(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editAdoption(@PathVariable("id") Long id,
                                          @RequestBody AdoptionDto adoptionDto){
        Adoption adoption = this.adoptionService.updateAdoption(id, adoptionDto);
        return ResponseEntity.ok(adoption);
    }


}
