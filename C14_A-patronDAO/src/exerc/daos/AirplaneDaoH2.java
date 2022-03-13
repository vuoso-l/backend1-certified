package exerc.daos;

import exerc.entities.Airplane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static exerc.config.DbH2.getConnection;

public class AirplaneDaoH2 implements IDao<Airplane> {

    public AirplaneDaoH2() {
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    @Override
    public Airplane register(Airplane airplane) throws Exception {
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("INSERT INTO airplane VALUES(?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, airplane.getId());
            preparedStatement.setString(2, airplane.getBrand());
            preparedStatement.setString(3, airplane.getModel());
            preparedStatement.setString(4, airplane.getLicense());
            preparedStatement.setString(5, airplane.getStartService());

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airplane;
    }

    @Override
    public void delete(Long id) {
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("DELETE FROM airplane WHERE id = ?");
            preparedStatement.setLong(1, id);

            //Execute a SQL sentence
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Airplane findOne(Long id) {
        Airplane airplane1 = null;
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("SELECT * FROM airplane WHERE id = ?");
            preparedStatement.setLong(1, id);

            //Execute a SQL sentence
            ResultSet result = preparedStatement.executeQuery();

            //Obtain results
            while (result.next()) {
                Long idAirplane = result.getLong("id");
                String brand = result.getString("brand");
                String model = result.getString("model");
                String license = result.getString("license");
                String startService = result.getString("startService");
                airplane1 = new Airplane(idAirplane, brand, model, license, startService);
            }

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return airplane1;
    }

    @Override
    public List findAll() {
        List<Airplane> airplanes = new ArrayList<>();
        try {
            //Conexion to the DB
            connection = getConnection();

            //Create sentence
            preparedStatement = connection.prepareStatement("SELECT * FROM airplane");

            //Execute a SQL sentence
            ResultSet result = preparedStatement.executeQuery();

            //Obtain results
            while (result.next()) {
                Long idAirplane = result.getLong("id");
                String brand = result.getString("brand");
                String model = result.getString("model");
                String license = result.getString("license");
                String startService = result.getString("startService");
                airplanes.add(new Airplane(idAirplane, brand, model, license, startService));
            }

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return airplanes;
    }
}
