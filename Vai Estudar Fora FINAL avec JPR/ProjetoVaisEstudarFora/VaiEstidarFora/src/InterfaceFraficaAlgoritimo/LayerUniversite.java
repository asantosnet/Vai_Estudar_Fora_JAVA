package InterfaceFraficaAlgoritimo;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.GlobeAnnotation;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.util.ArrayList;


public class LayerUniversite {
    
    private RenderableLayer layer= new RenderableLayer();
    private  ArrayList<String> coordonnes= new  ArrayList<String>();
    private Info information=new Info();
    private ArrayList<Marker> markslist=new ArrayList<Marker>();
    private PointPlacemarkAttributes pointAttribute = new PointPlacemarkAttributes();
    private ArrayList<Echanges> tabechange=new ArrayList<Echanges>();
    public ArrayList<Marker> getMarkslist() {
        return markslist;
    }

    public RenderableLayer getLayer() {
        return layer;
    }
    
    public LayerUniversite(ArrayList<Echanges> tabechange) {
        this.tabechange=tabechange;
        //pour donner les atributes 
        pointAttribute.setImageColor(Color.black);
        pointAttribute.setLabelFont(Font.decode("Verdana-Bold-8"));
        pointAttribute.setLabelMaterial(Material.WHITE);

        
        //pour prendre tous les coordones pour aficher les truc dans le map
       Echanges echange;
       PointPlacemark mark;
       Marker marker;
       System.out.println(tabechange.size());
        
      for (int i = 0; i < tabechange.size(); i++){
            System.out.println("afichage layeruniversite");
          
            //pour recuperer les informations de la base de donnes
            echange = tabechange.get(i);
            double latitude=echange.getLatitude();
            double longitude=echange.getLongitude();
            String langue=echange.getLangue();
            String ville=echange.getVille();
            String nompays=echange.getPays();
            String nomuniversite=echange.getUniversité();
            String departement=echange.getDepartement();
            String duree=Integer.toString(echange.getDurée());
            
            mark=new PointPlacemark(Position.fromDegrees(latitude,longitude));
            marker=new Marker(mark,nompays);
            
            //pour voir si la coordone n'etais pas deja ajoute
            if(this.dejaexistante(latitude, longitude)){
                System.out.println("deja ajoute");
                
                markslist.get(valeurassocie(latitude, longitude)).getDepartementlist().add(departement);
                markslist.get(valeurassocie(latitude,longitude)).getDuréelist().add(duree);
            }else{
                System.out.println(" ajoute");
                markslist.add(marker);
                int index=markslist.indexOf(marker);
                
                markslist.get(index).getDepartementlist().add(departement);
                markslist.get(index).getDuréelist().add(duree);
                markslist.get(index).setLangue(langue);
                markslist.get(index).getMarker().setLabelText(ville+","+nomuniversite);
                
                //si on veut le meme atribut pour tout
                markslist.get(index).getMarker().setAttributes(pointAttribute);
                System.out.println(markslist.get(index).getLangue());
            }
        }
 
        //pour ajouter les indicateurs dans la list a la layer
        System.out.println(markslist.size());
        for(int d=0;d<markslist.size();d++){
                layer.addRenderable(markslist.get(d).getMarker());
             
        }
        
    }
    /**
     *Methode pour voir si  l'universite a été deja associe a un marqueur.Chaque universite est associe a un coordone, donc je voit si les 
     * coordones on ete deja ajoutes a la list
     * @param latitude
     * @param longitude
     * @return
     */
    public boolean dejaexistante(double latitude,double longitude){
        //pour recuperer les donnes de l'arraylist
        double latitudearray;
        double longitudearray;
        boolean napastrouve=true;
        boolean dejaexistante=false;
        int i=0;
        
        //on compare les valeurs qu'on  a dans marklist avec le valeur du marker
        while(i<this.markslist.size()&&napastrouve){
            longitudearray=markslist.get(i).getMarker().getPosition().getLongitude().getDegrees();
            latitudearray=markslist.get(i).getMarker().getPosition().getLatitude().getDegrees();
            if(longitudearray==longitude&&latitudearray==latitude){
                napastrouve=false;
                i=markslist.size();
                dejaexistante=true;
            }
            i++;
        }
        return dejaexistante;
        }
    /**
     *methode pour recupere la position du marker dans la array list marklist
     * @param latitude
     * @param longitude
     * @return
     */
    public int valeurassocie(double latitude,double longitude){
        int position=0;
        double latitudearray;
        double longitudearray;
        boolean napastrouve=true;
        int i=0;
        
        //je doit creer un while, je peut pas utiliser indexof car il y un valeur de la position du marker et de la position qu'il y a dans la list echanges qui je n'ai pas
        while(i<this.markslist.size()&&napastrouve){
            longitudearray=markslist.get(i).getMarker().getPosition().getLongitude().getDegrees();
            latitudearray=markslist.get(i).getMarker().getPosition().getLatitude().getDegrees();
            if(longitudearray==longitude&&latitudearray==latitude){
                napastrouve=false;
                position=i;
                i=markslist.size();
            }
            i++;
        }
        
        return position;
    }
}
