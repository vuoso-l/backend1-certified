package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.repository.daos.impl.DentistDaoH2;
import com.example.proyectoClinica.domain.Dentist;
import com.example.proyectoClinica.servicies.DentistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentist")
public class DentistController {
    private DentistService dentistService = new DentistService(new DentistDaoH2());

    @PostMapping("/register")
    public ResponseEntity<Dentist> dentistRegister(@RequestBody Dentist dentist) throws Exception {
        return ResponseEntity.ok(dentistService.registerDentist(dentist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDentist(@PathVariable Long id) {
        ResponseEntity res = null;
        if (dentistService.findOneById(id) == null){
            res = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            dentistService.deleteDentist(id);
            res = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> findDentist(@PathVariable Long id) {
        return ResponseEntity.ok(dentistService.findOneById(id));
    }

    @GetMapping()
    public List<Dentist> findAllDentists() {
        return dentistService.findAll();
    }

    @PutMapping()
    public ResponseEntity<Dentist> updateDentist(@RequestBody Dentist dentist) {
        ResponseEntity<Dentist> response = null;

        if (dentist.getId() != null && dentistService.findOneById(dentist.getId()) != null)
            response = ResponseEntity.ok(dentistService.updateDentist(dentist));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

}
