package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.controller.daos.impl.AddressDaoH2;
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


    /*@GetMapping("/addressIndex")
    public String welcome(Model model){
        addressService.setAddressDao(new AddressDaoH2());
        model.addAttribute("street",addressService.findOneById(1L).getStreet());
        return "addressIndex";
    }*/

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

    @GetMapping("/all")
    public List<Address> findAllAddresses() {
        return addressService.findAll();
    }

}
