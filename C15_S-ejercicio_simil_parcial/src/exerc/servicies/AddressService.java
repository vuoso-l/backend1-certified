package exerc.servicies;

import exerc.daos.IDao;
import exerc.models.Address;
import exerc.models.Patient;

import java.util.List;

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

    public Address findOne(Long id) {
        return addressDao.findOne(id);
    }

    public List<Address> findAll() {
        return addressDao.findAll();
    }
}
