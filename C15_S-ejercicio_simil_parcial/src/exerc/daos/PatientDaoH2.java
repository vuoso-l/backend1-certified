package exerc.daos;

import exerc.models.Address;
import exerc.models.Patient;
import exerc.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static exerc.config.DbH2.getConnection;

public class PatientDaoH2 implements IDao<Patient> {
    private static final Logger logger = Logger.getLogger(PatientDaoH2.class);
    private AddressDaoH2 addressDaoH2 = new AddressDaoH2();

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
            patient.getAddress().setId(address.getId());

            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("INSERT INTO patient (lastname, firstname, dni, admissionDate, id_address) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, patient.getLastName());
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setLong(3, patient.getDni());
            preparedStatement.setDate(4, Util.utilDateToSqlDate(patient.getAdmissionDate()));
            preparedStatement.setLong(5, patient.getAddress().getId());

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
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
    public Patient findOne(Long id) {
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
                int dni = result.getInt("dni");
                Date admissionDate = result.getDate("admissionDate");
                Long idAddress = result.getLong("id_address");
                Address address = addressDaoH2.findOne(idAddress);
                patient1 = new Patient(lastname, firstname, dni, admissionDate, address);
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
    public List findAll() {
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
                int dni = result.getInt("dni");
                Date admissionDate = result.getDate("admissionDate");
                Long idAddress = result.getLong("id_address");
                Address address = addressDaoH2.findOne(idAddress);
                patients.add(new Patient(lastname, firstname, dni, admissionDate, address));
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
}
