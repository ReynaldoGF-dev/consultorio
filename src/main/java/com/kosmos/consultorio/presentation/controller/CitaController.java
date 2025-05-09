package com.kosmos.consultorio.presentation.controller;

import com.kosmos.consultorio.presentation.dto.CitaDTO;
import com.kosmos.consultorio.presentation.dto.CitaDataDTO;
import com.kosmos.consultorio.service.interfaces.ICitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cita")
public class CitaController {
    @Autowired
    private ICitaService iCitaService;

    @GetMapping("/find")
    public ResponseEntity<List<CitaDataDTO>> findAll() {
        return new ResponseEntity<>(this.iCitaService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<CitaDataDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.iCitaService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CitaDTO> createCita(@RequestBody CitaDTO citaDTO) {
        return new ResponseEntity<>(this.iCitaService.saveCita(citaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CitaDTO> updateUser(@RequestBody CitaDTO userDTO, @PathVariable Long id) {
        return new ResponseEntity<>(this.iCitaService.updateCita(userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(this.iCitaService.deleteCita(id), HttpStatus.OK);
    }

}
