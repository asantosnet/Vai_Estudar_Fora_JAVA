package InterfaceFraficaAlgoritimo;


import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.avlist.AVKey;

import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.layers.MarkerLayer;
import gov.nasa.worldwind.layers.RenderableLayer;

import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Polyline;

import gov.nasa.worldwind.render.markers.BasicMarker;

import gov.nasa.worldwind.render.markers.BasicMarkerAttributes;

import gov.nasa.worldwind.render.markers.BasicMarkerShape;

import java.awt.Color;

import java.awt.Font;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * La calsse responsable pour ajouter les marker pais quand on fait la selection depuis le panelinfo
 * il manque, enlever les combox e remplacer par panegauche
 */
public class LayorPais {
    private ArrayList positions=new ArrayList();
        
        private JComboBox jComboBox1 = new JComboBox();
        private JComboBox jComboBox2 = new JComboBox();
        private JComboBox jComboBox3 = new JComboBox();
        private RenderableLayer layer=new RenderableLayer();
        private Panelgauche gauche;
        private PointPlacemarkAttributes pointattribute = new PointPlacemarkAttributes();
        private ArrayList<String> listmarkerpais =new ArrayList<String>();
        private PanelTest globo=new PanelTest();
        private ArrayList<Marker> listmarkers=new ArrayList<Marker>();
        private ArrayList<Pays> listpays=new ArrayList<Pays>();
        //utiliser pour savoir si on a pais où echange
        private boolean isechange=true;
        private ArrayList<Echanges> tabechanges=new ArrayList<Echanges>();

    public void setIsechange(boolean isechange) {
        this.isechange = isechange;
    }

    public boolean isIsechange() {
        return isechange;
    }


    public RenderableLayer getLayer() {
        return layer;
    }

    public ArrayList<String> getListmarkerpais() {
        return listmarkerpais;
    }

    public ArrayList<Marker> getListmarkers() {
        return listmarkers;
    }

    public LayorPais(PanelTest globo,Panelgauche gauche,ArrayList<Echanges> tabechanges,ArrayList<Pays> listpays){
            this.tabechanges=tabechanges;
            this.listpays=listpays;
            this.jComboBox1=gauche.getPanelhaut().getJComboBox1();
            this.jComboBox2=gauche.getPanelhaut().getJComboBox2();
            this.jComboBox3=gauche.getPanelhaut().getJComboBox3();
            this.globo=globo;
            
            //pour recuperer le pais où on a echanges en utilisant le BD
            PointPlacemark mark;
            Marker marker;
            String paysnom;
        
            pointattribute.setImageAddress("images/pushpins/castshadow-white.png");
            pointattribute.setImageColor(Color.yellow);
            pointattribute.setLabelFont(Font.decode("Verdana-Bold-8"));
            pointattribute.setLabelMaterial(Material.WHITE);
            //pour voir si le pays a éte ajoute a la arraylist
            boolean hasnotfound=false;   
            //pour additioner le pais a la arraylist
                for(int i=0;i<tabechanges.size();i++){
                    if(this.respectcritiére(tabechanges.get(i))){
                    mark=new PointPlacemark(Position.fromDegrees(tabechanges.get(i).getLatitude(),tabechanges.get(i).getLongitude()));
                    marker=new Marker(mark,tabechanges.get(i).getPays());  
                    paysnom=tabechanges.get(i).getPays();
                    if(this.wasfound(marker)){
                    }else{
                    listmarkerpais.add(paysnom);
                    //pour ajouter les marker à la layer
                    }
                    } 
                }
            
                //pour recupere les coordonees associes a chaque pays et le mettre sous forme d'une tableau de marker
                int index=0;
                for(int b=0;b<listmarkerpais.size();b++){
                    index=indexdupais(listmarkerpais.get(b));
                    if(index!=-1){
                        mark=new PointPlacemark(Position.fromDegrees(listpays.get(index).getLatitude(), listpays.get(index).getLongitude()));
                        paysnom=listpays.get(index).getNom();
                        mark.setLabelText(paysnom);
                        marker= new Marker(mark,paysnom);
                        this.listmarkers.add(marker);
                    }
                }
    }
        /**
     *pour voir si les conditions selections sont respecets par l'echange où nom
     * @param echange
     * @return
     */
        public boolean respectcritiére(Echanges echange){
            boolean hasbeenfound=false;
            if(jComboBox1.getSelectedItem().equals(echange.getDepartement())||jComboBox1.getSelectedItem().equals("Depart")){
                if(jComboBox2.getSelectedItem().equals(echange.getLangue())||jComboBox2.getSelectedItem().equals("Langue")){
                    if((jComboBox3.getSelectedItem()).equals(Integer.toString(echange.getDurée())+" semaine")||jComboBox3.getSelectedItem().equals("Durée en semaine")){
                        hasbeenfound=true;
                    }
                }
            }     
            return hasbeenfound;
        }
        /**
     *pour savoir si le marker a éte deja mis dans la arraylist où non
     * @param marker
     * @return
     */
        public boolean wasfound(Marker marker){
             boolean trouve=false;
             for(int i=0;i<listmarkerpais.size();i++){
                 if(listmarkerpais.get(i).equals(marker.getPays())){
                    trouve=true;
                }
             }
             return trouve;          
        }
        /**
     *le methode responsable pour trouver le index associe au pays, 
     * 
     * 
     * @param nompays
     * @return
     */
        public int indexdupais(String nompays){
            int index=-1;
            PointPlacemark mark;
            for(int i=0;i<this.listpays.size();i++){
                if(listpays.get(i).getNom().equals(nompays)){
                    index=i;
                }
            }
            return index;
        }
}

