package InterfaceFraficaAlgoritimo;

/*
 * Ici on prend des �changes enregistr�es dans la base de donn�es pour renseigner l'application, on obtient un Arraylist d'�change � partir d'une ligne de la base
 * de donn�.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class BaseDonn�esEchanges {

    private Connection conn;
    private Statement stmt;
    private ResultSet res;
    private ArrayList<Echanges> tabEch = new ArrayList<Echanges>();
    private ArrayList<String> languelist=new ArrayList<String>();
    private ArrayList<String> languelistrie=new ArrayList<String>();
    private ArrayList<String> dur�elist=new ArrayList<String>();
    private ArrayList<String> dur�elistrie=new ArrayList<String>();
    
    public ArrayList<Echanges> getTabEch() {
        return tabEch;
    }

    public ArrayList<String> getLanguelistrie() {
        return languelistrie;
    }

    public ArrayList<String> getDur�elistrie() {
        return dur�elistrie;
    }

    public BaseDonn�esEchanges() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            //renseigner votre nom de login et votre mot de passe
            conn =DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql541992",
                         "sql541992", "cZ5*vP1%");
            Statement stmt = conn.createStatement();

            String sqlStr = ("select * from Echanges");
            //System.out.println("test1");
            res = stmt.executeQuery(sqlStr);
            //System.out.println("test2");
            stmt = conn.createStatement();

            while (res.next()) {
                String pays = res.getString(1);
                String ville = res.getString(2);
                String langue = res.getString(3);
                String universit� = res.getString(4);
                String departement = res.getString(5);
                int dur�e = res.getInt(6);
                String dur�estring=Integer.toString(dur�e);
                double latitude = res.getDouble(7);
                double longitude = res.getDouble(8);
                languelist.add(langue);
                dur�elist.add(dur�estring);
                tabEch.add(new Echanges(pays, ville, langue, universit�,
                                        departement, dur�e, latitude,
                                        longitude));
            }
            //pour recuperer une liste avec les different langues des echanges
            for(int i=0;i<languelist.size();i++){
                if(languelistrie==null){
                    languelistrie.add(languelist.get(i));
                }else if(languelistrie.contains(languelist.get(i))){
                }else{
                    languelistrie.add(languelist.get(i));    
                }
            }
            System.out.println(languelistrie);
            //pour recuperer une liste avec les differents dur�es des echanges
               for(int i=0;i<dur�elist.size();i++){
                   if(dur�elistrie==null){
                       dur�elistrie.add(dur�elist.get(i));
                   }else if(dur�elistrie.contains(dur�elist.get(i))){
                   }else{
                       dur�elistrie.add(dur�elist.get(i));    
                   }
               }
            System.out.println(dur�elistrie);
            
            
            //System.out.println(tabEch.toString());
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

