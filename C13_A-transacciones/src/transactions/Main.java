package transactions;

import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    private static final String SQL_CREATE_TABLE = "DROP TABLE IF EXISTS PATIENT;\n" + "CREATE TABLE PATIENT(" +
            "ID INT PRIMARY KEY," +
            "NAME VARCHAR(25)," +
            "LASTNAME VARCHAR(25)," +
            "ADDRESS VARCHAR(45)," +
            "DNI INT," +
            "DISCHARGEDATE VARCHAR(15)," +
            "USERNAME VARCHAR(25)," +
            "PASSWORD VARCHAR(60));\n";

    private static final String SQL_INSERT1 =  "INSERT INTO PATIENT (ID, NAME, LASTNAME, ADDRESS, DNI, DISCHARGEDATE, USERNAME, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE1 =  "UPDATE PATIENT SET PASSWORD = ? WHERE ID = ?";

    public static void main(String[] args) throws Exception {

        Patients pat1 = new Patients(1, "Lucas", "Vuoso", "sasasasas", 33489714, "01, 03, 2021", "admin", "root");

        Connection connection = null;
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();

            //Código para crear una tabla. Elimina la tabla si esta ya existe y la vuelve a crear
            stmt.execute(SQL_CREATE_TABLE);

                //Código para insertar los pacientes desde transacciones y registrar con logger
                PreparedStatement psInsert1 = connection.prepareStatement(SQL_INSERT1);
                psInsert1.setInt(1, pat1.getId());
                psInsert1.setString(2, pat1.getName());
                psInsert1.setString(3, pat1.getLastName());
                psInsert1.setString(4, pat1.getAddress());
                psInsert1.setInt(5, pat1.getDni());
                psInsert1.setString(6, pat1.getDischargeDate());
                psInsert1.setString(7, pat1.getUsername());
                psInsert1.setString(8, pat1.getPassword());
                psInsert1.execute();
                logger.info("Se creó el paciente " + pat1.getName() + " " + pat1.getLastName() + " satisfactoriamente");

                //Empieza la transacción
                connection.setAutoCommit(false);

                //Código para modificar paciente desde transacciones y registrar con logger
                PreparedStatement psUpdate1 = connection.prepareStatement(SQL_UPDATE1);
                psUpdate1.setString(1, "root123456789");
                psUpdate1.setInt(2, pat1.getId());
                psUpdate1.execute();
                logger.info("Se modificó la contraseña del paciente " + pat1.getName() + " " + pat1.getLastName() + " satisfactoriamente");

                //forzar error para probar el rollback
                int a = 4 / 0;

                connection.commit();
                connection.setAutoCommit(true);


            //Codigo para consultar todos los registros de la tabla PATIENT
            String sql = "select * from PATIENT";
            //Statement statem = connection.createStatement();
            ResultSet rd = stmt.executeQuery(sql);

            //Código para recorrer el resultado de la consulta
            while (rd.next()) {
                System.out.println(rd.getInt(1) + " " + rd.getString(2) + " " + rd.getString(3) + " " +
                        rd.getString(4) + rd.getInt(5) + " " + rd.getString(6) + " " +
                        rd.getString(7) + " " + rd.getString(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            logger.error(e);
        } finally {
            connection.close();
        }

        Connection connection2 = getConnection();
        String sql1 = "select * from PATIENT";
        Statement statem = connection2.createStatement();
        ResultSet rd1 = statem.executeQuery(sql1);

        //Código para recorrer el resultado de la consulta
        while (rd1.next()) {
            System.out.println(rd1.getInt(1) + " " + rd1.getString(2) + " " + rd1.getString(3) + " " +
                    rd1.getString(4) + rd1.getInt(5) + " " + rd1.getString(6) + " " +
                    rd1.getString(7) + " " + rd1.getString(8));
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:" +
                "~/C13-A", "sa", "");

    }
}
