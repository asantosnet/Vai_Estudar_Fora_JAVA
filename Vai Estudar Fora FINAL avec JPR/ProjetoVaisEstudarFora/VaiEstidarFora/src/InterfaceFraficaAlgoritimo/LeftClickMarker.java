package InterfaceFraficaAlgoritimo;

import com.sun.org.apache.bcel.internal.generic.Select;

import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;

import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;

import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.view.orbit.BasicOrbitView;

import java.awt.BorderLayout;

import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * classe responsable pour que quand l'utilisatuer clique sur un marker il change de paneaux où que le globe se rapproche d'un point
 */
public class LeftClickMarker implements SelectListener {
    private JPanel background=new JPanel();
    private Panelgauche panelgauche;
    private Google google=new Google();
    private PanelTest globe=new PanelTest();
    private JFrame fenetre=new JFrame();
    private ArrayList<JPanel> historique=new ArrayList<JPanel>();
    private ArrayList<Marker> markslist= new ArrayList<Marker>();
    private ArrayList<Pays> payslist=new ArrayList<Pays>();
    private ArrayList<Université> universitelist=new ArrayList<Université>();
    /**
     *
     * @param historique
     * @param background
     * @param globe
     * @param fenetre
     * @param google
     * @param panelgauche
     * @param markslist
     */
    public LeftClickMarker(JPanel background,PanelTest globe,JFrame fenetre,Google google,Panelgauche panelgauche, ArrayList<Marker> markslist,ArrayList<Pays> payslist,ArrayList<Université> universitelist) {
        this.background=background;
        this.globe=globe;
        this.fenetre=fenetre;
        this.google=google;
        this.panelgauche=panelgauche;
        this.historique=historique;
        this.markslist=markslist;
        this.payslist=payslist;
        this.universitelist=universitelist;
    }

    @Override
    /**
     * l'evenement qui sera active quand on clique sur un marquer quelqu'onque
     */
    public void selected(SelectEvent event) {
        //pour defenir l'ction pour le cas où le marqueur est liee a un echange ou dans le cas où il est liee a un pays, premier else if echange
        // deuxieme else if pays
        if(event.getEventAction().equals(SelectEvent.LEFT_CLICK)&&event.getTopPickedObject().getPosition()==null&&isechange()){
            JOptionPane.showMessageDialog(null,"cliquez au centre du marqueur s'il vous plais");
        }else if (event.getEventAction().equals(SelectEvent.LEFT_CLICK)&&isechange()){
            //pour recuperer la position du marquer
            Position posmarker=event.getTopPickedObject().getPosition();
            
            //pour recuperer l'change associe a ce marquer
            String université=this.trouverechange(posmarker);
            Marker marker=new Marker(new PointPlacemark(posmarker),new String());
            
            //methode pour trouver l'index associe au marker
            if(trouverindexmarker(posmarker)==-1){
                System.out.println("marker pas trouve");
            }else{            
                 marker=markslist.get(trouverindexmarker(posmarker));
            }
            PanelInfoUnivglobe infouniv=new PanelInfoUnivglobe(globe,google,université,marker,payslist,universitelist);
            
            background.removeAll();
            background.setLayout(new BorderLayout());
            background.add(infouniv, BorderLayout.CENTER);
            background.setPreferredSize(new Dimension(800,1000));
            background.repaint();
            fenetre.setSize(new Dimension(850,950));
            fenetre.repaint();
            //recupero a coordenada do marker e dai recupero o marker utilisado
        }else if(event.getEventAction().equals(SelectEvent.LEFT_CLICK)){//dans le cas où on a le pais
            try{
            //pour recupere la position donne par le mouse dans le moment où l'utilisateur realise cette evenement
            double latitude=this.globe.getStatusBar().getEventSource().getCurrentPosition().getLatitude().getDegrees();
            double longitude=this.globe.getStatusBar().getEventSource().getCurrentPosition().getLongitude().getDegrees();
           
           //responsable poure aller vers un point du globe
            BasicOrbitView view = (BasicOrbitView)globe.getWwd().getView();
            view.addPanToAnimator(new Position(Angle.fromDegrees(latitude),Angle.fromDegrees(longitude), 0),Angle.ZERO, Angle.ZERO,1145e3);   
            }catch(NumberFormatException nfe){
            System.out.println("erro no left click marker");
            }
        }
    }
    /**
     *pour dire si est dans l'altitude pour le marqueur lie directment a l'echange
     * @return
     */
    public boolean isechange(){
        boolean enois=false;
        //pour trouver l'elevation e la comparer avec la elevation minimale pour que les marqueurs liees au echanges sont afiches
        if(globe.getStatusBar().getEventSource().getView().getEyePosition().getElevation()<=1155e3){
            enois=true;
            }
        return enois;
    }
    /**
     *il vas trouver l'echange associe a une position du globe
     * @param posmarker
     * @return
     */
    public String trouverechange(Position posmarker){
        String paisuniversit="";
        boolean wasnotfound=true;
        int i=0;
        PointPlacemark mark;
        Marker marker;
        //ce methode vas recuperer le marqueur associe
        while(wasnotfound&&i<markslist.size()){
            marker=markslist.get(i);
            mark=marker.getMarker();
            //si les position sont la meme,on arrete le while e on recupere le valeur du i qu'on veut
            if(this.memeposition(posmarker, mark)){
                wasnotfound=false;
                System.out.println(i);
                i=markslist.size();
                //on recupere le text associe au marqueur
                paisuniversit=mark.getLabelText();
            }            
            i++;
        }
        System.out.println(paisuniversit);
        return paisuniversit;
        }

    /**
     *ce code est utilise pour comparer deux position
     * @param posmarker
     * @param marker
     * @return
     */
    
    public boolean memeposition(Position posmarker,PointPlacemark mark){
        boolean isequallat=false;
        boolean isequal=false;
        
        //pour recuperer les val de lat et long e les mettre en double
        double latmarker=mark.getPosition().getLatitude().getDegrees();
        double longmarker=mark.getPosition().getLongitude().getDegrees();
        double longposmarker=posmarker.getLongitude().getDegrees();
        double latposmarker=posmarker.getLatitude().getDegrees();
        
        //dans le program on a une incertitude de +-0.001, donc on utilise ce code pour la prendre en compte
        //pour la lat
        if(latposmarker<(latmarker+0.001)&&latposmarker>(latmarker-0.001)){
                isequallat=true;
        }
        
        //pour la long
        if(isequallat&&longposmarker<(longmarker+0.001)&&longposmarker>(longmarker-0.001)){
            isequal=true;//les deux sont le meme, donc c'est bon
        }
        return isequal;
    }
    /**
     *cette methode vas renvoir le index du marker dans la markslist
     * @param posmarker
     * @return
     */
    public int trouverindexmarker(Position posmarker){
            boolean wasnotfound =true;
            int i=0;
            int index=-1;
            Marker marker;
            PointPlacemark mark;
            while(wasnotfound&&i<markslist.size()){
                marker=markslist.get(i);
                mark=marker.getMarker();    
                //si les position sont la meme,on arrete le while e on recupere le valeur du i qu'on veut
                if(this.memeposition(posmarker, mark)){
                    wasnotfound=false;
                    index=i;
                }            
                i++;
            }
        return index;       
        }
}
    
