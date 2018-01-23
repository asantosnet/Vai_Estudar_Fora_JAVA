package InterfaceFraficaAlgoritimo;



import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;

import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.render.GlobeAnnotation;
import gov.nasa.worldwind.render.PointPlacemark;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


import java.util.ArrayList;


/**
 * La calsse responsable pour faire aficher l'information au dessus du marker
 */
public class RoolOverMarker  implements SelectListener{
        private PanelTest globo=new PanelTest();
        private PointPlacemark marker;
        private AnnotationLayer annotationlayer = new AnnotationLayer();
        private ArrayList<Marker> markslist=new ArrayList<Marker>();
        private ArrayList<Echanges> tabechanges=new ArrayList<Echanges>();
        private AnnotationLayer annotationlayerpays=new AnnotationLayer();
    public AnnotationLayer getAnnotationlayerpays() {
        return annotationlayerpays;
    }


    /**
     *quand le marker est associe a un echange
     * @param markslist
     * @param marker
     * @param globo
     */
        public RoolOverMarker(ArrayList<Marker> markslist,PointPlacemark marker,PanelTest globo) {
            this.marker=marker;
            this.globo=globo;
            this.markslist=markslist;
        }
    /**
     *quand le marker est associe a un pays
     * @param markslist
     * @param marker
     * @param globo
     * @param tabechanges
     */
        public RoolOverMarker(ArrayList<Marker> markslist,PointPlacemark marker,PanelTest globo,ArrayList<Echanges> tabechanges){
            this.marker=marker;
            this.globo=globo;
            this.markslist=markslist;
            this.tabechanges=tabechanges;
        }
        /**
     *l'evenement
     * @param event
     */
        public void selected(SelectEvent event) {
            if(event.getEventAction().equals(SelectEvent.ROLLOVER)&&event.getTopObject()==null){
            }
            else if (event.getEventAction().equals(SelectEvent.ROLLOVER)&&isechange()){
                this.Sourlinger(event.getTopObject());
                this.AddannotationEchange(event.getTopObject());
            }else if (event.getEventAction().equals(SelectEvent.ROLLOVER)){
                this.Sourlinger(event.getTopObject());
                this.AddannotationPays(event.getTopObject());
                
            }
        }
        /**
     *methode que vas dire si on doit où non sourlinger le marker
     * @param objetc
     */
        public void Sourlinger(Object objetc){
            boolean sourlinger= false;
            if(objetc.equals(marker)){
                sourlinger=true;    
            }
            marker.setHighlighted(sourlinger);
        }
        /**
     *methode pour addicioner l'anotation si on a un echange
     * @param object
     */
        public void AddannotationEchange(Object object){
            if(object.equals(marker)){
                
                //pour aficher l'annotation
                Position position = marker.getPosition();
                //pour recupere le bon marker, on recupere l'indice
                int index=this.valeurassocie(marker.getPosition().getLatitude().getDegrees(), marker.getPosition().getLongitude().getDegrees());
                
                //pour recuperer les valeurs associe a ce marker
                String langue=markslist.get(index).getLangue();
                String [] universitevilletab=markslist.get(index).getMarker().getLabelText().split(",");
                String universite=universitevilletab[0];
                ArrayList<String> departementlist=markslist.get(index).getDepartementlist();
                
                //pour ajouter le resume de chaque marker
                GlobeAnnotation ga = new GlobeAnnotation("Les departements qui ont une programme d'echange avec "+universite+" sont  "+departementlist+"  la langue parlee est "+langue,position, Font.decode("Arial-BOLD-13"));
                
                ga.getAttributes().setBorderColor(Color.WHITE);
                ga.getAttributes().setBackgroundColor(Color.WHITE);
                ga.getAttributes().setBorderWidth(1);
                ga.getAttributes().setImageScale(1.2);
                ga.getAttributes().setSize(new Dimension(200,170));
                ga.setAlwaysOnTop(true);
                ga.setMaxActiveAltitude(1155e3);
                annotationlayer.addAnnotation(ga);      
                globo.insertBeforeCompass(globo.getWwd(),annotationlayer); 
            }else{
                     annotationlayer.removeAllAnnotations();
                }
            }
        /**
     *pour le marler associe a un pays
     * @param object
     */
        public void AddannotationPays(Object object){
            if(object.equals(marker)){
            Position position = marker.getPosition();
            //pour recupere le marker
            int index=this.valeurassocie(marker.getPosition().getLatitude().getDegrees(),marker.getPosition().getLongitude().getDegrees());
            ArrayList<String> departements=new ArrayList<String>();
            int numerodechanges=0;
            //pour recuperer le nom du pays
            String nomteste="";    
            //pour recupere combien d'echanges sont associes a ce marker et les departements
            for(int i=0;i<tabechanges.size();i++){
                nomteste=tabechanges.get(i).getPays();
                
                if(markslist.get(index).getPays().equals(nomteste)){   
                    if(departements.size()==0){
                      departements.add(tabechanges.get(i).getDepartement());
                    }else if(departements.contains(tabechanges.get(i).getDepartement())){
                    }else{
                        departements.add(tabechanges.get(i).getDepartement());
                    }
                    numerodechanges++;
                }
            }

            String nompays=markslist.get(index).getPays();                       
            //pour ajouter le resume de chaque marker
            GlobeAnnotation ga = new GlobeAnnotation("Les departements qui ecistent dans le pays: "+nompays+" sont "+ departements+" le numero d'echanges est "+numerodechanges,position, Font.decode("Arial-BOLD-13"));
            
            ga.getAttributes().setBorderColor(Color.WHITE);
            ga.getAttributes().setBackgroundColor(Color.WHITE);
            ga.getAttributes().setBorderWidth(1);
            ga.getAttributes().setImageScale(1.2);
            ga.getAttributes().setSize(new Dimension(200,180));
            ga.setAlwaysOnTop(true);
            ga.setMinActiveAltitude(1160e3);
            annotationlayerpays.addAnnotation(ga);
                globo.insertBeforeCompass(globo.getWwd(),annotationlayerpays); 
            }else{
                     annotationlayerpays.removeAllAnnotations();
            }
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
        //je doit creer un whiel, je peut pas utiliser indexof car il y un valeur de la position du marker et de la position qu'il y a dans la list echanges qui je n'ai pas
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
    /**
     *pour dire si est dans l'altitude pour le marqueur lie directment a l'echange
     * @return
     */
    public boolean isechange(){
        boolean enois=false;
        //pour trouver l'elevation e la comparer avec la elevation minimale pour que les marqueurs liees au echanges sont afiches
        if(globo.getStatusBar().getEventSource().getView().getEyePosition().getElevation()<=1155e3){
            enois=true;
            }
        return enois;
    }
   
}

