package InterfaceFraficaAlgoritimo;

public class Pays {
    private String nom;
    private String description;
    private double latitude;
    private double longitude;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Pays(String nom,String description,double latitude,double longitude) {
        this.description=description;
        this.latitude=latitude;
        this.longitude=longitude;
        this.nom=nom;
    }
}
