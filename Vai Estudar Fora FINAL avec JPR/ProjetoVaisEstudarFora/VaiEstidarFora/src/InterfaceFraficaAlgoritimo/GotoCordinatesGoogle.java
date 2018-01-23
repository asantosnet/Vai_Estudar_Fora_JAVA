package InterfaceFraficaAlgoritimo;

import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.view.orbit.BasicOrbitView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Classe qui est le listener qui permetre recupere les donées fourni au panel google et que vas emener le globe où on veut
 */
public class GotoCordinatesGoogle implements ActionListener{
    private Google c=new Google();
    private PanelTest globo=new PanelTest();
    private ArrayList<String> villelisttrie=new ArrayList<String>();
    private ArrayList<String> payslist=new ArrayList<String>();
    private ArrayList<Pays> tabpays=new ArrayList<Pays>();
    private ArrayList<Université> tabuniversite=new ArrayList<Université>();
    private ArrayList<Echanges> tabechange=new ArrayList<Echanges>();
   /**
     *
     * @param c
     * @param globo
     * @param villelisttrie
     * @param payslist
     * @param tabpays
     * @param tabuniversite
     */
    public GotoCordinatesGoogle(ArrayList<Echanges> tabechange,Google c,PanelTest globo,ArrayList<String> villelisttrie,ArrayList<String> payslist,ArrayList<Pays> tabpays,ArrayList<Université> tabuniversite) {
        this.c=c;
        this.globo=globo;
        this.payslist=payslist;
        this.villelisttrie=villelisttrie;
        this.tabpays=tabpays;
        this.tabuniversite=tabuniversite;
        this.tabechange=tabechange;
    }


    public void actionPerformed(ActionEvent actionEvent) {
        //Pour recuperer la string du text area du google et la diviser entre lat e long
        System.out.println(c.getJComboBox1().getSelectedItem());
        //Si on a une coordone
        if(c.getJComboBox1().getSelectedItem()=="Coordonnees"){ 
            String [] temporatri=new String[10] ;
            temporatri=c.getJTextArea1().getText().split(";");
            if(c.getJComboBox1().getSelectedItem()=="Coordonnees"&&c.getJComboBox1().getSelectedItem()!=null){
                c.setLat(temporatri[0]);
                c.setLong(temporatri[1]);
            }else{
                c.setLat(null);
                c.setLong(null);
            }
            if(c.getLat()!=null&&c.getLong()!=null&&c.getLat()!="0"&&c.getLong()!="0"){
                try{
                    double lat=Double.parseDouble(c.getLat());
                    double lonng=Double.parseDouble(c.getLong());
                    //responsable pour emener le globle où on veut
                    BasicOrbitView view = (BasicOrbitView)globo.getWwd().getView();
                    //ici on tapez les valuers de latitude, longitude et altitude 
                    //ici on relgle 1140e3 car les marker vont apparaitre a 1155
                    view.addPanToAnimator(new Position(Angle.fromDegrees(lat),Angle.fromDegrees(lonng), 0),Angle.ZERO, Angle.ZERO,1140e3);
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null,"Tapez avec des points, ne pas utiliser virgule;veuillez taper des coordonnées numériques (pas de texte)");
                }
            }else if(c.getLat()=="0"){
                JOptionPane.showMessageDialog(null, "Veuillez tapez une lat different de 0");
            }else if(c.getLong()=="0"){
                JOptionPane.showMessageDialog(null, "Veuillez tapez une long different de 0");   
            }else{
                JOptionPane.showMessageDialog(null,"veuliez tapez une coordone");
            }
        //si on veut un pais
        }else if(c.getJComboBox1().getSelectedItem()=="Pays"){
            if(payslist.contains(c.getJTextArea1().getText())){
                int index=positiondanstabpays(c.getJTextArea1().getText());
                //pour recuperer la position du pays
                double latitude=tabpays.get(index).getLatitude();
                double longitude=tabpays.get(index).getLongitude();
                BasicOrbitView view = (BasicOrbitView)globo.getWwd().getView();
                //pour aller dans le pays qu'il a selectione
                //meme raison qu'avant pour le 1140e3
                view.addPanToAnimator(new Position(Angle.fromDegrees(latitude),Angle.fromDegrees(longitude),0),Angle.ZERO,Angle.ZERO,1140e3);
            }else{
                JOptionPane.showMessageDialog(null,"ce pays n'a pas d'echange pour le moment");      
            }

        }else{//pour une ville
            if(villelisttrie.contains(c.getJTextArea1().getText())){
                    int index=positiondanstabechange(c.getJTextArea1().getText());
                    //pour recuperer la position du pays
                    double latitude=tabechange.get(index).getLatitude();
                    double longitude=tabechange.get(index).getLongitude();
                    BasicOrbitView view = (BasicOrbitView)globo.getWwd().getView();
                    //pour aller à cette ville
                    view.addPanToAnimator(new Position(Angle.fromDegrees(latitude),Angle.fromDegrees(longitude),0),Angle.ZERO,Angle.ZERO,1140e3);
            }else{
                JOptionPane.showMessageDialog(null,"cette ville n'a pas d'echange pour le moment");      
            }
        }
    }
    /**
     *ce methode est responsable pour renvoier l'indice associe au item
     * @param item
     * @param list
     * @return
     */
    public int positiondanstabpays(String item){
        int  index=-1;
        boolean pastrouve=true;
        int i=0;
        while(pastrouve&&i<tabpays.size()){
            if(item.equals(tabpays.get(i).getNom())){
                index=i;
                pastrouve=false;
            }                               
        i++;
        }
        return index;       
    }
    /**
     *pour trouver le pays associe a la ville
     * @param ville
     * @return
     */
    public String paysassocie(String ville){
        String pays="";
        //on vas trouver sont index dans l'array list universite
        int index=-1;
        boolean pastrouve=true;
        int i=0;
        while(pastrouve&&i<tabuniversite.size()){
            if(ville.equals(tabuniversite.get(i).getVille())){
                index=i;
                pastrouve=false;
            }
            i++;    
        }
        pays=tabuniversite.get(i).getPays();
        return pays;
    }
    /**
     * pour retrouver la pos de l'université dans tabuni
     */
    public int positiondanstabechange(String item){
        int  index=-1;
        boolean pastrouve=true;
        int i=0;
        while(pastrouve&&i<tabechange.size()){
            if(item.equals(tabechange.get(i).getVille())){
                index=i;
                pastrouve=false;
            }                               
        i++;
        }
        return index;    
    }
    
}    
    