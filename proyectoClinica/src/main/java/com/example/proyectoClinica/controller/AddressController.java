package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.repository.impl.AddressDaoH2;
import com.example.proyectoClinica.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressDaoH2 addressRepository;

    @PostMapping("/register")
    public ResponseEntity<Address> addressRegister(@RequestBody Address address) throws Exception {
        return ResponseEntity.ok(addressRepository.register(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddress(@PathVariable Long id) {
        ResponseEntity res = null;
        if (addressRepository.findOneById(id) == null){
            res = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            addressRepository.delete(id);
            res = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findAddress(@PathVariable Long id) {
        return ResponseEntity.ok(addressRepository.findOneById(id));
    }

    @GetMapping()
    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    @PutMapping()
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        ResponseEntity<Address> response = null;

        if (address.getId() != null && addressRepository.findOneById(address.getId()) != null)
            response = ResponseEntity.ok(addressRepository.update(address));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

}
