package com.example.proyectoClinica.repository.daos.impl;

import com.example.proyectoClinica.repository.daos.IDao;
import com.example.proyectoClinica.domain.Address;
import com.example.proyectoClinica.domain.Patient;
import com.example.proyectoClinica.servicies.PatientService;
import com.example.proyectoClinica.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.proyectoClinica.config.DbH2.getConnection;

public class PatientDaoH2 implements IDao<Patient> {
    private static final Logger logger = Logger.getLogger(PatientDaoH2.class);
    private final AddressDaoH2 addressDaoH2 = new AddressDaoH2();

    public PatientDaoH2() {
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    @Override
    public Patient register(Patient patient) throws Exception {
        try {

            //Como primer paso primero debemos guardar el domicilio del paciente
            //ya que necesitamos el ID del domicilio que se generarĂ¡ en la base de datos para luego
            //insertar ese id en el campo domicilio_id de la tabla pacientes
            Address address = addressDaoH2.register(patient.getAddress());
            patient.getAddress().setId(address.getId());

            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("INSERT INTO patient (lastname, firstname, email, dni, admissionDate, id_address) VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, patient.getLastName());
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setString(3, patient.getEmail());
            preparedStatement.setLong(4, patient.getDni());
            preparedStatement.setDate(5, Util.utilDateToSqlDate(patient.getAdmissionDate()));
            preparedStatement.setLong(6, patient.getAddress().getId());

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                patient.setId(keys.getLong(1));

            logger.info("Se creĂ³ el paciente " + patient.getId() + "----" + patient.getLastName() + ", " + patient.getFirstName());

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        }
        return patient;
    }

    /*PacienteService pacienteService = new PacienteService(new PacienteDAOH2());
    paciente = pacienteService.buscarId(id);
    idDomicilio = paciente.getDomicilio().getId();


    //creo el servicio de domicilio para borrar el domicilio asociado antes de proceder a borrar al paciente.
    DomicilioService domicilioService = new DomicilioService(new DomicilioDAOH2());
            domicilioService.eliminarDomicilio(idDomicilio);*/





    @Override
    public void delete(Long id) {
        PatientDaoH2 patientDaoH2 = new PatientDaoH2();
        //PatientService patientService = new PatientService(new PatientDaoH2());
        try {
            //Conexion to the DB
            connection = getConnection();

            Patient patient = patientDaoH2.findOneById(id);
            addressDaoH2.delete(patient.getAddress().getId());

            //Create sentence
            preparedStatement = connection.prepareStatement("DELETE FROM patient WHERE id = ?");
            preparedStatement.setLong(1, id);

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            logger.info("Se eliminĂ³ el paciente con id " + id + " satisfactoriamente");

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
                Address address = addressDaoH2.findOneById(idAddress);
                patient1 = new Patient(lastname, firstname, email, dni, admissionDate, address);
                patient1.setId(idPatient);
                logger.info("Se realizĂ³ satisfactoriamente la bĂºsqueda del paciente con id " + idPatient + ": apellido " + lastname + ", nombre " + firstname + " - dni " + dni);
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
                Address address = addressDaoH2.findOneById(idAddress);
                patient1 = new Patient(lastname, firstname, email, dni, admissionDate, address);
                patient1.setId(idPatient);
                logger.info("Se realizĂ³ satisfactoriamente la bĂºsqueda del paciente con id " + idPatient + ": apellido " + lastname + ", nombre " + firstname + " - dni " + dni);
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
                Address address = addressDaoH2.findOneById(idAddress);
                Patient patient1 = new Patient(lastname, firstname, email, dni, admissionDate, address);
                patient1.setId(idPatient);
                patients.add(patient1);
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

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("UPDATE patient SET lastname = ?, firstname = ?, email = ?, dni = ?, admissionDate = ?, id_address = ? WHERE id = ?");
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            preparedStatement.setString(1, patient.getLastName());
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setString(3, patient.getEmail());
            preparedStatement.setInt(4, patient.getDni());
            preparedStatement.setDate(5, Util.utilDateToSqlDate(patient.getAdmissionDate()));
            preparedStatement.setLong(6, patient.getAddress().getId());
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