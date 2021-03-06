package com.example.proyectoClinica.repository.daos.impl;

import com.example.proyectoClinica.repository.daos.IDao;
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
                address.setId(keys.getLong(1));

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
                Long idAddress = result.getLong("id");
                String street = result.getString("street");
                int number = result.getInt("number");
                String locality = result.getString("locality");
                String province = result.getString("province");
                address1 = new Address(street, locality, number, province);
                address1.setId(idAddress);
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
                Long idAddress = result.getLong("id");
                String street = result.getString("street");
                int number = result.getInt("number");
                String locality = result.getString("locality");
                String province = result.getString("province");
                Address address1 = new Address(street, locality, number, province);
                address1.setId(idAddress);
                addresses.add(address1);
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

    @Override
    public Address update(Address address) {
        try {
            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("UPDATE address SET street = ?, number = ?, locality = ?, province = ? WHERE id = ?");
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getNumber());
            preparedStatement.setString(3, address.getLocality());
            preparedStatement.setString(4, address.getProvince());
            preparedStatement.setLong(5, address.getId());

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }
}
