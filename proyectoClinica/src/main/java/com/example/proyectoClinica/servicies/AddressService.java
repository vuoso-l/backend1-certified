package com.example.proyectoClinica.servicies;

import com.example.proyectoClinica.controller.daos.IDao;
import com.example.proyectoClinica.domain.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private IDao<Address> addressDao;

    public AddressService() {

    }

    public AddressService(IDao<Address> addressDao) {
        this.addressDao = addressDao;
    }

    public void setAddressDao(IDao<Address> addressDao) {
        this.addressDao = addressDao;
    }

    public Address registerAddress(Address address) throws Exception {
        addressDao.register(address);
        return address;
    }

    public void deleteAddress(Long id) {
        addressDao.delete(id);
    }

    public Address findOneById(Long id) {
        return addressDao.findOneById(id);
    }

    public List<Address> findAll() {
        return addressDao.findAll();
    }
}
