package parcial.daos;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import parcial.models.Odontologo;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static parcial.config.DbH2.getConnection;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    @Override
    public Odontologo registrar(Odontologo odontologo) throws Exception {
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("INSERT INTO odontologo (numero_matricula, nombre, apellido) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                odontologo.setId(keys.getLong(1));

            logger.info("Se creó el odontólogo " + odontologo.getId() + ". Sus datos son: * matrícula n° * " + odontologo.getNumeroMatricula() + ", * nombre * " + odontologo.getNombre() + " y * apellido * " + odontologo.getApellido());

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        }
        return odontologo;
    }

    @Override
    public void eliminar(Long id) {
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("DELETE FROM odontologo WHERE id = ?");
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
    public Odontologo listarUno(Long id) {
        Odontologo odontologo1 = null;
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("SELECT * FROM odontologo WHERE id = ?");
            preparedStatement.setLong(1, id);

            //Execute a SQL sentence
            ResultSet result = preparedStatement.executeQuery();

            //Obtain results
            while (result.next()) {
                Long idOdontologo = result.getLong("id");
                int matricula = result.getInt("numero_matricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                odontologo1 = new Odontologo(matricula, nombre, apellido);
                logger.info("Se realizó la búsqueda del odontólogo con id " + idOdontologo + " satisfactoriamente. Sus datos son -- nombre: " + nombre + " - apellido: " + apellido + " - matrícula n°: " + matricula);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return odontologo1;
    }

    @Override
    public List listarTodos() {
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("SELECT * FROM odontologo");

            //Execute a SQL sentence
            ResultSet result = preparedStatement.executeQuery();

            //Obtain results
            while (result.next()) {
                Long id = result.getLong("id");
                int matricula = result.getInt("numero_matricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                odontologos.add(new Odontologo(matricula, nombre, apellido));
                logger.info("* Odontólogo * con id " + id + "// nombre: " + nombre + " - apellido: " + apellido + " - matrícula n°: " + matricula);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return odontologos;
    }
}
