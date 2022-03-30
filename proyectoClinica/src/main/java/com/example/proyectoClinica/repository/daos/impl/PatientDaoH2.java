package com.example.proyectoClinica.repository.daos.impl;

import com.example.proyectoClinica.repository.daos.IDao;
import com.example.proyectoClinica.domain.Address;
import com.example.proyectoClinica.domain.Dentist;
import com.example.proyectoClinica.domain.Patient;
import com.example.proyectoClinica.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.proyectoClinica.config.DbH2.getConnection;

public class PatientDaoH2 implements IDao<Patient> {
    private static final Logger logger = Logger.getLogger(PatientDaoH2.class);
    private final AddressDaoH2 addressDaoH2 = new AddressDaoH2();
    private final DentistDaoH2 dentistDaoH2 = new DentistDaoH2();

    public PatientDaoH2() {
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    @Override
    public Patient register(Patient patient) throws Exception {
        try {

            //Como primer paso primero debemos guardar el domicilio del paciente
            //ya que necesitamos el ID del domicilio que se generará en la base de datos para luego
            //insertar ese id en el campo domicilio_id de la tabla pacientes
            Address address = addressDaoH2.register(patient.getAddress());
            Dentist dentist = dentistDaoH2.register(patient.getDentist());
            patient.getAddress().setId(address.getId());
            patient.getDentist().setId(dentist.getId());

            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("INSERT INTO patient (lastname, firstname, email, dni, admissionDate, id_address, id_dentist) VALUES(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, patient.getLastName());
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setString(3, patient.getEmail());
            preparedStatement.setLong(4, patient.getDni());
            preparedStatement.setDate(5, Util.utilDateToSqlDate(patient.getAdmissionDate()));
            preparedStatement.setLong(6, patient.getAddress().getId());
            preparedStatement.setLong(7, patient.getDentist().getId());

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                patient.setId(keys.getLong(1));

            logger.info("Se creó el paciente " + patient.getId() + "----" + patient.getLastName() + ", " + patient.getFirstName());

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        }
        return patient;
    }

    @Override
    public void delete(Long id) {
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("DELETE FROM patient WHERE id = ?");
            preparedStatement.setLong(1, id);

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            logger.info("Se eliminó el paciente con id " + id + " satisfactoriamente");

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient findOneById(Long id) {
        Patient patient1 = null;
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("SELECT * FROM patient WHERE id = ?");
            preparedStatement.setLong(1, id);

            //Execute a SQL sentence
            ResultSet result = preparedStatement.executeQuery();

            //Obtain results
            while (result.next()) {
                Long idPatient = result.getLong("id");
                String lastname = result.getString("lastname");
                String firstname = result.getString("firstname");
                String email = result.getString("email");
                int dni = result.getInt("dni");
                Date admissionDate = result.getDate("admissionDate");
                Long idAddress = result.getLong("id_address");
                Long idDentist = result.getLong("id_dentist");
                Address address = addressDaoH2.findOneById(idAddress);
                Dentist dentist = dentistDaoH2.findOneById(idDentist);
                patient1 = new Patient(lastname, firstname, email, dni, admissionDate, address, dentist);
                logger.info("Se realizó satisfactoriamente la búsqueda del paciente con id " + idPatient + ": apellido " + lastname + ", nombre " + firstname + " - dni " + dni);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient1;
    }

    @Override
    public Patient findOneByEmail(String emailPatient) {
        Patient patient1 = null;
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("SELECT * FROM patient WHERE email = ?");
            preparedStatement.setString(1, emailPatient);

            //Execute a SQL sentence
            ResultSet result = preparedStatement.executeQuery();

            //Obtain results
            while (result.next()) {
                Long idPatient = result.getLong("id");
                String lastname = result.getString("lastname");
                String firstname = result.getString("firstname");
                String email = result.getString("email");
                int dni = result.getInt("dni");
                Date admissionDate = result.getDate("admissionDate");
                Long idAddress = result.getLong("id_address");
                Long idDentist = result.getLong("id_dentist");
                Address address = addressDaoH2.findOneById(idAddress);
                Dentist dentist = dentistDaoH2.findOneById(idDentist);
                patient1 = new Patient(lastname, firstname, email, dni, admissionDate, address, dentist);
                logger.info("Se realizó satisfactoriamente la búsqueda del paciente con id " + idPatient + ": apellido " + lastname + ", nombre " + firstname + " - dni " + dni);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient1;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("SELECT * FROM patient");

            //Execute a SQL sentence
            ResultSet result = preparedStatement.executeQuery();

            //Obtain results
            while (result.next()) {
                Long idPatient = result.getLong("id");
                String lastname = result.getString("lastname");
                String firstname = result.getString("firstname");
                String email = result.getString("email");
                int dni = result.getInt("dni");
                Date admissionDate = result.getDate("admissionDate");
                Long idAddress = result.getLong("id_address");
                Long idDentist = result.getLong("id_dentist");
                Address address = addressDaoH2.findOneById(idAddress);
                Dentist dentist = dentistDaoH2.findOneById(idDentist);
                patients.add(new Patient(lastname, firstname, email, dni, admissionDate, address, dentist));
                logger.info("* Paciente * id " + idPatient + ": apellido " + lastname + ", nombre " + firstname + " - dni " + dni);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public Patient update(Patient patient) {
        try {
            //Como primer paso actualizamos el domicilio del paciente
            Address address1 = addressDaoH2.update(patient.getAddress());
            Dentist dentist1 = dentistDaoH2.update(patient.getDentist());

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("UPDATE patient SET lastname = ?, firstname = ?, email = ?, dni = ?, admissionDate = ?, id_address = ?, id_dentist = ?  WHERE id = ?");
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            preparedStatement.setString(1, patient.getLastName());
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setString(3, patient.getEmail());
            preparedStatement.setInt(4, patient.getDni());
            preparedStatement.setDate(5, Util.utilDateToSqlDate(patient.getAdmissionDate()));
            preparedStatement.setLong(6, patient.getAddress().getId());
            preparedStatement.setLong(7, patient.getDentist().getId());
            preparedStatement.setLong(8, patient.getId());

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient;
    }
}