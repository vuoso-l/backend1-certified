package com.example.proyectoClinica.repository.daos.impl;

import com.example.proyectoClinica.repository.daos.IDao;
import com.example.proyectoClinica.domain.Dentist;
import com.example.proyectoClinica.domain.DentistShift;
import com.example.proyectoClinica.domain.Patient;
import com.example.proyectoClinica.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.proyectoClinica.config.DbH2.getConnection;

public class DentistShiftDaoH2 implements IDao<DentistShift> {
    private static final Logger logger = Logger.getLogger(DentistShiftDaoH2.class);
    private final PatientDaoH2 patientDaoH2 = new PatientDaoH2();
    private final DentistDaoH2 dentistDaoH2 = new DentistDaoH2();

    public DentistShiftDaoH2() {
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    @Override
    public DentistShift register(DentistShift dentistShift) throws Exception {
        try {

            //Como primer paso primero debemos guardar el domicilio del paciente
            //ya que necesitamos el ID del domicilio que se generará en la base de datos para luego
            //insertar ese id en el campo domicilio_id de la tabla pacientes
            Patient patient = patientDaoH2.register(dentistShift.getPatient());
            Dentist dentist = dentistDaoH2.register(dentistShift.getDentist());
            dentistShift.getPatient().setId(patient.getId());
            dentistShift.getDentist().setId(dentist.getId());

            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("INSERT INTO dentistShift (date, id_patient, id_dentist) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, Util.utilDateToSqlDate(dentistShift.getDate()));
            preparedStatement.setLong(2, dentistShift.getPatient().getId());
            preparedStatement.setLong(3, dentistShift.getDentist().getId());

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                dentistShift.setId(keys.getLong(1));

            logger.info("Se creó el turno " + dentistShift.getId() + "----" + dentistShift.getDate());

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        }
        return dentistShift;
    }

    @Override
    public void delete(Long id) {
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("DELETE FROM dentistShift WHERE id = ?");
            preparedStatement.setLong(1, id);

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            logger.info("Se eliminó el turno con id " + id + " satisfactoriamente");

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public DentistShift findOneById(Long id) {
        DentistShift dentistShift1 = null;
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("SELECT * FROM dentistShift WHERE id = ?");
            preparedStatement.setLong(1, id);

            //Execute a SQL sentence
            ResultSet result = preparedStatement.executeQuery();

            //Obtain results
            while (result.next()) {
                int idDentistShift = result.getInt("id");
                Date date = result.getDate("date");
                Long idPatient = result.getLong("patient");
                Long idDentist = result.getLong("id_dentist");
                Patient patient = patientDaoH2.findOneById(idPatient);
                Dentist dentist = dentistDaoH2.findOneById(idDentist);
                dentistShift1 = new DentistShift(date, patient, dentist);
                logger.info("Se realizó satisfactoriamente la búsqueda del turno con id " + idDentistShift);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dentistShift1;
    }

    @Override
    public DentistShift findOneByEmail(String email) {
        return null;
    }

    @Override
    public List<DentistShift> findAll() {
        List<DentistShift> dentistShifts = new ArrayList<>();
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("SELECT * FROM patient");

            //Execute a SQL sentence
            ResultSet result = preparedStatement.executeQuery();

            //Obtain results
            while (result.next()) {
                int idDentistShift = result.getInt("id");
                Date date = result.getDate("date");
                Long idPatient = result.getLong("patient");
                Long idDentist = result.getLong("id_dentist");
                Patient patient = patientDaoH2.findOneById(idPatient);
                Dentist dentist = dentistDaoH2.findOneById(idDentist);
                dentistShifts.add(new DentistShift(date, patient, dentist));
                logger.info("* Turno * id " + idDentistShift + ": paciente " + patient + ", odontólogo " + dentist);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dentistShifts;
    }

    @Override
    public DentistShift update(DentistShift dentistShift) {
        try {
            //Como primer paso actualizamos el domicilio del paciente
            Patient patient1 = patientDaoH2.update(dentistShift.getPatient());
            Dentist dentist1 = dentistDaoH2.update(dentistShift.getDentist());

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("UPDATE patient SET date = ?, id_patient = ?, id_dentist = ? WHERE id = ?");
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            preparedStatement.setDate(1, Util.utilDateToSqlDate(dentistShift.getDate()));
            preparedStatement.setLong(2, dentistShift.getPatient().getId());
            preparedStatement.setLong(3, dentistShift.getDentist().getId());
            preparedStatement.setLong(8, dentistShift.getId());

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dentistShift;
    }
}
