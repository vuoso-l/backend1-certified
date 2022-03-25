package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.domain.Address;
import com.example.proyectoClinica.servicies.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping()
    public Address addressRegister(Address address) throws Exception {
        return addressService.registerAddress(address);
    }

    @GetMapping("/{id}")
    public Address findAddress(Long id) {
        return addressService.findOneById(id);
    }

    @GetMapping()
    public List<Address> findAllAddresses() {
        return addressService.findAll();
    }
}
