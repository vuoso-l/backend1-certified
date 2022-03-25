package com.example.proyectoClinica.controller.daos.impl;

import com.example.proyectoClinica.controller.daos.IDao;
import com.example.proyectoClinica.domain.Address;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.proyectoClinica.config.DbH2.getConnection;

public class AddressDaoH2 implements IDao<Address> {
    private static final Logger logger = Logger.getLogger(PatientDaoH2.class);

    public AddressDaoH2() {
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    @Override
    public Address register(Address address) throws Exception {
        try {

            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("INSERT INTO address (street, number, locality, province) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getNumber());
            preparedStatement.setString(3, address.getLocality());
            preparedStatement.setString(4, address.getProvince());

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                address.setId(keys.getInt(1));

            logger.info("Se creó la dirección con id " + address.getId() + "----" + address.getStreet());

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return address;
    }

    @Override
    public void delete(Long id) {
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("DELETE FROM address WHERE id = ?");
            preparedStatement.setLong(1, id);

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            logger.info("Se eliminó el domicilio con id " + id + " satisfactoriamente");

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address findOneById(Long id) {
        Address address1 = null;
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("SELECT * FROM address WHERE id = ?");
            preparedStatement.setLong(1, id);

            //Execute a SQL sentence
            ResultSet result = preparedStatement.executeQuery();

            //Obtain results
            while (result.next()) {
                int idAddress = result.getInt("id");
                String street = result.getString("street");
                int number = result.getInt("number");
                String locality = result.getString("locality");
                String province = result.getString("province");
                address1 = new Address(street, locality, number, province);
                logger.info("Se realizó satisfactoriamente la búsqueda del domicilio con id " + idAddress);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address1;
    }

    @Override
    public Address findOneByEmail(String email) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<>();
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("SELECT * FROM address");

            //Execute a SQL sentence
            ResultSet result = preparedStatement.executeQuery();

            //Obtain results
            while (result.next()) {
                int idAddress = result.getInt("id");
                String street = result.getString("street");
                int number = result.getInt("number");
                String locality = result.getString("locality");
                String province = result.getString("province");
                addresses.add(new Address(street, locality, number, province));
                logger.info("* Dommicilio * id " + idAddress + ": calle " + street + ", número " + number + " - localidad " + locality + " - provincia " + province);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addresses;
    }
}
