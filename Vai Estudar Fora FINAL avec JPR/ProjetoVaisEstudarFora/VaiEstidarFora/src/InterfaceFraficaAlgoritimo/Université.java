package InterfaceFraficaAlgoritimo;

public class Universit� {
  private String pays;
    private String ville;
    private String universit�;
    private String description;
    private String photo;


    /**D�finit les param�tres d'une Universit�.
     * @param pays
     * @param ville
     * @param universit�
     * @param description
     * @param photo
     */
    public Universit�(String pays, String ville, String universit�, String description, String photo) {
    this.pays = pays;
    this.ville = ville;
    this.universit� = universit�;
    this.description = description;
    this.photo = photo;
    
    
    }
    
    public String toString(){
      String s = " "+universit�;
      System.out.println(s);
      return s;
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


    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }
}
