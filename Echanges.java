package InterfaceFraficaAlgoritimo;

public class Echanges {
  
  private String pays;
  private String ville;
  private String universit�;
  private int dur�e;
  private String langue;
  private String departement;
  private double latitude;
  private double longitude;
  
  
  public Echanges(String pays, String ville, String langue, String universit�, String departement, int dur�e, double latitude, double longitude) {
  this.pays = pays;
  this.ville = ville;
  this.universit� = universit�;
  this.dur�e = dur�e;
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

  public String getUniversit�() {
    return universit�;
  }

  public int getDur�e() {
    return dur�e;
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
