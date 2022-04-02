package com.example.proyectoClinica.repository.daos.impl;

import com.example.proyectoClinica.repository.daos.IDao;
import com.example.proyectoClinica.domain.Dentist;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.proyectoClinica.config.DbH2.getConnection;

public class DentistDaoH2 implements IDao<Dentist> {
    private static final Logger logger = Logger.getLogger(DentistDaoH2.class);

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    @Override
    public Dentist register(Dentist dentist) throws Exception {
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("INSERT INTO dentist (registrationNumber, firstName, lastName) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, dentist.getRegistrationNumber());
            preparedStatement.setString(2, dentist.getFirstName());
            preparedStatement.setString(3, dentist.getLastName());

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                dentist.setId(keys.getLong(1));

            logger.info("Se creó el odontólogo " + dentist.getId() + ". Sus datos son: * matrícula n° * " + dentist.getRegistrationNumber() + ", * nombre * " + dentist.getFirstName() + " y * apellido * " + dentist.getLastName());

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        }
        return dentist;
    }

    @Override
    public void delete(Long id) {
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("DELETE FROM dentist WHERE id = ?");
            preparedStatement.setLong(1, id);

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            logger.info("Se eliminó el odontólogo con id " + id + " satisfactoriamente");

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dentist findOneById(Long id) {
        Dentist dentist1 = null;
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("SELECT * FROM dentist WHERE id = ?");
            preparedStatement.setLong(1, id);

            //Execute a SQL sentence
            ResultSet result = preparedStatement.executeQuery();

            //Obtain results
            while (result.next()) {
                Long idDentist = result.getLong("id");
                int registrationNumber = result.getInt("registrationNumber");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                dentist1 = new Dentist(registrationNumber, firstName, lastName);
                dentist1.setId(idDentist);
                logger.info("Se realizó la búsqueda del odontólogo con id " + idDentist + " satisfactoriamente. Sus datos son -- nombre: " + firstName + " - apellido: " + lastName + " - matrícula n°: " + registrationNumber);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dentist1;
    }

    @Override
    public Dentist findOneByEmail(String email) {
        return null;
    }

    @Override
    public List<Dentist> findAll() {
        List<Dentist> dentists = new ArrayList<>();
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("SELECT * FROM dentist");

            //Execute a SQL sentence
            ResultSet result = preparedStatement.executeQuery();

            //Obtain results
            while (result.next()) {
                Long idDentist = result.getLong("id");
                int registrationNumber = result.getInt("registrationNumber");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                Dentist dentist1 = new Dentist(registrationNumber, firstName, lastName);
                dentist1.setId(idDentist);
                dentists.add(dentist1);
                logger.info("* Odontólogo * con id " + idDentist + "// nombre: " + firstName + " - apellido: " + lastName + " - matrícula n°: " + registrationNumber);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dentists;
    }

    @Override
    public Dentist update(Dentist dentist) {
        try {
            //Create sentence
            preparedStatement = connection.prepareStatement("UPDATE address SET registrationNumber = ?, firstName = ?, lastName = ? WHERE id = ?");
            preparedStatement.setLong(1, dentist.getRegistrationNumber());
            preparedStatement.setString(2, dentist.getFirstName());
            preparedStatement.setString(3, dentist.getLastName());
            preparedStatement.setLong(4, dentist.getId());

            //Execute a SQL sentence
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dentist;
    }
}
