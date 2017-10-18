package InterfaceFraficaAlgoritimo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class BaseDonnéesPays {

    private Connection conn;
    private Statement stmt;
    private ResultSet res;
    //pour enregistrer une array list avec les objets pays qu'on a
    private ArrayList<Pays> tabPays = new ArrayList<Pays>();
    //pour enregistrer tous le pays differents qu'on a
    private ArrayList<String> payslist=new ArrayList<String>();


    public ArrayList<Pays> getTabPays() {
        return tabPays;
    }

    public ArrayList<String> getPayslist() {
        return payslist;
    }

    public BaseDonnéesPays() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            //renseigner votre nom de login et votre mot de passe
            conn =DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql541992",
                         "sql541992", "cZ5*vP1%");
            Statement stmt = conn.createStatement();

            String sqlStr = ("select * from InfosPays");
            //System.out.println("test1");
            res = stmt.executeQuery(sqlStr);
            //System.out.println("test2");
            stmt = conn.createStatement();
            int i=0;
            while (res.next()) {
                String nom = res.getString(1);
                double latitude = res.getDouble(2);
                double longitude = res.getDouble(3);
                String description = res.getString(4);
                payslist.add(nom);
                tabPays.add(new Pays(nom,description,latitude,longitude));
                i++;
            }
            commit();
           }catch (SQLException e) {
               e.printStackTrace();
           }catch (Exception e) {
               e.printStackTrace();
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
