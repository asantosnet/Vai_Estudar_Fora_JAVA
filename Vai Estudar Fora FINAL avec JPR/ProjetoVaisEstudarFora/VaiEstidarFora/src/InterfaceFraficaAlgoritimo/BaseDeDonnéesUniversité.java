package InterfaceFraficaAlgoritimo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class BaseDeDonnéesUniversité {

    private Connection conn;
    private Statement stmt;
    private ResultSet res;
    private ArrayList<Université> tabUniv = new ArrayList<Université>();
    private ArrayList<String> univelistrie=new ArrayList<String>();
    private ArrayList<String> univlist=new ArrayList<String>();
    private ArrayList<String> villelisttrie=new ArrayList<String>();
    private ArrayList<String> villelist=new ArrayList<String>();
    public ArrayList<Université> getTabEch() {
        return tabUniv;
    }

    public ArrayList<String> getUnivelistrie() {
        return univelistrie;
    }

    public ArrayList<String> getUnivlist() {
        return univlist;
    }

    public ArrayList<String> getVillelisttrie() {
        return villelisttrie;
    }

    public ArrayList<String> getVillelist() {
        return villelist;
    }

    public BaseDeDonnéesUniversité() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            //renseigner votre nom de login et votre mot de passe
            conn =DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com:3306/sql541992",
                         "sql541992", "cZ5*vP1%");
            Statement stmt = conn.createStatement();

            String sqlStr = ("select * from InfosUniversités");
            //System.out.println("test1");
            res = stmt.executeQuery(sqlStr);
            //System.out.println("test2");
            stmt = conn.createStatement();
            while (res.next()) {
                String pays = res.getString(1);
                String ville = res.getString(2);
                String université = res.getString(3);
                String description = res.getString(4);
                String photo = res.getString(5);
                villelist.add(ville);
                univlist.add(université);
                tabUniv.add(new Université(pays, ville, université,
                                        description, photo));
            }
            //pour recuperer une liste avec les differents villes
            for(int i=0;i<villelist.size();i++){
               if(villelisttrie==null){
                   villelisttrie.add(villelist.get(i));
               }else if(villelisttrie.contains(villelist.get(i))){
               }else{
                    villelisttrie.add(villelist.get(i));    
               }
            }
            System.out.println(villelisttrie);
            //pour recuperer la lsite avec les differents universités
            for(int i=0;i<univlist.size();i++){
                if(univelistrie==null){
                  univelistrie.add(univlist.get(i));
                }else if(univelistrie.contains(univlist.get(i))){
                }else{
                  univelistrie.add(univlist.get(i));    
                }
            }
            System.out.println(univelistrie);
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
