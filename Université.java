package InterfaceFraficaAlgoritimo;

public class Université {
  private String pays;
    private String ville;
    private String université;
    private String description;
    private String photo;


    /**Définit les paramètres d'une Université.
     * @param pays
     * @param ville
     * @param université
     * @param description
     * @param photo
     */
    public Université(String pays, String ville, String université, String description, String photo) {
    this.pays = pays;
    this.ville = ville;
    this.université = université;
    this.description = description;
    this.photo = photo;
    
    
    }
    
    public String toString(){
      String s = " "+université;
      System.out.println(s);
      return s;
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


    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }
}
