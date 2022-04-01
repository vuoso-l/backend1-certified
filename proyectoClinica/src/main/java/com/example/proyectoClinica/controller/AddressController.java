package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.repository.daos.impl.AddressDaoH2;
import com.example.proyectoClinica.domain.Address;
import com.example.proyectoClinica.servicies.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService = new AddressService(new AddressDaoH2());

    @PostMapping("/register")
    public ResponseEntity<Address> addressRegister(@RequestBody Address address) throws Exception {
        return ResponseEntity.ok(addressService.registerAddress(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddress(@PathVariable Long id) {
        ResponseEntity res = null;
        if (addressService.findOneById(id) == null){
            res = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            addressService.deleteAddress(id);
            res = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findAddress(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.findOneById(id));
    }

    @GetMapping()
    public List<Address> findAllAddresses() {
        return addressService.findAll();
    }

    @PutMapping()
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        ResponseEntity<Address> response = null;

        if (address.getId() != null && addressService.findOneById(address.getId()) != null)
            response = ResponseEntity.ok(addressService.updateAddress(address));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

}
