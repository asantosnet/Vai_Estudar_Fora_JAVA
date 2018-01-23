package InterfaceFraficaAlgoritimo;

public class Echanges {
  
  private String pays;
  private String ville;
  private String université;
  private int durée;
  private String langue;
  private String departement;
  private double latitude;
  private double longitude;
  
  
  public Echanges(String pays, String ville, String langue, String université, String departement, int durée, double latitude, double longitude) {
  this.pays = pays;
  this.ville = ville;
  this.université = université;
  this.durée = durée;
  this.langue = langue;
  this.departement = departement;
  this.latitude = latitude;
  this.longitude = longitude;
  
  }

  public String getPays() {
    return pays;
  }

  public String getVille() {
    return ville;
  }

  public String getUniversité() {
    return université;
  }

  public int getDurée() {
    return durée;
  }

  public String getLangue() {
    return langue;
  }

  public String getDepartement() {
    return departement;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }
  
  
}
