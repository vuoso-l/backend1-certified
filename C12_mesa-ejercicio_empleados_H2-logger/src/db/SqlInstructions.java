package db;

public class SqlInstructions {
    public String insertEmployed(Employed employed) {
        return "INSERT INTO BUISNESS VALUES(" + employed.getId() + ",'" + employed.getName() + "'," + employed.getAge() + ",'" + employed.getBusiness() + "','" + employed.getStartToWork() + "');\n";
    }

    public String udpateEmployed(Employed employed) {
        int ageEmployed = employed.getAge();
        int idEmployed = employed.getId();
        return "UPDATE BUISNESS SET AGE = " + ageEmployed + " WHERE ID = " + idEmployed + ";\n";
    }

    public String deleteEmployedById(Employed employed) {
        int idEmployed = employed.getId();
        return "DELETE FROM BUISNESS WHERE ID = " + idEmployed + ";\n";
    }

    public String deleteEmployedByName(Employed employed) {
        String nameEmployed = employed.getName();
        return "DELETE FROM BUISNESS WHERE NAME = " + nameEmployed + ";\n";
    }
}
