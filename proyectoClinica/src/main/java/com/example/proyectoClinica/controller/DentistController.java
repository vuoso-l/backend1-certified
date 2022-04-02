package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.repository.impl.DentistDaoH2;
import com.example.proyectoClinica.domain.Dentist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    @Autowired
    private DentistDaoH2 dentistRepository;

    @PostMapping("/register")
    public ResponseEntity<Dentist> dentistRegister(@RequestBody Dentist dentist) throws Exception {
        return ResponseEntity.ok(dentistRepository.register(dentist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDentist(@PathVariable Long id) {
        ResponseEntity res = null;
        if (dentistRepository.findOneById(id) == null){
            res = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            dentistRepository.delete(id);
            res = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> findDentist(@PathVariable Long id) {
        return ResponseEntity.ok(dentistRepository.findOneById(id));
    }

    @GetMapping()
    public List<Dentist> findAllDentists() {
        return dentistRepository.findAll();
    }

    @PutMapping()
    public ResponseEntity<Dentist> updateDentist(@RequestBody Dentist dentist) {
        ResponseEntity<Dentist> response = null;

        if (dentist.getId() != null && dentistRepository.findOneById(dentist.getId()) != null)
            response = ResponseEntity.ok(dentistRepository.update(dentist));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

}
