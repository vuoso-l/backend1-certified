package transactions;

public class SqlInstructions {
    public String insertPatient(Patients patient) {
        return "INSERT INTO PATIENT VALUES(" + patient.getId() + ",'" + patient.getName() + "','" + patient.getLastName() + "','" + patient.getAddress() + "'," + patient.getDni() + " ,'" + patient.getDischargeDate() + "','" + patient.getUsername() + "','" + patient.getAddress() + "');\n";
    }

    public String updatePatient(Patients patient) {
        return "UPDATE PATIENT SET = " + patient.getPassword() + " WHERE ID = " + patient.getId() + ";\n";
    }
}
