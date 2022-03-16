package maven.entities;

import java.time.LocalDate;

public class Airplane {
    private Long id;
    private String brand;
    private String model;
    private String license;
    private String startService;

    public Airplane(Long id, String brand, String model, String license, String startService) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.license = license;
        this.startService = startService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getStartService() {
        return startService;
    }

    public void setStartService(String startService) {
        this.startService = startService;
    }
}
