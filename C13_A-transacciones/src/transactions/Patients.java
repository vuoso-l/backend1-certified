package transactions;

public class Patients {
    private int id;
    private String name;
    private String lastName;
    private String address;
    private int dni;
    private String dischargeDate;
    private String username;
    private String password;

    public Patients(int id, String name, String lastName, String address, int dni, String dischargeDate, String user, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.dni = dni;
        this.dischargeDate = dischargeDate;
        this.username = user;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
