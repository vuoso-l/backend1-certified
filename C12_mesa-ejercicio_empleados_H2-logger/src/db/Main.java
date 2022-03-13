package db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        Employed emp1 = new Employed(1, "Lucas", 34, "Google", LocalDate.of(2022, 05, 31));
        Employed emp2 = new Employed(2, "Rodrigo", 34, "Facebook", LocalDate.of(2022, 05, 31));
        Employed emp3 = new Employed(3, "Pablo", 34, "Digital", LocalDate.of(2022, 05, 31));
        emp2.setAge(30);
        SqlInstructions dbSentence = new SqlInstructions();

        Connection connection = null;
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();

            //Código para crear una tabla. Elimina la tabla si esta ya existe y la vuelve a crear
            try {
                String createSql = "DROP TABLE IF EXISTS BUISNESS;\n" + "CREATE TABLE BUISNESS(" +
                        "ID INT PRIMARY KEY," +
                        "NAME VARCHAR(20)," +
                        "AGE INT," +
                        "BUISNESS VARCHAR(20)," +
                        "STARTWORK DATE);\n";
                stmt.execute(createSql);

                //Código para insertar los empleados y registrar con logger
                String insertSql1 = dbSentence.insertEmployed(emp1);
                String insertSql2 = dbSentence.insertEmployed(emp2);
                String insertSql3 = dbSentence.insertEmployed(emp3);
                stmt.execute(insertSql1);
                stmt.execute(insertSql2);
                stmt.execute(insertSql3);

                //Código para actualizar datos y registrar la acción con logger
                String updateSql1 = dbSentence.udpateEmployed(emp2);
                stmt.execute(updateSql1);
                logger.debug("Se actualizaron datos de la edad del empleado " + emp2.getName());

                //Código para eliminar un empleado por id y registrar la acción con logger
                String deleteSql1 = dbSentence.deleteEmployedById(emp3);
                stmt.execute(deleteSql1);
                logger.info("Se eliminó el empleado " + emp3.getName());

                //Código para eliminar un empleado por name y registrar la acción con logger
                String deleteSql2 = dbSentence.deleteEmployedByName(emp2);
                stmt.execute(deleteSql2);
                logger.info("Se eliminó el empleado " + emp2.getName());

            } catch (Exception e) {
                logger.error(e);
            }

            //Codigo para consultar todos los registros de la tabla BUSINESS
            String sql = "select * from BUISNESS";
            ResultSet rd = stmt.executeQuery(sql);

            //Código para recorrer el resultado de la consulta
            while (rd.next()) {
                System.out.println(rd.getInt(1) + " " + rd.getString(2) + " " + rd.getString(3) + " " + rd.getString(4) + " " + rd.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:" +
                "~/test", "sa", "");

    }
}
