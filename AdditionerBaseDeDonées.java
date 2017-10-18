package InterfaceFraficaAlgoritimo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
/**
 * classe responsable pour l'addition des echanges, pays et universit�es � la base de don�es
 */
public class AdditionerBaseDeDon�es {
    private Connection conn;
    private Statement stmt;
    private ResultSet res;
  
    public AdditionerBaseDeDon�es() {
    }
    /**
     *methode pour additioner un echange � la base de don�es
     * @param tab
     */
    public void addechange(ArrayList<Echanges> tab) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            //renseigner votre nom de login et votre mot de passe
            conn =DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql541992",
                         "sql541992", "cZ5*vP1%");
            Statement stmt = conn.createStatement();
            System.out.println(tab);
            for(int i=0;i<tab.size();i++){
            String pays=tab.get(i).getPays();
            String ville=tab.get(i).getVille();
            String langue=tab.get(i).getLangue();
            String universit�=tab.get(i).getUniversit�();
            String departement=tab.get(i).getDepartement();
            int dur�e=tab.get(i).getDur�e();
            double latitude=tab.get(i).getLatitude();
            double longitude= tab.get(i).getLongitude();

            String requete = ("INSERT INTO Echanges VALUES(?,?,?,?,?,?,?,?)");
            
            PreparedStatement dml_stmt=conn.prepareStatement(requete);
            dml_stmt.setInt(6,dur�e );
            dml_stmt.setString(1, pays);
            dml_stmt.setString(2, ville);
            dml_stmt.setString(3,langue);
            dml_stmt.setString(4,universit�);
            dml_stmt.setString(5,departement);
            dml_stmt.setDouble(7,latitude);
            dml_stmt.setDouble(8,longitude);
            dml_stmt.executeUpdate();
            }
                
            commit();
           }catch (SQLException e) {
               e.printStackTrace();
           }catch (Exception e) {
               e.printStackTrace();
           }
    
    }
    /**
     *methode pour aditioner un pays � la base de don�es
     * @param tab
     */
    public void addpays(ArrayList<Pays> tab) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            //renseigner votre nom de login et votre mot de passe
            conn =DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql541992",
                         "sql541992", "cZ5*vP1%");
            Statement stmt = conn.createStatement();
            System.out.println(tab);
            for(int i=0;i<tab.size();i++){
                String pays=tab.get(i).getNom();
                String description=tab.get(i).getDescription();
                double latitude=tab.get(i).getLatitude();
                double longitude=tab.get(i).getLongitude();

                String requete = ("INSERT INTO InfosPays VALUES(?,?,?,?)");
            
                PreparedStatement dml_stmt=conn.prepareStatement(requete);
                dml_stmt.setString(1, pays);
                dml_stmt.setDouble(2,latitude);
                dml_stmt.setDouble(3,longitude);
                dml_stmt.setString(4, description);
                dml_stmt.executeUpdate();
                }
                
            commit();
           }catch (SQLException e) {
               e.printStackTrace();
           }catch (Exception e) {
               e.printStackTrace();
           }
    
    }
    /**
     *methode responsable d'aditionner une universit�, pour cela on cr�e un Arraylist d'Universit� prenant les attributs(pays,ville,universit�, description et url)
     * que l'on ins�re terme � terme dans la base de donn�e en s�parant chaque attributs pour qu'il remplisse la colonne qui lui correspond dans la BDe
     * @param tab
     */
    public void adduniversit�(ArrayList<Universit�> tab) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            //renseigner votre nom de login et votre mot de passe
            conn =DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql541992",
                         "sql541992", "cZ5*vP1%");
            Statement stmt = conn.createStatement();
            System.out.println(tab);
            for(int i=0;i<tab.size();i++){
                String pays=tab.get(i).getPays();
                String ville=tab.get(i).getVille();
                String universite=tab.get(i).getUniversit�();
                String description=tab.get(i).getDescription();
                String urls=tab.get(i).getPhoto();

                String requete = ("INSERT INTO InfosUniversit�s VALUES(?,?,?,?,?)");
            
                PreparedStatement dml_stmt=conn.prepareStatement(requete);
                dml_stmt.setString(1, pays);
                dml_stmt.setString(2,ville);
                dml_stmt.setString(3,universite);
                dml_stmt.setString(4,description);
                dml_stmt.setString(5,urls);
                dml_stmt.executeUpdate();
                }
                
            commit();
           }catch (SQLException e) {
               e.printStackTrace();
           }catch (Exception e) {
               e.printStackTrace();
           }
    }
    /**
     *pour changer la description d'un universit�
     * @param nom
     * @param description
     */
    public void changerdecripitionuniversit�(String nom,String description){
        try
            {
              // create a java mysql database connection
                Class.forName("com.mysql.jdbc.Driver");
                
                //renseigner votre nom de login et votre mot de passe
                conn =DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql541992",
                             "sql541992", "cZ5*vP1%");
            Statement stmt = conn.createStatement();
                System.out.println(nom+"update");
                System.out.println(description+"update");
              // pour creer le sql statement
              String query = "UPDATE InfosUniversit�s SET Description=? WHERE Universit�=?";
              PreparedStatement preparedStmt = conn.prepareStatement(query);
              preparedStmt.setString(1,description);
              preparedStmt.setString(2, nom);
              preparedStmt.executeUpdate();
              
             
              
              commit();;
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
    }
    /**
     *pour changer la descripiton d'un pays
     * @param nom
     * @param description
     */
    public void changerdescriptionpays(String nom,String description){
        try
            {
              Class.forName("com.mysql.jdbc.Driver");
              
              //renseigner votre nom de login et votre mot de passe
              conn =DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql541992",
                           "sql541992", "cZ5*vP1%");
              Statement stmt = conn.createStatement();
            
              // create the java mysql update preparedstatement
              String query = "UPDATE InfosPays SET Description = ? WHERE Pays = ?";
              PreparedStatement preparedStmt = conn.prepareStatement(query);
              preparedStmt.setString(1,description);
              preparedStmt.setString(2, nom);

              // execute the java preparedstatement
              preparedStmt.executeUpdate();
              
                commit();
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
    }
    /**
     *Pour changer la url d'un pays
     * @param url
     * @param nom
     */
    public void changerurl(String url,String nom){
        try
            {
                Class.forName("com.mysql.jdbc.Driver");
                
                //renseigner votre nom de login et votre mot de passe
                conn =DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql541992",
                             "sql541992", "cZ5*vP1%");
                Statement stmt = conn.createStatement();
                // create the java mysql update preparedstatement
                String query = "UPDATE InfosUniversit�s SET LiensPhotos = ? WHERE Universit�= ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, url);
                preparedStmt.setString(2, nom);

                // execute the java preparedstatement
                preparedStmt.executeUpdate();
              
                commit();
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
    }
    public void commit() {
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println("erreur2");
            
        }
    }
}
